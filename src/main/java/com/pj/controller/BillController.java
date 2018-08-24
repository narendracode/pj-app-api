package com.pj.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pj.constants.AppConstants;
import com.pj.dao.BillRepository;
import com.pj.model.Bill;



@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	BillRepository billRepo;
	
	
	@PostMapping
	public Bill create(@RequestBody Bill bill) {
		bill.setStatus(AppConstants.BILL_PENDING_PAYMENT);
		bill.setCreatedAt(new java.sql.Date(new java.util.Date().getTime()));
		bill.setUpdatedAt(new java.sql.Date(new java.util.Date().getTime()));
		return billRepo.save(bill);
	}
	
	@PutMapping
	public Bill save(@RequestBody Bill bill) {
		bill.setUpdatedAt(new java.sql.Date(new java.util.Date().getTime()));
		return billRepo.save(bill);
	}
	
	
	@GetMapping
	public List<Bill> get(){
		return billRepo.findAll();
	}
	
	@RequestMapping(value = "/buyer/{id}", method = RequestMethod.GET)
	public List<Bill> getBillsByBuyerID(@PathVariable String id){
		return billRepo.getBillsByBuyerID(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Bill> get(@PathVariable String id){
		return billRepo.findById(id);
	}
	 
	@DeleteMapping
	public boolean delete(@RequestBody Bill bill) {
		billRepo.deleteById(bill.getId());
		 return true;
	}
	
	
	@RequestMapping(value = "/paid/{id}", method = RequestMethod.POST)
	public boolean deactivate(@PathVariable String id) {
		return billRepo.paid(id);
	}
	
}
