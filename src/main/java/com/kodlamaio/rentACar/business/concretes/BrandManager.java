package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ModelMapperService mapper;

	// @Autowired //git constructoor parametresine bak onun somutunu newle onu ver

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		checkIfBrandExistByName(createBrandRequest.getName());
		Brand brand = this.mapper.forRequest().map(createBrandRequest, Brand.class);
		// gelen requesti veri tabanı nesnesine çevirdik ve onu gönderdik
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		Brand brand = this.mapper.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandRepository.delete(brand);
		return new SuccessResult("BRAND.DELETED");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.mapper.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public DataResult<Brand> getById(ReadBrandResponse readBrandResponse) {
		Brand item = this.mapper.forResponse().map(readBrandResponse, Brand.class);
		item = brandRepository.findById(readBrandResponse.getId()).get();
		return new SuccessDataResult<Brand>(item);

	}

	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> response = brands.stream()
				.map(brand -> this.mapper.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllBrandsResponse>>(response);
	}

	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXIST");
		}
	}

}
