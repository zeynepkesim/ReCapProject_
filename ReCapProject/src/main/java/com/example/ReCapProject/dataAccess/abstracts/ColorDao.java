package com.example.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.Color;

public interface ColorDao extends JpaRepository<Color, Integer>{
	Color getByColorId(int colorId);
}
