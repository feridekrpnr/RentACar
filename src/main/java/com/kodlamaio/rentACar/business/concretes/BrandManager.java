package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.responses.brands.GetAllBrandsResponse;
import com.kodlamaio.rentACar.business.responses.brands.ReadBrandResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService { // ımpl=manager
	
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	// @Autowired //git constructoor parametresine bak onun somutunu newle onu ver
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		super();
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		// gelen requesti veri tabanı nesnesine çevirdik ve onu gönderdik
		brandRepository.save(brand);
		return new SuccessResult(brand.getName()+ " added successfully");
	}
	
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		checkIfBrandExistById(deleteBrandRequest.getId());
		brandRepository.deleteById(deleteBrandRequest.getId());
		return new SuccessResult("id: " +  deleteBrandRequest.getId()+ " deleted sucessfully");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		checkIfBrandExistById(updateBrandRequest.getId());
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		brandRepository.save(brand);
		return new SuccessResult(updateBrandRequest.getId()+" updated succesfully");

	}

	@Override
	public DataResult<Brand> getById(ReadBrandResponse readBrandResponse) {
		checkIfBrandExistById(readBrandResponse.getId());
		Brand brand = this.modelMapperService.forResponse().map(readBrandResponse, Brand.class);
		brand = brandRepository.findById(readBrandResponse.getId()).get();
		return new SuccessDataResult<Brand>(brand, " the brand was successfully listed");
	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBrandsResponse>>(response, " brands was successfully listed ");
	}

	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXIST");
		}
	}
	
	private void checkIfBrandExistById(int id) {
		boolean result = brandRepository.existsById(id);
		if(result == false) {
			throw new BusinessException("BRAND NOT EXIST");
		}
	}

}
