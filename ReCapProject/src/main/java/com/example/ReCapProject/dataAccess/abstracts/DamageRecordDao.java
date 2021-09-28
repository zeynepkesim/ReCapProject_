package com.example.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.DamageRecord;

public interface DamageRecordDao extends JpaRepository<DamageRecord, Integer> {
	
	List<DamageRecord> getByCar_CarId(int carId);

}
