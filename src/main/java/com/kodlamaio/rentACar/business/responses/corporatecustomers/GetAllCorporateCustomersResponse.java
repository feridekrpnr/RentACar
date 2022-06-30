package com.kodlamaio.rentACar.business.responses.corporatecustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateCustomersResponse {
	private int id;
	private String taxNumber;
	private String corporateNumber;
	private Integer addressId;}