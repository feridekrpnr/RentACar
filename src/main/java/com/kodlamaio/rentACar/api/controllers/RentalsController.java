package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.responses.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@RestController
@RequestMapping( "/api/rentals")
public class RentalsController {
	@Autowired
	private RentalService rentalService;

   

    @PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
    	return this.rentalService.add(createRentalRequest);
    }
   
    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
    	return this.rentalService.delete(deleteRentalRequest);
    }
    
    @PostMapping("/update")
    public Result update(@RequestBody UpdateRentalRequest updeteRentalRequest) {
    	return this.rentalService.update(updeteRentalRequest);
    }
    
    @GetMapping("/getById")
   	public DataResult<Rental> getById(@RequestBody ReadRentalResponse readRentalResponse) {
   		return this.rentalService.getById(readRentalResponse);
   	}
    
    @GetMapping("/getAll")
    public DataResult<List<GetAllRentalsResponse>> getAll() {
    	return this.rentalService.getAll();
    }
}
