package com.kodlamaio.rentACar.business.requests.cars;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	@NotBlank
	@NotEmpty
	@Size(min=2,max=20)
	private String description;
	@Min(20)
	private double dailyPrice;
	private String plate;
	private int brandId;
	private int colorId;
}
