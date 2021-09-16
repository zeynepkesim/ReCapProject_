package com.example.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer> {
	Brand getByBrandId(int bradnId);
}
