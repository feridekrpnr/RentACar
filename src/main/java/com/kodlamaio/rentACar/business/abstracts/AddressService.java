package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.GetAllAddressesResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Address;

public interface AddressService {
    Result add(CreateAddressRequest createAddressRequest);
    Result delete(DeleteAddressRequest deleteAddressRequest);
    Result update(UpdateAddressRequest updateAddressRequest);
    DataResult<Address> getById(ReadAddressResponse readAddressResponse);
    DataResult<List<GetAllAddressesResponse>>getAll();
   
}
