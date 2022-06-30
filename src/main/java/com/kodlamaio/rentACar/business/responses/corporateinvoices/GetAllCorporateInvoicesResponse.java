package com.kodlamaio.rentACar.business.responses.corporateinvoices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCorporateInvoicesResponse {
	private int id;
	private int invoiceNumber;
	private boolean state;
}
