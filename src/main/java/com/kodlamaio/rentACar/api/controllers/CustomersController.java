package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CustomerService;
import com.kodlamaio.rentACar.business.requests.customers.CreateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.DeleteCustomerRequest;
import com.kodlamaio.rentACar.business.requests.customers.UpdateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.customers.GetAllCustomersFilterResponse;
import com.kodlamaio.rentACar.business.responses.customers.GetAllCustomersResponse;
import com.kodlamaio.rentACar.business.responses.customers.ReadCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	private CustomerService customerService;
	

	public CustomersController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest)
			throws NumberFormatException, RemoteException {
		return this.customerService.add(createCustomerRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCustomerRequest deleteCustomerRequest) {
		return this.customerService.delete(deleteCustomerRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
		return this.customerService.update(updateCustomerRequest);
	}

	@GetMapping("/getById")
	public DataResult<Customer> getById(@RequestBody ReadCustomerResponse readCustomerResponse) {
		return this.customerService.getById(readCustomerResponse);
	}

	@GetMapping("/getAll")

	public DataResult<List<GetAllCustomersResponse>> getAll() {
		return this.customerService.getAll();
	}

	@GetMapping("/getAllByPage")
	DataResult<List<GetAllCustomersResponse>> getAllByPage(int pageNo, int pageSize) {
		return this.customerService.getAllByPage(pageNo, pageSize);
	}

	@GetMapping("/getAllFilterCustomers")
	DataResult<List<GetAllCustomersFilterResponse>> getAllFilterCustomers() {
		return this.customerService.getAllFilterCustomers();
	}
	@GetMapping("/getCustomerAddressById")
	DataResult<String>getCustomerAddressById(ReadCustomerResponse readCustomerResponse) {
		return this.customerService.getCustomerAddressById(readCustomerResponse);
	}
	@GetMapping("/getCustomerInvoiceAddressById")
	DataResult<String>getCustomerInvoiceAddressById(ReadCustomerResponse readCustomerResponse) {
		return this.customerService.getCustomerInvoiceAddressById(readCustomerResponse);
	}
}
