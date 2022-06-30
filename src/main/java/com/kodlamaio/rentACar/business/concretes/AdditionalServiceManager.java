package com.kodlamaio.rentACar.business.concretes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalservices.GetAllAdditionalServicesResponse;
import com.kodlamaio.rentACar.business.responses.additionalservices.ReadAdditionalServicesResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	private AdditionalServiceRepository additionalServiceRepository;
	private ModelMapperService modelMapperService;
	private AdditionalItemRepository additionalItemRepository;

	public AdditionalServiceManager(AdditionalServiceRepository additionalServiceRepository,
			ModelMapperService modelMapperService, AdditionalItemRepository additionalItemRepository) {
		super();
		this.additionalServiceRepository = additionalServiceRepository;
		this.modelMapperService = modelMapperService;
		this.additionalItemRepository = additionalItemRepository;
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		checkIfAdditionalItemExistById(createAdditionalServiceRequest.getAdditionalItemId());
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		AdditionalItem additionalItem = additionalItemRepository
				.findById(createAdditionalServiceRequest.getAdditionalItemId()).get();
		additionalService.setDay(
				diffDay(createAdditionalServiceRequest.getSendDate(), createAdditionalServiceRequest.getReturnDate()));
		additionalService.setTotalPrice(additionalItem.getPrice() * additionalService.getDay());
		additionalServiceRepository.save(additionalService);
		return new SuccessResult("Additional Service added successfully");
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		checkIfAdditionalServiceExistById(deleteAdditionalServiceRequest.getId());
		additionalServiceRepository.deleteById(deleteAdditionalServiceRequest.getId());
		return new SuccessResult("id: " + deleteAdditionalServiceRequest.getId() + " deleted successfully");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		checkIfAdditionalItemExistById(updateAdditionalServiceRequest.getAdditionalItemId());
		checkIfAdditionalServiceExistById(updateAdditionalServiceRequest.getId());
		
		AdditionalService addServiceToUpdate = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceRepository.save(addServiceToUpdate);
		return new SuccessResult(updateAdditionalServiceRequest.getId() + " updated succesffully");
		
		
	}

	@Override
	public DataResult<AdditionalService> getById(ReadAdditionalServicesResponse readAdditionalServicesResponse) {
		AdditionalService additionalService = this.modelMapperService.forResponse().map(readAdditionalServicesResponse,
				AdditionalService.class);
		additionalService = additionalServiceRepository.findById(readAdditionalServicesResponse.getId()).get();
		return new SuccessDataResult<AdditionalService>(additionalService, "additional service listed successfully");
	}

	@Override
	public DataResult<List<GetAllAdditionalServicesResponse>> getAll() {
		List<AdditionalService> items = this.additionalServiceRepository.findAll();
		List<GetAllAdditionalServicesResponse> response = items.stream()
				.map(item -> this.modelMapperService.forResponse().map(item, GetAllAdditionalServicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalServicesResponse>>(response,
				"additonal services listed successfully");
	}

	// eklenecek additionalıtem var mı?
	private void checkIfAdditionalItemExistById(int id) {
		boolean result = additionalItemRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("ADDITIONAL ITEM NOT EXİST");
		}
	}

	// additionalservice id var mı?
	private void checkIfAdditionalServiceExistById(int id) {
		boolean result = additionalServiceRepository.existsById(id);
		if (result == false) {
			throw new BusinessException("ADDITIONAL SERVICE NOT EXİST");
		}
	}

	// tarihler dogru mu?
	private int diffDay(Date sendDate, Date returnDate) {
		long diff = returnDate.getTime() - sendDate.getTime();
		if (diff < 0) {
			throw new BusinessException("CHECK THE DATE");
		} else {
			long time = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			int days = (int) time;
			return days;
		}

	}

}
