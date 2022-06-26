package com.kodlamaio.rentACar.business.requests.cars;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	
	@Min(20)
	private double dailyPrice;
	@NotEmpty
	@Size(min=2,max=20)
	private String description;
	private int brandId;
	private int colorId;
	private String plate;
	private double kilometer;
	private String statusInformation;
	private int minFindexScore;
	
}
