package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.GetAllMaintenancesResponse;
import com.kodlamaio.rentACar.business.responses.maintenances.ReadMaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
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
		if (checkIfCarState(createMaintenanceRequest.getCarId())) {
			Maintenance maintenance = this.modelMapperService.forResponse().map(createMaintenanceRequest, Maintenance.class);
			Car car = this.carRepository.findById(createMaintenanceRequest.getCarId()).get();
			car.setId(createMaintenanceRequest.getCarId());
			car.setStatusInformation("");
			maintenance.setCar(car);
			this.maintenanceRepository.save(maintenance);
			return new SuccessResult("CAR.IS.IN.MAINTENANCE");
		}
		return new ErrorResult("CAR.IS.NOT.IN.MAINTENANCE");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {

		Maintenance maintenance = this.modelMapperService.forRequest().map(deleteMaintenanceRequest, Maintenance.class);
		this.maintenanceRepository.delete(maintenance);
		return new SuccessResult("MAİNTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		// state haric güncelleme yapmak için
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult();
	}

	@Override
	public DataResult<Maintenance> getById(ReadMaintenanceResponse readMaintenanceResponse) {
		Maintenance item = this.modelMapperService.forResponse().map(readMaintenanceResponse, Maintenance.class);
		item = maintenanceRepository.findById(readMaintenanceResponse.getId()).get();
		return new SuccessDataResult<Maintenance>(item);

	}

	@Override
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenancesResponse> response = maintenances.stream()
				.map(maintenance -> this.modelMapperService.forResponse().map(maintenance, GetAllMaintenancesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllMaintenancesResponse>>(response);
	}

	
	private boolean checkIfCarState(int id) {
		Car car = this.carRepository.findById(id).get();
		if (car.getStatusInformation() == "") {
			return true;
		}
		return false;

	}

	

}
