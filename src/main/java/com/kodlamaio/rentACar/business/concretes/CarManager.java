package com.kodlamaio.rentACar.business.concretes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private BrandRepository brandRepository;
	private ColorRepository colorRepository;

	
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService,
			BrandRepository brandRepository, ColorRepository colorRepository) {
		super();
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.brandRepository = brandRepository;
		this.colorRepository = colorRepository;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		checkIfColorExistById(createCarRequest.getColorId());
		checkIfBrandExistById(createCarRequest.getBrandId());
		checkBrandCount(createCarRequest.getBrandId());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		carRepository.save(car);
		return new SuccessResult("Car added successfully");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		checkIfCarExistById(deleteCarRequest.getId());
		carRepository.deleteById(deleteCarRequest.getId());
		return new SuccessResult("id: " + deleteCarRequest.getId()+ " deleted successfully");

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCarExistById(updateCarRequest.getId());
		checkIfColorExistById(updateCarRequest.getColorId());
		checkIfBrandExistById(updateCarRequest.getBrandId());
		checkBrandCount(updateCarRequest.getBrandId());
		Brand brand =brandRepository.findById(updateCarRequest.getBrandId()).get();
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		if (brand.getId() == updateCarRequest.getBrandId()) {
			this.carRepository.save(car);
			return new SuccessResult("Car updated succesfully");
		}
		return new ErrorResult("There can be no more than five cars of the same brand");
		

	}

	@Override
	public DataResult<Car> getById(ReadCarResponse readCarResponse) {
		checkIfCarExistById(readCarResponse.getId());
		Car car = this.modelMapperService.forResponse().map(readCarResponse, Car.class);
		car = carRepository.findById(readCarResponse.getId()).get();
		return new SuccessDataResult<Car>(car, "the car was successfully listed ");
	}

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarsResponse>>(response, " cars was successfully listed ");
	}

	@Override
	public DataResult<List<GetAllCarsMinFindexScoreResponse>> getAllFindex() {
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsMinFindexScoreResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, GetAllCarsMinFindexScoreResponse.class))
				.sorted(Comparator.comparing(GetAllCarsMinFindexScoreResponse::getMinFindexScore))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllCarsMinFindexScoreResponse>>(response, " cars findex score was successfully listed ");
	}

	private void checkBrandCount(int id) {
		List<Car> cars = this.carRepository.getByBrandId(id);
		if (cars.size() > 4) {
			throw new BusinessException("THERE CAN BE NO MORE THAN FIVE CARS OF THE SAME BRAND");
		}
	}
	
	private void checkIfCarExistById(int id) {
		boolean result = carRepository.existsById(id);
		if(result == false) {
			throw new BusinessException("CAR NOT EXIST");
		}
	}

	private void checkIfBrandExistById(int id) {
		boolean result = brandRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("CAR NOT EXIST");
		}
	}
	
	private void checkIfColorExistById(int id) {
		boolean result =colorRepository.existsById(id);
		if( result == false) {
			throw new BusinessException("BRAND NOT EXIST");
		}
	}
}
