package com.pj.dao;



import org.springframework.data.mongodb.repository.MongoRepository;
import com.pj.model.Buyer;

public interface BuyerRepository extends MongoRepository<Buyer, String>, BuyerRepositoryCustom{

}

