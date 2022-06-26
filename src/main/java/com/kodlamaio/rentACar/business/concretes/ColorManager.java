package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.responses.colors.GetAllColorsResponse;
import com.kodlamaio.rentACar.business.responses.colors.ReadColorResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;
	
	
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		super();
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		checkIfColorExistByName(createColorRequest.getName());
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		colorRepository.save(color);
		return new SuccessResult(color.getName()+ " added successfully");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		checkIfColorExistById(deleteColorRequest.getId());
		colorRepository.deleteById(deleteColorRequest.getId());
		return new SuccessResult("id: " + deleteColorRequest.getId()+ " deleted successfully");

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		checkIfColorExistById(updateColorRequest.getId());
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		colorRepository.save(color);
		return new SuccessResult(updateColorRequest.getId()+ " updated successfuly");

	}

	@Override
	public DataResult<Color> getById(ReadColorResponse readColorResponse) {
		checkIfColorExistById(readColorResponse.getId());
		Color color= this.modelMapperService.forResponse().map(readColorResponse, Color.class);		
	    color = colorRepository.findById(readColorResponse.getId()).get();
	    return new SuccessDataResult<Color>(color, "the color was successfully listed");
	}

	@Override
	public DataResult<List<GetAllColorsResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		List<GetAllColorsResponse> response = colors.stream()
				.map(color -> this.modelMapperService.forResponse().map(color, GetAllColorsResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllColorsResponse>>(response, " colors was successfully listed ");
	}

	
	private void checkIfColorExistByName(String name) {
		Color currentColor = colorRepository.findByName(name);
		if(currentColor!= null) {
			throw new BusinessException("COLOR.EXİST");
		}
	}
	
	private void checkIfColorExistById(int id) {
		boolean result= colorRepository.existsById(id);
		if(result == false) {
			throw new BusinessException("COLOR NOT EXİST");
		}
 	}
}
