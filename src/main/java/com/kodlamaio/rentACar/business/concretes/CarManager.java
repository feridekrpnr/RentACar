package com.kodlamaio.rentACar.business.concretes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.GetAllCarsMinFindexScoreResponse;
import com.kodlamaio.rentACar.business.responses.cars.GetAllCarsResponse;
import com.kodlamaio.rentACar.business.responses.cars.ReadCarResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ModelMapperService mapper;

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		checkBrandCount(createCarRequest.getBrandId());
		Car car = this.mapper.forRequest().map(createCarRequest, Car.class);
		car.setStatusInformation(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = this.mapper.forRequest().map(deleteCarRequest, Car.class);
		this.carRepository.delete(car);
		return new SuccessResult("CAR.DELETED");

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Car car = this.mapper.forRequest().map(updateCarRequest, Car.class);

		this.carRepository.save(car);
		return new SuccessResult("CAR.UPDATED");

	}

	@Override
	public DataResult<Car> getById(ReadCarResponse readCarResponse) {
		Car item = this.mapper.forResponse().map(readCarResponse, Car.class);
		item = carRepository.findById(readCarResponse.getId()).get();
		return new SuccessDataResult<Car>(item);
	}

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream()
				.map(car -> this.mapper.forResponse().map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarsResponse>>(response);
	}

	private void checkBrandCount(int id) {

		List<Car> cars = this.carRepository.getByBrandId(id);
		if (cars.size() > 4) {
			throw new BusinessException("ERROR");
		}
	}

	@Override
	public DataResult<List<GetAllCarsMinFindexScoreResponse>> getAllFindex() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsMinFindexScoreResponse> response = cars.stream()
				.map(car-> this.mapper.forResponse().map(car, GetAllCarsMinFindexScoreResponse.class))
				.sorted(Comparator.comparing(GetAllCarsMinFindexScoreResponse::getMinFindexScore)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<GetAllCarsMinFindexScoreResponse>>(response);
	}

	

	

}
