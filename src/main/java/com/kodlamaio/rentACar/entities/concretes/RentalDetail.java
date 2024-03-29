package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentalDetails")
public class RentalDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id")
	private int id;	
	
	@Column(name="sumTotalPrice")
	private double sumTotalPrice;
	
	@ManyToOne
	@JoinColumn(name="rental_id")	
	private Rental rental;
	
	@ManyToOne
	@JoinColumn(name="additionalService_id")
	private AdditionalService additionalService;
	
	@OneToMany(mappedBy = "rentalDetail")
	List<Invoice>invoices;
	
	
	
}
