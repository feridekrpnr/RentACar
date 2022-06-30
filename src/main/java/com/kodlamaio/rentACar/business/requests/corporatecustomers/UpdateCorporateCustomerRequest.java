package com.kodlamaio.rentACar.business.requests.corporatecustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCorporateCustomerRequest {
	private int id;
	private String taxNumber;
	private String corporateNumber;
	private Integer addressId;
	
}