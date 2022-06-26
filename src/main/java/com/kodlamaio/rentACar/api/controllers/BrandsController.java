package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.responses.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.responses.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Brand;

@RestController // anatosyon bir sınıf bir değişkenin çalışma anında imzası gibidir
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;

	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("/sayhello") // endpoint:uc nokta
	public String sayHello() {
		return "Hello Spring";
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
		return brandService.add(createBrandRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		return brandService.delete(deleteBrandRequest);

	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return brandService.update(updateBrandRequest);

	}
	
	@GetMapping("/getById")
	public DataResult<Brand> getById(@RequestBody ReadBrandResponse readBrandResponse) {
		return brandService.getById(readBrandResponse);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		return brandService.getAll();
	}

}
