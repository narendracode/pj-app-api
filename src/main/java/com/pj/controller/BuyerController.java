package com.pj.controller;

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
import com.pj.dao.BuyerRepository;
import com.pj.model.Buyer;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	
	@Autowired
	BuyerRepository buyerRepo;
	
	
	@PostMapping
	public Buyer create(@RequestBody Buyer buyer) {
		buyerRepo.create(buyer);
		return buyer;
	}
	
	@PutMapping
	public Buyer save(@RequestBody Buyer buyer) {
		buyerRepo.save(buyer);
		return buyer;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable String id) {
		 buyerRepo.deleteById(id);
		 return true;
	}
	
	@GetMapping
	public List<Buyer> getAll(){
		return buyerRepo.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Buyer> get(@PathVariable String id){
		return buyerRepo.findById(id);
	}
	
	
	@RequestMapping(value = "/deactivate/{id}", method = RequestMethod.POST)
	public boolean deactivate(@PathVariable String id) {
		return buyerRepo.deactivate(id);
	}
	
	
	@RequestMapping(value = "/activate/{id}", method = RequestMethod.POST)
	public boolean activate(@PathVariable String id) {
		return buyerRepo.activate(id);
	}
	
}
