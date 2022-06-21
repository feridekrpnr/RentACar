package com.kodlamaio.rentACar.entities.concretes;

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

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Entity
@Table(name="Invoices")
public class Invoice {
	
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;
  
  @Column(name="invoice_number")
  String invoiceNumber;
  
  @Column(name="state")
  private boolean state;
  
  @ManyToOne
  @JoinColumn(name="rentalDetails_id")
  private RentalDetail rentalDetail;
  
  
}
