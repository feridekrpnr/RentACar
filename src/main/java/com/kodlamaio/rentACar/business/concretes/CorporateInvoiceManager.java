package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateInvoiceService;
import com.kodlamaio.rentACar.business.requests.corporateinvoices.CreateCorporateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.corporateinvoices.StateUpdateCorporateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.corporateinvoices.GetAllCorporateInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.corporateinvoices.ReadCorporateInvoiceResponse;
import com.kodlamaio.rentACar.business.responses.individualinvoices.GetAllIndividualInvoicesResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateInvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;
import com.kodlamaio.rentACar.entities.concretes.CorporateInvoice;
import com.kodlamaio.rentACar.entities.concretes.IndividualInvoice;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class CorporateInvoiceManager implements CorporateInvoiceService {

	private CorporateInvoiceRepository corporateInvoiceRepository;
	private CorporateCustomerRepository corporateCustomerRepository;
	private AdditionalServiceRepository additionalServiceRepository;
	private RentalRepository rentalRepository;
	private ModelMapperService modelMapperService;

	public CorporateInvoiceManager(CorporateInvoiceRepository corporateInvoiceRepository,
			CorporateCustomerRepository corporateCustomerRepository,
			AdditionalServiceRepository additionalServiceRepository, RentalRepository rentalRepository,
			ModelMapperService modelMapperService) {
		super();
		this.corporateInvoiceRepository = corporateInvoiceRepository;
		this.corporateCustomerRepository = corporateCustomerRepository;
		this.additionalServiceRepository = additionalServiceRepository;
		this.rentalRepository = rentalRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result addCorporateInvoice(CreateCorporateInvoiceRequest createCorporateInvoiceRequest) {
		checkIfRentalExist(createCorporateInvoiceRequest.getRentalId());
		checkIfAdditionalServiceExist(createCorporateInvoiceRequest.getAdditionalServiceId());
		checkIfCorporateCustomerExist(createCorporateInvoiceRequest.getCorporateCustomerId());
		CorporateInvoice corporateInvoice = modelMapperService.forRequest().map(createCorporateInvoiceRequest,
				CorporateInvoice.class);
		corporateInvoice.setState(1);
		corporateInvoice.setRental(corporateInvoice.getRental());
		corporateInvoice.setTotalPrice(CalculateTotalPrice(createCorporateInvoiceRequest.getRentalId(),
				createCorporateInvoiceRequest.getAdditionalServiceId()));
		;
		corporateInvoiceRepository.save(corporateInvoice);
		return new SuccessResult("added successfully");
	}

	@Override
	public Result cancelCorporateInvoice(StateUpdateCorporateInvoiceRequest stateUpdateCorporateInvoiceRequest) {
		checkIfCorporateInvoicesExist(stateUpdateCorporateInvoiceRequest.getId());
		CorporateInvoice corporateInvoice = modelMapperService.forRequest().map(stateUpdateCorporateInvoiceRequest,
				CorporateInvoice.class);
		corporateInvoice=corporateInvoiceRepository.findById(stateUpdateCorporateInvoiceRequest.getId()).get();
		corporateInvoice.setState(0);
		corporateInvoiceRepository.save(corporateInvoice);
		return new SuccessResult("Invoices state changed to false");
	}

	@Override
	public Result activateCorporateInvoice(StateUpdateCorporateInvoiceRequest stateUpdateCorporateInvoiceRequest) {
		checkIfCorporateInvoicesExist(stateUpdateCorporateInvoiceRequest.getId());
		CorporateInvoice corporateInvoice = modelMapperService.forRequest().map(stateUpdateCorporateInvoiceRequest,
				CorporateInvoice.class);
		corporateInvoice=corporateInvoiceRepository.findById(stateUpdateCorporateInvoiceRequest.getId()).get();
		corporateInvoice.setState(0);
		corporateInvoiceRepository.save(corporateInvoice);
		return new SuccessResult("Invoices state changed to true");
	}

	@Override
	public DataResult<List<GetAllCorporateInvoicesResponse>> getAll() {
		List<CorporateInvoice> corporateInvoices = this.corporateInvoiceRepository.findAll();
		List<GetAllCorporateInvoicesResponse> response = corporateInvoices.stream()
				.map(item -> modelMapperService.forResponse().map(item,GetAllCorporateInvoicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateInvoicesResponse>>(response, "the invoices successfully listed");
	}

	@Override
	public DataResult<CorporateInvoice> getById(ReadCorporateInvoiceResponse readCorporateInvoiceResponse) {
		checkIfCorporateInvoicesExist(readCorporateInvoiceResponse.getId());
		CorporateInvoice corporateInvoice = modelMapperService.forResponse().map(readCorporateInvoiceResponse, CorporateInvoice.class);
		corporateInvoice = this.corporateInvoiceRepository.findById(readCorporateInvoiceResponse.getId()).get();
		return new SuccessDataResult<CorporateInvoice>(corporateInvoice, "the invoices successfully listed");
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
	private void checkIfCorporateCustomerExist(int id) {
		boolean result = corporateCustomerRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("INDIVIDUAL CUSTOMER NOT EXIST");
		}
	}

	// iptal etme ve active etme işlemleri için öncesinde kontrol sağlıyoruz
	private void checkIfCorporateInvoicesExist(int id) {
		boolean result = corporateInvoiceRepository.existsById(id);
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
