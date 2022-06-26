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
		AdditionalItem addItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(addItem);
		return new SuccessResult(addItem.getName() + " isimli ek özellik başarıyla eklenmiştir.");
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		additionalItemRepository.findById(deleteAdditionalItemRequest.getId());
		return new SuccessResult(deleteAdditionalItemRequest.getId() + "li ek özellik başarıyla silinmiştir.");
	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		AdditionalItem addItemToUpdate = modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		additionalItemRepository.save(addItemToUpdate);
		return new SuccessResult(updateAdditionalItemRequest.getId() + "li ek özellik başarıyla güncellenmiştir.");
	}
	
	@Override
	public DataResult<AdditionalItem> getById(ReadAdditionalItemResponse readAdditionalItemResponse) {
		AdditionalItem item =this.modelMapperService.forResponse().map(readAdditionalItemResponse, AdditionalItem.class);
		item=additionalItemRepository.findById(readAdditionalItemResponse.getId()).get();
		return new SuccessDataResult<AdditionalItem>(item);
		
	}
	
	@Override
	public DataResult<List<GetAllAdditionalItemsResponse>> getAll() {
		List<AdditionalItem> items =this.additionalItemRepository.findAll();
		List<GetAllAdditionalItemsResponse> response =items.stream().map(item->this.modelMapperService.forResponse().map(item, GetAllAdditionalItemsResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAdditionalItemsResponse>>(response);
	}

	

	private void checkIfBrandExistsByName(String name) 
	{
		AdditionalItem currentItem = this.additionalItemRepository.findByName(name);
		if (currentItem!=null) {
			throw new BusinessException("ADDITIONAL ITEM EXİSTS");
		}
		
	}

	

	


}
