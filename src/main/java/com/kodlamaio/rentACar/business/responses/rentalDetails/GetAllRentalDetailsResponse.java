package com.kodlamaio.rentACar.business.responses.rentalDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalDetailsResponse {
	private int id;
	private double sumTotalPrice;
	private int additionalServiceId;
	private int rentalId;
}
