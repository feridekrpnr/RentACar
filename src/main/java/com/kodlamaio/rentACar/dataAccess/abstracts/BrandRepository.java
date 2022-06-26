package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> { // jpa çok güçlü bir implementesyon, somutunu bul getir.Buyuk harfle yyazılan ınteger onun classı kucukle yazılan onun ınstance
	Brand findByName(String name);

}
