package com.kodlamaio.rentACar.business.responses.cars;

import org.hibernate.internal.build.AllowPrintStacktrace;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllowPrintStacktrace
public class GetAllCarsResponse {
	private String description;
	private double dailyPrice;
	private int brandId;
	private int colorId;
}
