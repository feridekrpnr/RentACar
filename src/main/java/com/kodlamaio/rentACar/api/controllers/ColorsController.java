package com.kodlamaio.rentACar.api.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.responses.colors.GetAllColorsResponse;
import com.kodlamaio.rentACar.business.responses.colors.ReadColorResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Color;
@RestController 
@RequestMapping("/api/colors")
public class ColorsController {
	
	
	private ColorService colorService;
	
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		
		return colorService.add(createColorRequest);
		
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteColorRequest deleteColorRequest) {
		
		return colorService.delete(deleteColorRequest);
		
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateColorRequest updtaColorRequest) {
		return colorService.update(updtaColorRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<Color>getById(@RequestBody ReadColorResponse readColorResponse) {
		return colorService.getById(readColorResponse);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllColorsResponse>> getAll() {
		return colorService.getAll();
	}
	
}
