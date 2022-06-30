
package com.kodlamaio.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@PrimaryKeyJoinColumn(name = "corporate_invoice_id", referencedColumnName = "id")
@Table(name = "corporate_invoices")
public class CorporateInvoice extends Invoice {
	
	@Column(name = "corporate_invoice_id", insertable = false, updatable = false)
	private int corporateInvoiceId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "corporate_customer_id")
	private CorporateCustomer corporateCustomer;
}