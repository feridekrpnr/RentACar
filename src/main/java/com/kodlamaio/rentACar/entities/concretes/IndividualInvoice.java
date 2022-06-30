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
@Entity
@PrimaryKeyJoinColumn(name = "individual_invoice_id", referencedColumnName = "id")
@Table(name = "individual_invoices")
@EqualsAndHashCode(callSuper = true)
public class IndividualInvoice extends Invoice{
	
	@Column(name = "individual_invoice_id", insertable = false, updatable = false)
	private int individualInvoiceId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "individual_customer_id")
	private IndividualCustomer individualCustomer;
}
