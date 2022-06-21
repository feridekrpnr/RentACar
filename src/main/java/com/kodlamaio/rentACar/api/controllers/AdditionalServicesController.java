package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalservices.GetAllAdditionalServicesResponse;
import com.kodlamaio.rentACar.business.responses.additionalservices.ReadAdditionalServicesResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

@RestController
@RequestMapping("api/additionalservices")
public class AdditionalServicesController {
	private AdditionalServiceService additionalService;

	public AdditionalServicesController(AdditionalServiceService additionalService) {
		this.additionalService = additionalService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalServiceRequest additionalServiceRequest) {
		additionalService.add(additionalServiceRequest);
		return new SuccessResult();
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		additionalService.update(updateAdditionalServiceRequest);
		return new SuccessResult();
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		additionalService.delete(deleteAdditionalServiceRequest);
		return new SuccessResult();
	}

	@GetMapping("/getById")
	public DataResult<AdditionalService> getbyid(@RequestBody ReadAdditionalServicesResponse readAdditionalServicesResponse) {
		
		return this.additionalService.getById(readAdditionalServicesResponse);
		
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalServicesResponse>> getall() {

		return this.additionalService.getAll();
		
	}

}
