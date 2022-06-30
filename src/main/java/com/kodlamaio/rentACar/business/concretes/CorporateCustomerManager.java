package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.corporatecustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.responses.corporatecustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerRepository corporateCustomerRepository;
	private ModelMapperService modelMapperService;
	private AddressRepository addressRepository;

	public CorporateCustomerManager(CorporateCustomerRepository corporateCustomerRepository,
			ModelMapperService modelMapperService, AddressRepository addressRepository) {
		super();
		this.corporateCustomerRepository = corporateCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.addressRepository = addressRepository;
	}

	@Override
	public Result addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		checkAddress(createCorporateCustomerRequest.getAddressId());
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult("added successfully");
	}

	@Override
	public Result deleteCorporateCustomer(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(deleteCorporateCustomerRequest.getId());
		CorporateCustomer corporateCustomer = modelMapperService.forRequest()
				.map(deleteCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomerRepository.deleteById(corporateCustomer.getId());
		return new SuccessResult("deleted successfully");
	}

	@Override
	public Result updateCorporateCustomer(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(updateCorporateCustomerRequest.getId());
		checkAddress(updateCorporateCustomerRequest.getAddressId());
		CorporateCustomer corporateCustomer =modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		corporateCustomerRepository.save(corporateCustomer);
		return new SuccessResult(updateCorporateCustomerRequest.getId()+" updated successfully");
	}

	@Override
	public DataResult<CorporateCustomer> getById(ReadCorporateCustomerResponse readCorporateCustomerResponse) {
		checkIfCorporateCustomerExistsById(readCorporateCustomerResponse.getId());
		CorporateCustomer corporateCustomer =modelMapperService.forRequest().map(readCorporateCustomerResponse, CorporateCustomer.class);
		corporateCustomer =corporateCustomerRepository.findById(corporateCustomer.getCorporateCustomerId()).get();		
		return new SuccessDataResult<CorporateCustomer>(corporateCustomer,"listed successfully");
	}

	@Override
	public DataResult<List<GetAllCorporateCustomersResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers = corporateCustomerRepository.findAll();
		List<GetAllCorporateCustomersResponse> response = corporateCustomers.stream()
				.map(item -> modelMapperService.forResponse().map(item, GetAllCorporateCustomersResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCorporateCustomersResponse>>(response, "listed successfully");
	}

	// adres kontrolü
	private void checkAddress(int id) {
		boolean result = addressRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("ADDRESS NOT EXISTS");
		}
	}

	// var olmayan customer kontrolü
	private void checkIfCorporateCustomerExistsById(int id) {
		boolean result = corporateCustomerRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("CUSTOMER NOT EXISTS");
		}
	}
}
