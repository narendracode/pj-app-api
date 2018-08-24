package com.pj.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pj.constants.AppConstants;
import com.pj.model.Bill;


@Repository
@Transactional(readOnly = true)
public class BillRepositoryImpl implements BillRepositoryCustom{
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public BillRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Bill> getBillsByBuyerID(String buyerId) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("buyerId").is(buyerId));
		return mongoTemplate.find(query, Bill.class);
	}
	
	@Override
	public boolean paid(String id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("status", AppConstants.BILL_PAID);
		Bill updatedBill = mongoTemplate.findAndModify(query, update, Bill.class);
		
		if(updatedBill != null)
			return true;
		else
			return false;
	}
	
}
