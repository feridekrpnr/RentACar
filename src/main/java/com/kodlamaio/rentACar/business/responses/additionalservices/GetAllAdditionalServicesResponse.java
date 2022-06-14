package com.kodlamaio.rentACar.business.responses.additionalservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdditionalServicesResponse {
	private int id;
	private int day;
	private double totalPrice;
	private int rentalId;
	private int additionalItemId;
	private Integer price;
}
