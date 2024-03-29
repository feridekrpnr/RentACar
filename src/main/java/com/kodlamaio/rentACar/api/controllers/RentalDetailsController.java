package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalDetailService;
import com.kodlamaio.rentACar.business.requests.rentalDetails.CreateRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.DeleteRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.UpdateRentalDetailRequest;
import com.kodlamaio.rentACar.business.responses.rentalDetails.GetAllRentalDetailsResponse;
import com.kodlamaio.rentACar.business.responses.rentalDetails.ReadRentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.RentalDetail;

@RestController
@RequestMapping("/api/rentaldetails")
public class RentalDetailsController {
	@Autowired
	RentalDetailService rentalDetailService;
	
	@PostMapping("/add")
	private Result add(@RequestBody CreateRentalDetailRequest createRentalDetailRequest) {

		return this.rentalDetailService.add(createRentalDetailRequest);
	}
	@PostMapping("/delete")
	private Result delete(@RequestBody DeleteRentalDetailRequest deleteRentalDetailRequest) {
		return this.rentalDetailService.delete(deleteRentalDetailRequest);
	}
	@PostMapping("/update")
	private Result update(@RequestBody UpdateRentalDetailRequest updateRentalDetailRequest) {
		return this.rentalDetailService.update(updateRentalDetailRequest);
	}
	
	@GetMapping("/getById")
	private DataResult<RentalDetail> getById(ReadRentalDetailResponse readRentalDetailResponse) {
		return this.rentalDetailService.getById(readRentalDetailResponse);
	}
	
	@GetMapping("/getAll")
	private DataResult<List<GetAllRentalDetailsResponse>> getAll() {
		return this.rentalDetailService.getAll();
	}

}
