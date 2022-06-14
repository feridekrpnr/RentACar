package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.requests.additionalitems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalitems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalitems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalitems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.responses.additionalitems.ReadAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

@RestController
@RequestMapping("api/additionalitems")
public class AdditionalItemsController {
	private AdditionalItemService additionalService;

	public AdditionalItemsController(AdditionalItemService additionalService) {
		this.additionalService = additionalService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalItemRequest createAdditionalItemRequest) {
		additionalService.add(createAdditionalItemRequest);
		return new SuccessResult();
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		additionalService.update(updateAdditionalItemRequest);
		return new SuccessResult();
	}

	@PostMapping("/delete")
	public Result update(@RequestBody DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		additionalService.delete(deleteAdditionalItemRequest);
		return new SuccessResult();
	}

	@GetMapping("/getById")
	public DataResult<AdditionalItem> getbyid(@RequestBody ReadAdditionalItemResponse readAdditionalItemResponse) {

		return this.additionalService.getById(readAdditionalItemResponse);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalItemsResponse>> getall() {

		return this.additionalService.getAll();
	}

}
