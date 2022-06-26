package com.kodlamaio.rentACar.business.abstracts;


import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.responses.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public interface RentalService {
	Result add(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	DataResult<Rental> getById(ReadRentalResponse readRentalResponse);
	DataResult<List<GetAllRentalsResponse>> getAll();
}
