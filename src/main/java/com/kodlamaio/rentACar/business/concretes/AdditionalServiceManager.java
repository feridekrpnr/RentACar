package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalservices.GetAllAdditionalServicesResponse;
import com.kodlamaio.rentACar.business.responses.additionalservices.ReadAdditionalServicesResponse;
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
	
	public AdditionalServiceManager(AdditionalServiceRepository additionalServiceRepository, ModelMapperService modelMapperService,AdditionalItemRepository additionalItemRepository) {
		super();
		this.additionalServiceRepository = additionalServiceRepository;
		this.modelMapperService = modelMapperService;
		this.additionalItemRepository = additionalItemRepository;
	}
	

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		AdditionalItem item = additionalItemRepository.findById(createAdditionalServiceRequest.getAdditionalItemId()).get();
		double price = item.getPrice();
		int day = additionalService.getDay();
		additionalService.setTotalPrice(price*day);
		this.additionalServiceRepository.save(additionalService);
		return new SuccessResult(" id'li ek özellik başarıyla eklenmiştir.");
	}

	

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		additionalServiceRepository.deleteById(deleteAdditionalServiceRequest.getId());
		return new SuccessResult("li ek özellik başarıyla silinmiştir.");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		AdditionalService addServiceToUpdate = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceRepository.save(addServiceToUpdate);
		return new SuccessResult(updateAdditionalServiceRequest.getId() + "li ek özellik başarıyla güncellenmiştir.");
		
		
	}

	@Override
	public DataResult<AdditionalService> getById(ReadAdditionalServicesResponse readAdditionalServicesResponse) {
		AdditionalService item = this.modelMapperService.forResponse().map(readAdditionalServicesResponse, AdditionalService.class);
		item = additionalServiceRepository.findById(readAdditionalServicesResponse.getId());
		return new SuccessDataResult<AdditionalService>(item);
	}

	@Override
	public DataResult<List<GetAllAdditionalServicesResponse>> getAll() {
		List<AdditionalService> items = this.additionalServiceRepository.findAll();
		List<GetAllAdditionalServicesResponse> response = items.stream()
				.map(item -> this.modelMapperService.forResponse().map(item, GetAllAdditionalServicesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalServicesResponse>>(response);
	}
}
