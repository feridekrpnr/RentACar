package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalDetailService;
import com.kodlamaio.rentACar.business.requests.rentalDetails.CreateRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.DeleteRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.UpdateRentalDetailRequest;
import com.kodlamaio.rentACar.business.responses.rentalDetails.GetAllRentalDetailsResponse;
import com.kodlamaio.rentACar.business.responses.rentalDetails.ReadRentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.RentalDetail;
@Service
public class RentalDetailManager implements RentalDetailService {
	@Autowired
	RentalDetailRepository rentalDetailRepository;
	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	AdditionalServiceRepository additionalServiceRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Override
	public Result add(CreateRentalDetailRequest createRentalDetailRequest) {
		RentalDetail rentalDetail = this.modelMapperService.forRequest().map(createRentalDetailRequest,
				RentalDetail.class);
		AdditionalService additionalService = this.additionalServiceRepository
				.findById(createRentalDetailRequest.getAdditionalServiceId());
		Rental rental = this.rentalRepository.findById(createRentalDetailRequest.getRentalId());

		rentalDetail.setAdditionalService(additionalService);
		rentalDetail.setRental(rental);

		double sum = additionalService.getTotalPrice() + rental.getTotalPrice();

		rentalDetail.setSumTotalPrice(sum);

		this.rentalDetailRepository.save(rentalDetail);

		return new SuccessResult("added");
	}

	@Override
	public Result delete(DeleteRentalDetailRequest deleteRentalDetailRequest) {
		this.rentalDetailRepository.deleteById(deleteRentalDetailRequest.getId());
		return new SuccessResult("deleted");
	}

	@Override
	public Result update(UpdateRentalDetailRequest updateRentalDetailRequest) {
		RentalDetail rentalDetail = this.modelMapperService.forRequest().map(updateRentalDetailRequest,
				RentalDetail.class);

		AdditionalService additionalService = this.additionalServiceRepository
				.findById(updateRentalDetailRequest.getAdditionalServiceId());
		Rental rental = this.rentalRepository.findById(updateRentalDetailRequest.getRentalId());

		rentalDetail.setAdditionalService(additionalService);
		rentalDetail.setRental(rental);
		double sum = additionalService.getTotalPrice() + rental.getTotalPrice();

		rentalDetail.setSumTotalPrice(sum);
		this.rentalDetailRepository.save(rentalDetail);

		return new SuccessResult("updated");
	}

	@Override
	public DataResult<RentalDetail> getById(ReadRentalDetailResponse readRentalDetailResponse) {
		RentalDetail readRentalDetailResponseToGet= this.modelMapperService.forResponse().map(readRentalDetailResponse,
				RentalDetail.class);
		
		readRentalDetailResponseToGet = rentalDetailRepository.findById(readRentalDetailResponse.getId()).get();
		return new SuccessDataResult<RentalDetail>(readRentalDetailResponseToGet);
	}

	@Override
	public DataResult<List<GetAllRentalDetailsResponse>> getAll() {
		List<RentalDetail> rentalDetails = this.rentalDetailRepository.findAll();

		List<GetAllRentalDetailsResponse> response = rentalDetails.stream()
				.map(rentalDetail -> this.modelMapperService.forResponse().map(rentalDetail, GetAllRentalDetailsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllRentalDetailsResponse>>(response);
	}
}
