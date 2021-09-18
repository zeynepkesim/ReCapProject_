package com.example.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ReCapProject.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
	Brand getByBrandId(int bradnId);
}
