package com.kodlamaio.rentACar.business.concretes;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.requests.additionalitems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalitems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalitems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalitems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.responses.additionalitems.ReadAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;


@Service
public class AdditionalItemManager implements AdditionalItemService {
	
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	
	public AdditionalItemManager(AdditionalItemRepository additionalItemRepository, ModelMapperService modelMapperService) {
		super();
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
		checkIfBrandExistsByName(createAdditionalItemRequest.getName());
		AdditionalItem addditionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		additionalItemRepository.save(addditionalItem);
		return new SuccessResult(addditionalItem.getName() + " added successfully");
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		checkIfAdditionalItemExistById(deleteAdditionalItemRequest.getId());
		additionalItemRepository.deleteById(deleteAdditionalItemRequest.getId());
		return new SuccessResult(deleteAdditionalItemRequest.getId() + " deleted sccessfully");
	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		checkIfAdditionalItemExistById(updateAdditionalItemRequest.getId());
		AdditionalItem additonalItem = modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		additionalItemRepository.save(additonalItem);
		return new SuccessResult(updateAdditionalItemRequest.getId() + " updated successfullly");
	}
	
	@Override
	public DataResult<AdditionalItem> getById(ReadAdditionalItemResponse readAdditionalItemResponse) {
		checkIfAdditionalItemExistById(readAdditionalItemResponse.getId());
		AdditionalItem addditionalItem =this.modelMapperService.forResponse().map(readAdditionalItemResponse, AdditionalItem.class);
		addditionalItem=additionalItemRepository.findById(readAdditionalItemResponse.getId()).get();
		return new SuccessDataResult<AdditionalItem>(addditionalItem, "  the additional item was successfully listed ");
		
	}
	
	@Override
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		List<AdditionalItem> items =this.additionalItemRepository.findAll();
		List<GetAllAdditionalItemsResponse> response =items.stream().map(item->this.modelMapperService.forResponse().map(item, GetAllAdditionalItemsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemsResponse>>(response, " listed successfully ");
	}

	

	private void checkIfBrandExistsByName(String name) {
		AdditionalItem currentItem = this.additionalItemRepository.findByName(name);
		if (currentItem!=null) {
			throw new BusinessException("ADDITIONAL ITEM EXİST");
		}
		
	}
	
	private void checkIfAdditionalItemExistById(int id) {
		boolean result = additionalItemRepository.existsById(id);
		if(result== false) {
			throw new BusinessException("ADDITIONAL ITEM NOT EXİST");
		}
	}

	

	


}
