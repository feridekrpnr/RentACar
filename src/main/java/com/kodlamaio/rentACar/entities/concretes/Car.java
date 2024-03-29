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

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "maintenances", "rentals"})
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
	private String statusInformation= "available";
	
	@Column(name="min_findex_score")
	private int minFindexScore;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@OneToMany(mappedBy = "car") //bir arabanın birden çok bakımı olabilir
	private List<Maintenance> maintenances;

	@OneToMany(mappedBy = "car") //bir araba birden fazla kiralanabilir
	private List<Rental> rentals;
	
	
	
}
