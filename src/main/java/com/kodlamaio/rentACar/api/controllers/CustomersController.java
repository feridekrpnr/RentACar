//package com.kodlamaio.rentACar.api.controllers;
//
//import java.rmi.RemoteException;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
//import com.kodlamaio.rentACar.business.requests.individualcustomers.CreateIndividualCustomerRequest;
//import com.kodlamaio.rentACar.business.requests.individualcustomers.DeleteIndividualCustomerRequest;
//import com.kodlamaio.rentACar.business.requests.individualcustomers.UpdateIndividualCustomerRequest;
//import com.kodlamaio.rentACar.business.responses.individualcustomers.GetAllIndividualCustomersFilterResponse;
//import com.kodlamaio.rentACar.business.responses.individualcustomers.GetAllIndividualCustomersResponse;
//import com.kodlamaio.rentACar.business.responses.individualcustomers.ReadIndividualCustomerResponse;
//import com.kodlamaio.rentACar.core.utilities.results.DataResult;
//import com.kodlamaio.rentACar.core.utilities.results.Result;
//import com.kodlamaio.rentACar.entities.concretes.Customer;
//
//@RestController
//@RequestMapping("/api/customers")
//public class CustomersController {
//
//	private IndividualCustomerService individualCustomerService;
//	
//
//	public CustomersController(IndividualCustomerService individualCustomerService) {
//		super();
//		this.individualCustomerService = individualCustomerService;
//	}
//
//	@PostMapping("/add")
//	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest)
//			throws NumberFormatException, RemoteException {
//		return this.individualCustomerService.add(createIndividualCustomerRequest);
//	}
//
//	@PostMapping("/delete")
//	public Result delete(@RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
//		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
//	}
//
//	@PostMapping("/update")
//	public Result update(@RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
//		return this.individualCustomerService.update(updateIndividualCustomerRequest);
//	}
//
//	@GetMapping("/getById")
//	public DataResult<Customer> getById(@RequestBody ReadIndividualCustomerResponse readIndividualCustomerResponse) {
//		return this.individualCustomerService.getById(readIndividualCustomerResponse);
//	}
//
//	@GetMapping("/getAll")
//	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
//		return this.individualCustomerService.getAll();
//	}
//
//	@GetMapping("/getAllByPage")
//	DataResult<List<GetAllIndividualCustomersResponse>> getAllByPage(int pageNo, int pageSize) {
//		return this.individualCustomerService.getAllByPage(pageNo, pageSize);
//	}
//
//	@GetMapping("/getAllFilterCustomers")
//	DataResult<List<GetAllIndividualCustomersFilterResponse>> getAllFilterCustomers() {
//		return this.individualCustomerService.getAllFilterCustomers();
//	}
//	@GetMapping("/getCustomerAddressById")
//	DataResult<String>getCustomerAddressById(ReadIndividualCustomerResponse readIndividualCustomerResponse) {
//		return this.individualCustomerService.getCustomerAddressById(readIndividualCustomerResponse);
//	}
//	@GetMapping("/getCustomerInvoiceAddressById")
//	DataResult<String>getCustomerInvoiceAddressById(ReadIndividualCustomerResponse readIndividualCustomerResponse) {
//		return this.individualCustomerService.getCustomerInvoiceAddressById(readIndividualCustomerResponse);
//	}
//}
