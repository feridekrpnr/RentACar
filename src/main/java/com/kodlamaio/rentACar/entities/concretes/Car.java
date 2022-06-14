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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "maintenances", "rentals","additionalServices"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "dailyPrice")
	private double dailyPrice;

	@Column(name = "plate")
	private String plate;

	@Column(name = "kilometer")
	private double kilometer;

	@Column(name = "statusInformation")
	private int statusInformation;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@OneToMany(mappedBy = "car") //bir arabanın birden çok bakımı olabilir
	List<Maintenance> maintenances;

	@OneToMany(mappedBy = "car")
	List<Rental> rentals;
	
	@Column(name="pick_up_city_id")
	private int pickUpCityId; 
	
	@Column(name="return_city_id")
	private int returnCityId;
	
	
}
