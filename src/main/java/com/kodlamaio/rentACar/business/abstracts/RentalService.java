package com.kodlamaio.rentACar.business.abstracts;


import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentals.CreateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.CreateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateCorporateCustomerRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateIndividualCustomerRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.kodlamaio.rentACar.business.responses.rentals.ReadRentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public interface RentalService {
	
	
	Result addIndividualCustomerRental(CreateIndividualCustomerRentalRequest createIndividualCustomerRentalRequest);
	Result addCorporateCustomerRental(CreateCorporateCustomerRentalRequest createCorporateCustomerRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result updateIndividualCustomerRental(UpdateIndividualCustomerRentalRequest updateIndividualCustomerRentalRequest);
	Result updateCorporateCustomerRental(UpdateCorporateCustomerRentalRequest updateCorporateCustomerRentalRequest);
	DataResult<Rental> getById(ReadRentalResponse readRentalResponse);
	DataResult<List<GetAllRentalsResponse>> getAll();
}
