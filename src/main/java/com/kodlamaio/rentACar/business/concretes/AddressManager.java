package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.GetAllAddressesResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ReadAddressResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService {

	ModelMapperService modelMapperService;
	AddressRepository addressRepository;
	
	public AddressManager(ModelMapperService modelMapperService, AddressRepository addressRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.addressRepository = addressRepository;
	}

	@Override
	public Result add(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		if(address.getInvoiceAddress() == null || address.getInvoiceAddress() == "") {
			address.setInvoiceAddress(address.getAddress());
		}
		this.addressRepository.save(address);
		return new SuccessResult("ADRES.ADDED");
	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(deleteAddressRequest, Address.class);
		this.addressRepository.delete(address);
		return new SuccessResult("ADRES.DELETED");
	}

	@Override
	public Result update(UpdateAddressRequest updateAddressRequest) {
		Address addressToUpdate = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(addressToUpdate);
		return new SuccessResult("ADRES.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAddressesResponse>> getAll() {
		List<Address> addresses = this.addressRepository.findAll();
		List<GetAllAddressesResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse().map(addresses, GetAllAddressesResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressesResponse>>(response);

	}

	@Override
	public DataResult<Address> getById(ReadAddressResponse readAddressResponse) {
		;
		Address item = this.modelMapperService.forResponse().map(readAddressResponse, Address.class);
		item = addressRepository.findById(readAddressResponse.getId()).get();
		return new SuccessDataResult<Address>(item);
	}
	

}
