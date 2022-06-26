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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="additional_services")  

public class AdditionalService {  //OrderedAdditionalItem
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="day")
	private int day;
	
	@Column(name="total_price")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "additionalItem_id")
	private AdditionalItem additionalItem;
	
	@OneToMany(mappedBy = "additionalService")
	private List<Rental> rentals;
	
	
}