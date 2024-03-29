package com.kodlamaio.rentACar.business.requests.customers;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 2, max = 30)
	private String firstName;
	@NotBlank
	@NotEmpty
	@NotNull
	@Size(min = 2, max = 30)
	private String lastName;
	@NotBlank
	@NotEmpty
	@NotNull
	@Min(11)
	private String tcNo;
	@NotBlank
	@NotEmpty
	@NotNull
	private String email;
	@NotBlank
	@NotEmpty
	@NotNull
	private String password;
	
	private int dateOfBirth;
	private int addressId;
}
