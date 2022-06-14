package com.kodlamaio.rentACar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.responses.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	@Autowired
	private RentalRepository rentalRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private ModelMapperService mapper;

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		if (checkIfCarState(createRentalRequest.getCarId())) {
			Rental rental = this.mapper.forRequest().map(createRentalRequest, Rental.class);
			if (this.checkIfDatesAreCorrect(rental.getPickupDate(), rental.getReturnDate())) {
				long dayDifference = (rental.getReturnDate().getTime() - rental.getPickupDate().getTime());
				long time = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
				Car car = this.carRepository.findById(createRentalRequest.getCarId()).get();
				car.setStatusInformation(3);
				rental.setTotalDays((int) time);
				double totalPrice = car.getDailyPrice() * time;
				rental.setTotalPrice(totalPrice);
				this.rentalRepository.save(rental);
				return new SuccessResult("CAR.RENTED");

			}
		}
		return new ErrorResult("CAR.NOT.RENTED");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = this.mapper.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalRepository.delete(rental);
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.mapper.forRequest().map(updateRentalRequest, Rental.class);
		Car car = this.carRepository.findById(updateRentalRequest.getCarId()).get();

		long dayDifference = (rental.getReturnDate().getTime() - rental.getPickupDate().getTime());
		long time = TimeUnit.DAYS.convert(dayDifference, TimeUnit.MILLISECONDS);
		rental.setTotalDays((int) time);
		double totalPrice = car.getDailyPrice() * time;
		rental.setTotalPrice(totalPrice);
		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED.");

	}

	@Override
	public DataResult<Rental> getById(ReadRentalResponse readRentalResponse) {
		Rental item = this.mapper.forResponse().map(readRentalResponse, Rental.class);
		item = this.rentalRepository.findById(readRentalResponse.getId()).get();
		return new SuccessDataResult<Rental>(item);
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.mapper.forResponse().map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response);
	}

	private boolean checkIfDatesAreCorrect(Date pickUpDate, Date returnDate) {
//		Date date = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    
//		var x = formatter.format(date);
		if (!pickUpDate.before(returnDate)) {
			return false;
		}
		return true;

	}

	private boolean checkIfCarState(int id) {
		Car car = this.carRepository.findById(id).get();
		if (car.getStatusInformation() == 1) {
			return true;
		}
		return false;
	}

}
