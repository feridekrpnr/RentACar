package com.kodlamaio.rentACar.business.requests.individualcustomers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	private int individualCustomerId;
	private String identityNumber;
	private String email;
	private String password;
	private int addressId;
	private String firstName;
	private String lastName;
	private int birthYear;
	
}
