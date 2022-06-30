package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.corporatecustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporatecustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.corporatecustomers.GetAllCorporateCustomersResponse;
import com.kodlamaio.rentACar.business.responses.corporatecustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerService {

	Result addCorporateCustomer(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result deleteCorporateCustomer(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	Result updateCorporateCustomer(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	DataResult<CorporateCustomer> getById(ReadCorporateCustomerResponse readCorporateCustomerResponse);
	DataResult<List<GetAllCorporateCustomersResponse>> getAll();
}
