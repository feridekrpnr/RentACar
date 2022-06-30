package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.IndividualInvoiceService;
import com.kodlamaio.rentACar.business.requests.individualinvoices.CreateIndividualInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.individualinvoices.StateUpdateIndividualInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.individualinvoices.GetAllIndividualInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.individualinvoices.ReadIndividualInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualInvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;
import com.kodlamaio.rentACar.entities.concretes.IndividualInvoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class IndividualInvoiceManager implements IndividualInvoiceService {

	private IndividualInvoiceRepository individualInvoiceRepository;
	private IndividualCustomerRepository individualCustomerRepository;
	private AdditionalServiceRepository additionalServiceRepository;
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;

	public IndividualInvoiceManager(IndividualInvoiceRepository individualInvoiceRepository,
			IndividualCustomerRepository individualCustomerRepository,
			AdditionalServiceRepository additionalServiceRepository, RentalRepository rentalRepository,
			ModelMapperService modelMapperService) {
		super();
		this.individualInvoiceRepository = individualInvoiceRepository;
		this.individualCustomerRepository = individualCustomerRepository;
		this.additionalServiceRepository = additionalServiceRepository;
		this.rentalRepository = rentalRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result addIndividualInvoice(CreateIndividualInvoiceRequest createIndividualInvoiceRequest) {
		checkIfRentalExist(createIndividualInvoiceRequest.getRentalId());
		checkIfAdditionalServiceExist(createIndividualInvoiceRequest.getAdditionalServiceId());
		checkIfIndividualCustomerExist(createIndividualInvoiceRequest.getIndividualCustomerId());
		IndividualInvoice individualInvoice = modelMapperService.forRequest().map(createIndividualInvoiceRequest, IndividualInvoice.class);
		individualInvoice.setState(1);
		individualInvoice.setRental(individualInvoice.getRental());
		individualInvoice.setTotalPrice(CalculateTotalPrice(createIndividualInvoiceRequest.getRentalId(),createIndividualInvoiceRequest.getAdditionalServiceId()));;
		individualInvoiceRepository.save(individualInvoice);
		return new SuccessResult("added successfully");
	}


	@Override
	public Result cancelIndividualInvoice(StateUpdateIndividualInvoiceRequest stateUpdateIndividualInvoiceRequest) {
		checkIfIndividualInvoicesExist(stateUpdateIndividualInvoiceRequest.getId());
		IndividualInvoice individualInvoice = modelMapperService.forRequest().map(stateUpdateIndividualInvoiceRequest, IndividualInvoice.class);
		individualInvoice=individualInvoiceRepository.findById(stateUpdateIndividualInvoiceRequest.getId()).get();
		individualInvoice.setState(0);
		individualInvoiceRepository.save(individualInvoice);
		return new SuccessResult("Invoices state changed to false");
	}




	@Override
	public Result activateIndividualInvoice(StateUpdateIndividualInvoiceRequest stateUpdateIndividualInvoiceRequest) {
		checkIfIndividualInvoicesExist(stateUpdateIndividualInvoiceRequest.getId());
		IndividualInvoice individualInvoice = modelMapperService.forRequest().map(stateUpdateIndividualInvoiceRequest, IndividualInvoice.class);
		individualInvoice=individualInvoiceRepository.findById(stateUpdateIndividualInvoiceRequest.getId()).get();
		individualInvoice.setState(1);
		individualInvoiceRepository.save(individualInvoice);
		return new SuccessResult("Invoices state changed to true");
	}




	@Override
	public DataResult<List<GetAllIndividualInvoicesResponse>> getAll() {
		List<IndividualInvoice> individualInvoices = this.individualInvoiceRepository.findAll();
		List<GetAllIndividualInvoicesResponse> response = individualInvoices.stream()
				.map(item -> modelMapperService.forResponse().map(item, GetAllIndividualInvoicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualInvoicesResponse>>(response, "the invoices successfully listed");
	}




	@Override
	public DataResult<IndividualInvoice> getById(ReadIndividualInvoiceResponse readIndividualInvoiceResponse) {
		checkIfIndividualInvoicesExist(readIndividualInvoiceResponse.getId());
		IndividualInvoice individualInvoiceToGet = modelMapperService.forResponse().map(readIndividualInvoiceResponse, IndividualInvoice.class);
		individualInvoiceToGet = this.individualInvoiceRepository.findById(readIndividualInvoiceResponse.getId()).get();
		return new SuccessDataResult<IndividualInvoice>(individualInvoiceToGet, "the invoices successfully listed");
	}


	// faturaya kiralama eklemeden önce kontrol sağlıyoruz
	private void checkIfRentalExist(int id) {

		boolean result = rentalRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("RENTAL NOT EXIST");
		}
	}

	// faturaya ek servis eklemeden kontrol sagla
	private void checkIfAdditionalServiceExist(int id) {
		boolean result = additionalServiceRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("ADDITIONAL SERVİCE NOT EXIST");
		}
	}

	// faturaya müşteri eklemeden önce kontrol sağlıyoruz
	private void checkIfIndividualCustomerExist(int id) {
		boolean result = individualCustomerRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("INDIVIDUAL CUSTOMER NOT EXIST");
		}
	}

	// iptal etme ve active etme işlemleri için öncesinde kontrol sağlıyoruz
	private void checkIfIndividualInvoicesExist(int id) {
		boolean result = individualInvoiceRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("INDIVIDUAL INVOICE NOT EXIST");
		}
	}

	// Fatura toplam ücreti hesaplıyoruz
	private double CalculateTotalPrice(int rentalId, int additionalServiceId) {
		double totalPrice;
		Rental rental = rentalRepository.findById(rentalId).get();
		AdditionalService additionalService = additionalServiceRepository.findById(additionalServiceId).get();
		totalPrice = rental.getTotalPrice() + additionalService.getTotalPrice();
		return totalPrice;
	}






}
