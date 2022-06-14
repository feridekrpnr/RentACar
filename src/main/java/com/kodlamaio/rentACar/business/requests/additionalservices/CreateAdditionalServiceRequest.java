package com.kodlamaio.rentACar.business.requests.additionalservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceRequest {
	private int day;
	private int additionalItemId;
	private double totalPrice;

}
