package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualcustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualcustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualcustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.individualcustomers.GetAllIndividualCustomersFilterResponse;
import com.kodlamaio.rentACar.business.responses.individualcustomers.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentACar.business.responses.individualcustomers.ReadIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {
	private IndividualCustomerService individualCustomerService;

	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("/add")
	public Result addIndividualCustomer(@RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		return this.individualCustomerService.addIndividualCustomer(createIndividualCustomerRequest);
	}

	@PostMapping("/delete")
	public Result deleteIndividualCustomer(@RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.deleteIndividualCustomer(deleteIndividualCustomerRequest);
	}

	@PostMapping("/update")
	public Result updateIndividualCustomer(@RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NumberFormatException, RemoteException {
		return this.individualCustomerService.updateIndividualCustomer(updateIndividualCustomerRequest);

	}

	@GetMapping("/getById")
	public DataResult<IndividualCustomer> getById(@RequestBody ReadIndividualCustomerResponse readIndividualCustomerResponse) {
		return this.individualCustomerService.getById(readIndividualCustomerResponse);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
		return this.individualCustomerService.getAll();
	}

	@GetMapping("/getAllByPage")
	DataResult<List<GetAllIndividualCustomersResponse>> getAllByPage(int pageNo, int pageSize) {
		return this.individualCustomerService.getAllByPage(pageNo, pageSize);
	}

	@GetMapping("/getAllFilter")
	DataResult<List<GetAllIndividualCustomersFilterResponse>> getAllFilterIndividualCustomers() {
		return this.individualCustomerService.getAllFilterIndividualCustomers();
	}
	
	
}