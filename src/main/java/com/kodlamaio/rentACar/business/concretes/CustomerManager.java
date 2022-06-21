package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerCheckService;
import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.customers.GetAllCustomersFilterResponse;
import com.kodlamaio.rentACar.business.responses.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.responses.customers.ReadCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;
import com.kodlamaio.rentACar.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {

	@Autowired
	private ModelMapperService mapper;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerCheckService customerCheckService;

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) throws NumberFormatException, RemoteException {

		checkIfUserExistTcNo(createCustomerRequest.getTcNo());
		Customer customer = this.mapper.forRequest().map(createCustomerRequest, Customer.class);
		if (customerCheckService.CheckIfRealPerson(customer)) {
			this.customerRepository.save(customer);
			return new SuccessResult("CUSTOMER.ADDED");
		} else {
			throw new BusinessException("CUSTOMER NOT ADDED");
		}

	}

	@Override
	public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
		Customer customer = this.mapper.forRequest().map(deleteCustomerRequest, Customer.class);
		this.customerRepository.delete(customer);
		return new SuccessResult("CUSTOMER.DELETED");
	}

	@Override
	public Result update(UpdateCustomerRequest updateCustomerRequest) {
		Customer customer = this.mapper.forRequest().map(updateCustomerRequest, Customer.class);
		this.customerRepository.save(customer);
		return new SuccessResult("CUSTOMER.UPDATED");
	}

	@Override
	public DataResult<Customer> getById(ReadCustomerResponse readCustomerResponse) {
		Customer item = this.mapper.forRequest().map(readCustomerResponse, Customer.class);
		item = customerRepository.findById(readCustomerResponse.getId());
		return new SuccessDataResult<Customer>(item);
	}

	@Override
	public DataResult<List<GetAllCustomersResponse>> getAll() {
		List<Customer> customers = this.customerRepository.findAll();
		List<GetAllCustomersResponse> response = customers.stream()
				.map(user -> this.mapper.forResponse().map(user, GetAllCustomersResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllCustomersResponse>>(response);
	}

	private void checkIfUserExistTcNo(String tcNo) {
		Customer currentUser = this.customerRepository.findByTcNo(tcNo);
		if (currentUser != null) {
			throw new BusinessException("USER.EXİST");
		}
	}

	@Override
	public DataResult<List<GetAllCustomersResponse>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Customer> customers = this.customerRepository.findAll(pageable).getContent();
		List<GetAllCustomersResponse> response = customers.stream()
				.map(user -> this.mapper.forResponse().map(user, GetAllCustomersResponse.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<GetAllCustomersResponse>>(response);

	}

    //stream api 
	@Override
	public DataResult<List<GetAllCustomersFilterResponse>> getAllFilterCustomers() {
		List<Customer> customers = this.customerRepository.findAll();
		List<GetAllCustomersFilterResponse> response = customers.stream()
				.map(user -> this.mapper.forResponse().map(user, GetAllCustomersFilterResponse.class))
				.filter(user -> (user.getFirstName().contains("a") && (user.getDateOfBirth() > 1990)))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCustomersFilterResponse>>(response);
	}

	@Override
	public DataResult<String> GetCustomerAddressById(ReadCustomerResponse readCustomerResponse) {
		Customer customer = this.mapper.forResponse().map(readCustomerResponse, Customer.class);
		Address address=customer.getAddress();
		String data = address.getAddress();
		return new DataResult<String>(data,true);
	}

	@Override
	public DataResult<String> GetCustomerInvoiceAddressById(ReadCustomerResponse readCustomerResponse) {
		Customer customer = this.mapper.forResponse().map(readCustomerResponse, Customer.class);
		Address address=customer.getAddress();
		String data =address.getInvoiceAddress();
		return new DataResult<String>(data, true);
	}

}
