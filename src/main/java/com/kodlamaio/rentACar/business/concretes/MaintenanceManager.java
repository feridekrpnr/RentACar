package com.kodlamaio.rentACar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.GetAllMaintenancesResponse;
import com.kodlamaio.rentACar.business.responses.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {

	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository,
			ModelMapperService modelMapperService) {
		super();
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {
		checkIfCarExistById(createMaintenanceRequest.getCarId());
		checkIfCarState(createMaintenanceRequest.getCarId());
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		maintenance.equals(diffDay(maintenance.getSendDate(), maintenance.getReturnDate()));
		maintenanceRepository.save(maintenance);
		changeCarState(maintenance.getCar().getId());
		return new SuccessResult("Maintenance added successfully");

	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		checkIfMaintenancesExistById(deleteMaintenanceRequest.getId());
		maintenanceRepository.deleteById(deleteMaintenanceRequest.getId());
		return new SuccessResult("id: " + deleteMaintenanceRequest.getId() + "maintenance deleted successfully");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		checkIfMaintenancesExistById(updateMaintenanceRequest.getId());
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("Maintenance updated succesfully");
	}

	@Override
	public DataResult<Maintenance> getById(ReadMaintenanceResponse readMaintenanceResponse) {
		checkIfMaintenancesExistById(readMaintenanceResponse.getId());
		Maintenance maintenance = this.modelMapperService.forResponse().map(readMaintenanceResponse, Maintenance.class);
		maintenance = maintenanceRepository.findById(readMaintenanceResponse.getId()).get();
		return new SuccessDataResult<Maintenance>(maintenance, " maintenance listed successfully");

	}

	@Override
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenancesResponse> response = maintenances.stream().map(
				maintenance -> this.modelMapperService.forResponse().map(maintenance, GetAllMaintenancesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenancesResponse>>(response, " maintenances listed successfully");
	}

	// bakıma gonderilecek arac var mı?
	private void checkIfCarExistById(int carId) {
		boolean result = carRepository.existsById(carId);
		if (result == false) {
			throw new BusinessException("CAR DOES NOT EXIST");
		}
	}

	// state bilgisini kontrol et
	private void checkIfCarState(int carId) {
		Car car = this.carRepository.findById(carId).get();
		if (!car.getStatusInformation().equals("available")) {
			throw new BusinessException("Maintenance the car not available");
		}
	}

	// bakım eklendiginde araba state gunceller
	private void changeCarState(int id) {
		Car car = carRepository.findById(id).get();
		car.setStatusInformation("maintenance");
		carRepository.save(car);
	}

	// maintenance var mı?
	private void checkIfMaintenancesExistById(int id) {
		boolean result = maintenanceRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("MAINTENANCE NOT EXİST");
		}
	}

	// tarih dogru verildi mi?
	private int diffDay(Date sentDate, Date returnDate) {
		long diff = returnDate.getTime() - sentDate.getTime();
		if (diff < 0) {
			throw new BusinessException("CHECK THE DATE");
		} else {
			long time = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			int days = (int) time;
			return days;
		}
	}
}
