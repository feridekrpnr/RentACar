package com.kodlamaio.rentACar.business.responses.individualcustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllIndividualCustomersResponse {
	private int individualCustomerId;
	private String firstName;
	private String lastName;
	private String identityNumber;
	private int birthYear;
	private int addressId;
	private String email;
	private String password;
	
}
