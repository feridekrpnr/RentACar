package com.kodlamaio.rentACar.business.requests.corporateinvoices;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateUpdateCorporateInvoiceRequest {
	private int id;
	private int invoiceNumber;
	private boolean state;

	
}