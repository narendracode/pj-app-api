package com.pj.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pj.model.Bill;

public interface BillRepository extends MongoRepository<Bill, String>, BillRepositoryCustom{

}
