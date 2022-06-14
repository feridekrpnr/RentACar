package com.kodlamaio.rentACar.entities.concretes;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cars"})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="maintenances")

public class Maintenance {
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_sent")
	private Date dateSent; 
	
	@Column(name="date_return")
	private Date dateReturn; 
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	

	

}
