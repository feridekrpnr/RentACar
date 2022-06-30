package com.kodlamaio.rentACar.business.requests.individualinvoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateUpdateIndividualInvoiceRequest {
	private int id;
	private int invoiceNumber;
	private boolean state;

	
}
