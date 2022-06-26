package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalservices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalservices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalservices.GetAllAdditionalServicesResponse;
import com.kodlamaio.rentACar.business.responses.additionalservices.ReadAdditionalServicesResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	DataResult<List<GetAllAdditionalServicesResponse>> getAll();
	DataResult<AdditionalService> getById(ReadAdditionalServicesResponse readAdditionalServicesResponse);
}
