package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.responses.colors.GetAllColorsResponse;
import com.kodlamaio.rentACar.business.responses.colors.ReadColorResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ModelMapperService mapper;

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = this.mapper.forRequest().map(createColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		Color color = this.mapper.forRequest().map(deleteColorRequest, Color.class);
		this.colorRepository.delete(color);
		return new SuccessResult("COLOR.DELETED");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.mapper.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");

	}

	@Override
	public DataResult<Color> getById(ReadColorResponse readColorResponse) {
		Color item = this.mapper.forResponse().map(readColorResponse, Color.class);		
	    item = this.colorRepository.findById(readColorResponse.getId()).get();
	    return new SuccessDataResult<Color>(item);
	}

	@Override
	public DataResult<List<GetAllColorsResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		List<GetAllColorsResponse> response = colors.stream()
				.map(color -> this.mapper.forResponse().map(color, GetAllColorsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllColorsResponse>>(response);
	}

}
