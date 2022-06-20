package com.kodlamaio.rentACar.api.controllers;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.GetAllAddressesResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Address;

@RestController
@RequestMapping("api/addresses")
public class AddressesControllers {
    @Autowired
	AddressService addressService;
	
    @PostMapping("/add")
    public Result add(@RequestBody CreateAddressRequest createAddressRequest) {
    	return this.addressService.add(createAddressRequest);
    }
    
    @PostMapping("/delete")
    public Result delete(@RequestBody DeleteAddressRequest deleteAddressRequest) {
    	return this.addressService.delete(deleteAddressRequest);
    }
    @PostMapping("/update")
    public Result update(@RequestBody UpdateAddressRequest updateAddressRequest) {
    	return this.addressService.update(updateAddressRequest);
    }
    
    @GetMapping("/getById")
    public DataResult<Address> getById(ReadAddressResponse readAddressResponse) {
    	return this.addressService.getById(readAddressResponse);
    	
    }
    
    @GetMapping("getAll")
    public DataResult<List<GetAllAddressesResponse>> getAll() {
    	return this.addressService.getAll();
    }
    
}
