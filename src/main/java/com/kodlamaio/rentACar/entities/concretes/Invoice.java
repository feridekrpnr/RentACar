package com.kodlamaio.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","additionalService","rental"})
@Inheritance(strategy =InheritanceType.JOINED)
@Table(name = "Invoices")
public class Invoice {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "total_price")
	private double totalPrice;

	@Column(name = "state")
	private int state; // int yap
	
	@ManyToOne
	@JoinColumn(name = "additional_service_id")
	private AdditionalService additionalService;
	
	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;

}
