package com.kodlamaio.rentACar.business.requests.rentalDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalDetailRequest {
	private int id;
	private int additionalServiceId; 
	private int rentalId;
}
