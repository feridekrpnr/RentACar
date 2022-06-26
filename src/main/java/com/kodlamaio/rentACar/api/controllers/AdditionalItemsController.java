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
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

@RestController
@RequestMapping("api/additionalitems")
public class AdditionalItemsController {
	
	private AdditionalItemService additionalItemService;

	public AdditionalItemsController(AdditionalItemService additionalItemService) {
		this.additionalItemService = additionalItemService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalItemRequest createAdditionalItemRequest) {
		return this.additionalItemService.add(createAdditionalItemRequest);
	
	}
	

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		return this.additionalItemService.delete(deleteAdditionalItemRequest);
		
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
		
	}


	@GetMapping("/getById")
	public DataResult<AdditionalItem> getById(@RequestBody ReadAdditionalItemResponse readAdditionalItemResponse) {
		return this.additionalItemService.getById(readAdditionalItemResponse);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		return this.additionalItemService.getAll();
	}

}
