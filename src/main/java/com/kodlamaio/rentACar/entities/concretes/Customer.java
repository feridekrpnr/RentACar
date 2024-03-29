package com.kodlamaio.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "customers")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
public class Customer extends User {

	@Column(name = "customer_id", insertable = false, updatable = false)
	private int customerId;

	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;

	@ManyToOne()
	@JoinColumn(name = "address")
	private Address address;
}