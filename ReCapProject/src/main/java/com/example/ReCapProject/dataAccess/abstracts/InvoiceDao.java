package com.example.ReCapProject.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReCapProject.entities.concretes.Invoice;

public interface InvoiceDao extends JpaRepository <Invoice, Integer> {
	
//	@Query("Select new com.example.ReCapProject.entities.dtos.InvoiceDetailDto"
//	  		+ " (i.invoiceNo, i.creationDate, r.rentalId, r.rentDate, r.returnDate, r.rentalPrice, r.pickUpKilometer, r.returnKilometer, c.carId, b.brandName, b.modelName, b.modelYear)"
//	  		+ " From Rental r Inner Join r.invoice i"
//			+ " Inner Join r.car c"
//          + " Inner Join c.brand b" )
//	List<InvoiceDetailDto> getInvoiceDetails();
	
	Invoice getTop1ByOrderByCreationDateDesc();
	Invoice getByRental_RentalId(int rentalId);
	
	List<Invoice> getByRental_User_UserId(int userId);
	List<Invoice> getByCreationDateBetween(LocalDate minDate, LocalDate maxDate);

}
