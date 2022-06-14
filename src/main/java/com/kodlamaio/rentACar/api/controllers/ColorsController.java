package com.kodlamaio.rentACar.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@Autowired
	private ColorService colorService;
	

	
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) {
		
		return colorService.add(createColorRequest);
		
	}
	@GetMapping("/getById")
	public DataResult<Color>getbyid(@RequestBody ReadColorResponse readColorResponse) {
		return this.colorService.getById(readColorResponse);
	}
	@PostMapping("/delete")
	public void delete(@RequestBody DeleteColorRequest deleteColorRequest) {
		
		this.colorService.delete(deleteColorRequest);
		
	}
	@PostMapping("/update")
	public void update(@RequestBody UpdateColorRequest updtaColorRequest) {
		this.colorService.update(updtaColorRequest);
	}
	@GetMapping("/getAll")
	public DataResult<List<GetAllColorsResponse>> getAll() {
		return this.colorService.getAll();
	}
	
}
