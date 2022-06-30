package com.kodlamaio.rentACar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.responses.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.FindexServiceAdapter;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	private RentalRepository rentalRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private FindexServiceAdapter adapter;
	private IndividualCustomerRepository individualCustomerRepository;
	private CorporateCustomerRepository corporateCustomerRepository;

	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService, FindexServiceAdapter adapter,
			IndividualCustomerRepository individualCustomerRepository,
			CorporateCustomerRepository corporateCustomerRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.adapter = adapter;
		this.individualCustomerRepository = individualCustomerRepository;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}

	@Override
	public Result addIndividualCustomerRental(
			CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest) {
		checkIfCarExistById(createIndividualCustomerRentalRequest.getCarId()); // kiralanacak arac var mı?
		checkIfIndividualCustomersExistById(createIndividualCustomerRentalRequest.getIndividualCustomerId()); // musteri var mı?
		checkIfCarState(createIndividualCustomerRentalRequest.getCarId()); // arac musait mi
		Rental rental = this.modelMapperService.forRequest().map(createIndividualCustomerRentalRequest, Rental.class);
		IndividualCustomer individualCustomer = this.individualCustomerRepository
				.findById(createIndividualCustomerRentalRequest.getIndividualCustomerId()).get();
		Car car = carRepository.findById(rental.getCar().getId()).get();
		rental.setTotalDays(diffDay(rental.getPickupDate(), rental.getReturnDate())); // tarih farkı
		checkFindexMinValue(car.getMinFindexScore(), individualCustomer.getIdentityNumber()); // findex puan kontrolü
		rental.setTotalPrice(calculateTotalPrice(createIndividualCustomerRentalRequest.getTotalDays(), car, rental));
		rentalRepository.save(rental);
		changeCarState(rental.getCar().getId()); // arac eklendikten sonra durumu degistir
		return new SuccessResult("Rental added successfully");

	}

	@Override
	public Result addCorporateCustomerRental(CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest) {
	   checkIfCarExistById(createCorporateCustomerRentalRequest.getCarId()); // kiralanacak arac var mı?
	   checkIfCorporateCustomersExistById(createCorporateCustomerRentalRequest.getCorporateCustomerId()); // musteri var mı?
	   checkIfCarState(createCorporateCustomerRentalRequest.getCarId()); 
	   Rental rental = this.modelMapperService.forRequest().map(createCorporateCustomerRentalRequest, Rental.class);
//	   CorporateCustomer corporateCustomer	=this.corporateCustomerRepository.findById(createCorporateCustomerRentalRequest.getCorporateCustomerId()).get();
	   Car car = carRepository.findById(rental.getCar().getId()).get();
	   rental.setTotalDays(diffDay(rental.getPickupDate(), rental.getReturnDate())); // tarih farkı  
	   rental.setTotalPrice(calculateTotalPrice(createCorporateCustomerRentalRequest.getTotalDays(), car, rental));
	   rentalRepository.save(rental);
	   changeCarState(rental.getCar().getId()); // arac eklendikten sonra durumu degistir
	   return new SuccessResult("Rental added successfully");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		checkIfRentalsExistById(deleteRentalRequest.getId());
		rentalRepository.deleteById(deleteRentalRequest.getId());
		return new SuccessResult("id: " + deleteRentalRequest.getId() + "rental deleted successfully");
	}

	@Override
	public Result updateIndividualCustomerRental(UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest) {
		checkIfRentalsExistById(updateIndividualCustomerRentalRequest.getId()); //guncellenecek id var mı
		checkIfCarExistById(updateIndividualCustomerRentalRequest.getCarId()); //guncellenecek arac var mı
		checkIfIndividualCustomersExistById(updateIndividualCustomerRentalRequest.getIndividualCustomerId()); //musteri
		Rental rental = this.modelMapperService.forRequest().map(updateIndividualCustomerRentalRequest, Rental.class);
		IndividualCustomer individualCustomer = this.individualCustomerRepository
				.findById(updateIndividualCustomerRentalRequest.getIndividualCustomerId()).get();
     	Car car = this.carRepository.findById(rental.getCar().getId()).get();
		rental.setTotalDays(diffDay(updateIndividualCustomerRentalRequest.getPickupDate(),
				updateIndividualCustomerRentalRequest.getReturnDate()));
	    rental.setTotalPrice(calculateTotalPrice(updateIndividualCustomerRentalRequest.getTotalDays(), car, rental));
		changeCarStateUpdate(updateIndividualCustomerRentalRequest.getId()); // arac degisirse,eski arac durum degistir
		rentalRepository.save(rental);
		changeCarState(rental.getCar().getId()); // yeni eklenen aracın durumu degisir
		return new SuccessResult(
				"id: " + updateIndividualCustomerRentalRequest.getId() + "rental updated successfully");
	}

	@Override
	public Result updateCorporateCustomerRental(UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest) {
		checkIfRentalsExistById(updateCorporateCustomerRentalRequest.getId()); //guncellenecek id var mı
		checkIfCarExistById(updateCorporateCustomerRentalRequest.getCarId()); //guncellenecek arac var mı
		checkIfCorporateCustomersExistById(updateCorporateCustomerRentalRequest.getCorporateCustomerId()); //musteri var mı
		Rental rental = this.modelMapperService.forRequest().map(updateCorporateCustomerRentalRequest, Rental.class);
		CorporateCustomer corporateCustomer	=this.corporateCustomerRepository.findById(updateCorporateCustomerRentalRequest.getCorporateCustomerId()).get();
		Car car = this.carRepository.findById(rental.getCar().getId()).get();
		rental.setTotalDays(diffDay(rental.getPickupDate(), rental.getReturnDate())); // tarih farkı  
		rental.setTotalPrice(calculateTotalPrice(updateCorporateCustomerRentalRequest.getTotalDays(), car, rental));
		changeCarStateUpdate(updateCorporateCustomerRentalRequest.getId()); // arac degisirse,eski arac durum degistir
		rentalRepository.save(rental);
		changeCarState(rental.getCar().getId()); // yeni eklenen aracın durumu degisir
		return new SuccessResult(
				"id: " + updateCorporateCustomerRentalRequest.getId() + "rental updated successfully");
	}

	@Override
	public DataResult<Rental> getById(ReadRentalResponse readRentalResponse) {
		checkIfRentalsExistById(readRentalResponse.getId());
		Rental rental = this.modelMapperService.forResponse().map(readRentalResponse, Rental.class);
		rental = this.rentalRepository.findById(readRentalResponse.getId()).get();
		return new SuccessDataResult<Rental>(rental, " rental listed successfully");
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, GetAllRentalsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllRentalsResponse>>(response, "rentals listed successfully");
	}

	// var olmayan arac kiraya gonderilemez
	private void checkIfCarExistById(int carId) {
		boolean result = carRepository.existsById(carId);
		if (result == false) {
			throw new BusinessException("THE CAR DOESNT EXİST");
		}
	}

	// State bilgisini kontrol eder
	private void checkIfCarState(int carId) {
		Car car = carRepository.findById(carId).get();
		if (!car.getStatusInformation().equals("available")) {
			throw new BusinessException("THE CAR DOESNT READY TO RENT, CHECK CAR STATE");
		}
	}

	// arac alma bırakma sehir kontrolu saglandı
	private boolean checkCarCityState(int rentalId) {
		boolean state = false;
		Rental rental = rentalRepository.findById(rentalId).get();
		if (!rental.getReturnCity().equals(rental.getPickupCity())) {
			state = true;
		} else {
			state = false;
		}
		return state;
	}

	// totalprice ekleme
	private double calculateTotalPrice(int totalDays, Car car, Rental rental) {
		double totalPrice = totalDays * car.getDailyPrice();

		if (!rental.getPickupCity().equals(rental.getReturnCity())) {

			totalPrice += 750;
		}

		return totalPrice;
	}

	// id kontrolü
	private void checkIfRentalsExistById(int id) {
		boolean result = rentalRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("RENTAL NOT EXIST");
		}
	}

	// kiralama işlemi sonrası car state değişir örn; "rented"
	private void changeCarState(int id) {
		Car car = carRepository.findById(id).get();
		car.setStatusInformation("rented");
		carRepository.save(car);
	}

	// kiralama guncellenirken arac degistiyse eski aracı available yap
	private void changeCarStateUpdate(int id) {
		Rental rentalBeforeCarId = rentalRepository.findById(id).get();
		Car car = carRepository.findById(rentalBeforeCarId.getCar().getId()).get();
		car.setStatusInformation("available");
		carRepository.save(car);
	}

	// tarih dogru mu? ve fark al
	private int diffDay(Date pickDate, Date returnDate) {
		long diff = returnDate.getTime() - pickDate.getTime();
		if (diff < 0) {
			throw new BusinessException("CHECK THE DATE");
		} else {
			long time = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			int days = (int) time;
			return days;
		}
	}

	// guncelleme ve eklemede musteri kontrolu
	private void checkIfIndividualCustomersExistById(int id) {
		boolean result = individualCustomerRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("INDIVIDUAL CUSTOMER NOT EXIST");
		}
	}

	// guncelleme ve eklemede sirket kontrolu
	private void checkIfCorporateCustomersExistById(int id) {
		boolean result = corporateCustomerRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("INDIVIDUAL CUSTOMER NOT EXIST");
		}
	}

	// kiralamak için findex score yeterli mi
	public void checkFindexMinValue(int score, String tc) {
		if (adapter.CheckFindexScore(tc) < score) {
			throw new BusinessException("FINDEX SCORE NOT ENOUGHG TO RENT THİS CAR");
		}
	}

}
