package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.Damage;

public interface DamageDao extends JpaRepository<Damage, Integer> {
	
	List<Damage> getByCar_CarId(int carId);

}
