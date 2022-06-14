package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> { // jpa çok güçlü bir implementesyon
	Brand findByName(String name);
//	Brand findById(int id);
}
