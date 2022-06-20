package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentalDetails.CreateRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.DeleteRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.UpdateRentalDetailRequest;
import com.kodlamaio.rentACar.business.responses.rentalDetails.GetAllRentalDetailsResponse;
import com.kodlamaio.rentACar.business.responses.rentalDetails.ReadRentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.RentalDetail;

public interface RentalDetailService {
	Result add(CreateRentalDetailRequest createRentalDetailRequest);
	Result delete(DeleteRentalDetailRequest deleteRentalDetailRequest);
	Result update(UpdateRentalDetailRequest updateRentalDetailRequest);
	DataResult<RentalDetail> getById(ReadRentalDetailResponse readRentalDetailResponse);
	DataResult<List<GetAllRentalDetailsResponse>>getAll();
}
