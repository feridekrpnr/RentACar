package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.corporatecustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.responses.corporatecustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {

	private CorporateCustomerService corporateCustomerService;

	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

	@PostMapping("/add")
	public Result addCorporateCustomer(@RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return corporateCustomerService.addCorporateCustomer(createCorporateCustomerRequest);
	}
	
    @PostMapping("/delete")
    public Result deleteCorporateCustomer(@RequestBody DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
    	return corporateCustomerService.deleteCorporateCustomer(deleteCorporateCustomerRequest);
    }
    
    @PostMapping("/update")
	public Result updateCorporateCustomer(@RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest){
		return corporateCustomerService.updateCorporateCustomer(updateCorporateCustomerRequest);
	}
    

	@GetMapping("/getById")
	public DataResult<CorporateCustomer> getById(@RequestBody ReadCorporateCustomerResponse readCorporateCustomerResponse) {
		return corporateCustomerService.getById(readCorporateCustomerResponse);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllCorporateCustomersResponse>> GetAll() {
		return corporateCustomerService.getAll();
	}
}
