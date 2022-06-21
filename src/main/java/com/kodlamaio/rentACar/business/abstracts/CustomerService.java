package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.customers.GetAllCustomersFilterResponse;
import com.kodlamaio.rentACar.business.responses.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.responses.customers.ReadCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Customer;

public interface CustomerService {
	Result add(CreateCustomerRequest createCustomerRequest) throws NumberFormatException, RemoteException;
	Result delete(DeleteCustomerRequest deleteCustomerRequest);
	Result update(UpdateCustomerRequest updateCustomerRequest);
	DataResult<Customer>getById(ReadCustomerResponse readCustomerResponse);
	DataResult<List<GetAllCustomersResponse>> getAll();
	DataResult<List<GetAllCustomersResponse>> getAll(int pageNo,int pageSize);
	DataResult<List<GetAllCustomersFilterResponse>> getAllFilterCustomers();

	
	DataResult<String>GetCustomerAddressById(ReadCustomerResponse readCustomerResponse);
	DataResult<String>GetCustomerInvoiceAddressById(ReadCustomerResponse readCustomerResponse);
}
