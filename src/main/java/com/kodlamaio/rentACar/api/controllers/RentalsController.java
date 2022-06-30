package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.responses.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@RestController
@RequestMapping( "/api/rentals")
public class RentalsController {
	private RentalService rentalService;

    public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}

	@PostMapping("/addIndividualCustomerRental")
	public Result addIndividualCustomerRental(@RequestBody CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest) {
    	return rentalService.addIndividualCustomerRental(createIndividualCustomerRentalRequest);
    }
   
	@PostMapping("/addCorporateCustomerRental")
	public Result addCorporateCustomerRental(@RequestBody CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest) {
    	return rentalService. addCorporateCustomerRental(createCorporateCustomerRentalRequest);
    }
	
    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
    	return rentalService.delete(deleteRentalRequest);
    }
    
    @PostMapping("/updateIndividualCustomerRental")
    public Result updateIndividualCustomerRental(@RequestBody UpdateIndividualCustomerRentalRequest updeteIndividualCustomerRentalRequest) {
    	return rentalService.updateIndividualCustomerRental(updeteIndividualCustomerRentalRequest);
    }
    
    @PostMapping("/updateCorporateCustomerRental")
    public Result updateCorporateCustomerRental(@RequestBody UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest) {
    	return rentalService.updateCorporateCustomerRental(updateCorporateCustomerRentalRequest);
    }
    
    @GetMapping("/getById")
   	public DataResult<Rental> getById(@RequestBody ReadRentalResponse readRentalResponse) {
   		return rentalService.getById(readRentalResponse);
   	}
    
    @GetMapping("/getAll")
    public DataResult<List<GetAllRentalsResponse>> getAll() {
    	return rentalService.getAll();
    }
}
