package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.GetAllCarsMinFindexScoreResponse;
import com.kodlamaio.rentACar.business.responses.cars.GetAllCarsResponse;
import com.kodlamaio.rentACar.business.responses.cars.ReadCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Car;

@RestController
@RequestMapping("/api/cars")
public class CarsControllers {

	private CarService carService;
	

	public CarsControllers(CarService carService) {
		super();
		this.carService = carService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
	
		return carService.add(createCarRequest);
		
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}

	
	
	@GetMapping("/getById")
	public DataResult<Car> getById(@RequestBody ReadCarResponse readCarResponse) {
		return this.carService.getById(readCarResponse);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetAllCarsResponse>> getAll() {
		return this.carService.getAll();
	}
	@GetMapping("/getAllFindex")
	public DataResult<List<GetAllCarsMinFindexScoreResponse>> getAllFindex() {
		return this.carService.getAllFindex();
	}
}
