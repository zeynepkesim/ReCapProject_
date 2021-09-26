package com.example.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.Receipt;

public interface ReceiptDao extends JpaRepository<Receipt, Integer> {

}
