package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerCheckService;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualcustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.individualcustomers.GetAllIndividualCustomersFilterResponse;
import com.kodlamaio.rentACar.business.responses.individualcustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.responses.individualcustomers.ReadIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private ModelMapperService modelMapperService;
	private IndividualCustomerRepository individualCustomerRepository;
	private IndividualCustomerCheckService individualCustomerCheckService;
	private AddressRepository addressRepository;

	public IndividualCustomerManager(ModelMapperService modelMapperService,
			IndividualCustomerRepository individualCustomerRepository,
			IndividualCustomerCheckService individualCustomerCheckService, AddressRepository addressRepository) {
		this.modelMapperService = modelMapperService;
		this.individualCustomerRepository = individualCustomerRepository;
		this.individualCustomerCheckService = individualCustomerCheckService;
		this.addressRepository = addressRepository;
	}

	@Override
	public Result addIndividualCustomer(CreateIndividualCustomerRequest createIndividualCustomerRequest)
			throws NumberFormatException, RemoteException {
		IndividualCustomer individualCustomer = modelMapperService.forRequest()
				.map(createIndividualCustomerRequest, IndividualCustomer.class);
		CheckIfRealPerson(individualCustomer);
		checkIfIndividualCustomerExistIdentityNumber(individualCustomer.getIdentityNumber());
		checkIfAddress(individualCustomer.getAddress().getId());
		individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("Individual Customer added successfully");
	}

	@Override
	public Result deleteIndividualCustomer(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		checkIfIndividualCustomerExistById(deleteIndividualCustomerRequest.getId());
		individualCustomerRepository.deleteById(deleteIndividualCustomerRequest.getId());
		return new SuccessResult(" id: " + deleteIndividualCustomerRequest.getId() + " customer deleted successfully");
	}

	@Override
	public Result updateIndividualCustomer(UpdateIndividualCustomerRequest updateIndividualCustomerRequest)
			throws NumberFormatException, RemoteException {
		checkIfIndividualCustomerExistById(updateIndividualCustomerRequest.getIndividualCustomerId());
		checkIfIndividualCustomerExistUpdateIdentityNumber(updateIndividualCustomerRequest.getIdentityNumber());
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(updateIndividualCustomerRequest, IndividualCustomer.class);
		CheckIfRealPerson(individualCustomer);
		checkIfAddress(individualCustomer.getAddress().getId());
		individualCustomerRepository.save(individualCustomer);
		return new SuccessResult("Individual Customer updated successfully");
	}

	@Override
	public DataResult<IndividualCustomer> getById(ReadIndividualCustomerResponse readIndividualCustomerResponse) {
		checkIfIndividualCustomerExistById(readIndividualCustomerResponse.getId());
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(readIndividualCustomerResponse,
				IndividualCustomer.class);
		individualCustomer = individualCustomerRepository.findById(readIndividualCustomerResponse.getId()).get();
		return new SuccessDataResult<IndividualCustomer>(individualCustomer,
				" the individual customer was successfully listed ");
	}

	@Override
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomersResponse> response = individualCustomers.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllIndividualCustomersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomersResponse>>(response,
				" individual customers was successfully listed ");
	}

	@Override
	public DataResult<List<GetAllIndividualCustomersResponse>> getAllByPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll(pageable).getContent();
		List<GetAllIndividualCustomersResponse> response = individualCustomers.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllIndividualCustomersResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllIndividualCustomersResponse>>(response,
				"individual customers was successfully listed by page");

	}

	// stream api
	@Override
	public DataResult<List<GetAllIndividualCustomersFilterResponse>> getAllFilterIndividualCustomers() {
		List<IndividualCustomer> individualCustomer = this.individualCustomerRepository.findAll();
		List<GetAllIndividualCustomersFilterResponse> response = individualCustomer.stream().map(
				user -> this.modelMapperService.forResponse().map(user, GetAllIndividualCustomersFilterResponse.class))
				.filter(user -> (user.getFirstName().contains("a") && (user.getBirthYear() > 1990)))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllIndividualCustomersFilterResponse>>(response,
				"individual customers was successfully listed by filter");
	}

	@Override
	public DataResult<String> getIndividualCustomerAddressById(
			ReadIndividualCustomerResponse readIndividualCustomerResponse) {
		IndividualCustomer individualCustomer = individualCustomerRepository
				.findById(readIndividualCustomerResponse.getId()).get();
		checkIfIndividualCustomerExistIdentityNumber(individualCustomer.getIdentityNumber());
		Address address = individualCustomer.getAddress();
		String data = address.getAddress();
		return new DataResult<String>(data, true, "listed successfully");
	}

	@Override
	public DataResult<String> getIndividualCustomerInvoiceAddressById(
			ReadIndividualCustomerResponse readIndividualCustomerResponse) {
		IndividualCustomer individualCustomer = individualCustomerRepository
				.findById(readIndividualCustomerResponse.getId()).get();
		checkIfIndividualCustomerExistIdentityNumber(individualCustomer.getIdentityNumber());
		Address address = individualCustomer.getAddress();
		String data = address.getInvoiceAddress();
		return new DataResult<String>(data, true, "listed successfully");
	}

	// tc kımlık no daha once eklenmis mi?
	private void checkIfIndividualCustomerExistIdentityNumber(String identityNumber) {
		List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();
		for (IndividualCustomer item : individualCustomers) {
			if (item.getIdentityNumber().equals(identityNumber)) {
				throw new BusinessException("CUSTOMER EXISTS");
			}
		}
	}
	
	private void checkIfIndividualCustomerExistUpdateIdentityNumber(String identityNumber) {
		List<IndividualCustomer> individualCustomers = individualCustomerRepository.findAll();
		for (IndividualCustomer item : individualCustomers) {
			if (!item.getIdentityNumber().equals(identityNumber)) {
				throw new BusinessException("CUSTOMER NOT EXISTS");
			}
		}
	}

	// kısı dogrumu mernis kontrolü
	private void CheckIfRealPerson(IndividualCustomer individualCustomer)
			throws NumberFormatException, RemoteException {
		if (!individualCustomerCheckService.CheckIfRealPerson(individualCustomer)) {
			throw new BusinessException("USER VALIDATION ERROR");
		}

	}

	private void checkIfAddress(int id) {
		boolean result = addressRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("ADDRESS NOT EXIST");
		}
	}

	// boyle bi id var mı? bence yok :)
	private void checkIfIndividualCustomerExistById(int id) {
		boolean result = individualCustomerRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("INDIVİDUAL CUSTOMER NOT EXIST");
		}
	}

}
