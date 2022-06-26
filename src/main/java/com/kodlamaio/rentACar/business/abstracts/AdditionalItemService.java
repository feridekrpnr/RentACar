package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalitems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalitems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalitems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalitems.GetAllAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.responses.additionalitems.ReadAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

public interface AdditionalItemService {
	
	Result add(CreateAdditionalItemRequest createAdditionalItemRequest);
	Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest);
	Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest);
	DataResult<List<GetAllAdditionalItemsResponse>> getAll();
	DataResult<AdditionalItem> getById(ReadAdditionalItemResponse readAdditionalItemResponse);
}
