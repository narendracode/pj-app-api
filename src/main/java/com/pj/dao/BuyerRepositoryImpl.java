package com.pj.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pj.constants.AppConstants;
import com.pj.model.Buyer;

@Repository
@Transactional(readOnly = true)
public class BuyerRepositoryImpl implements BuyerRepositoryCustom{

	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public BuyerRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public boolean deactivate(String id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("status", AppConstants.BUYER_DEACTIVE);
		Buyer updatedBuyer = mongoTemplate.findAndModify(query, update, Buyer.class);
		
		if(updatedBuyer != null)
			return true;
		else
			return false;
	}

	
	@Override
	public boolean activate(String id) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("status", AppConstants.BUYER_ACTIVE);
		Buyer updatedBuyer = mongoTemplate.findAndModify(query, update, Buyer.class);
		
		if(updatedBuyer != null)
			return true;
		else
			return false;
	}
	
	@Override
	public void create(Buyer buyer) {
		buyer.setCreatedAt(new java.sql.Date(new java.util.Date().getTime()));
		buyer.setUpdatedAt(new java.sql.Date(new java.util.Date().getTime()));
		buyer.setStatus(AppConstants.BUYER_ACTIVE);
		mongoTemplate.save(buyer);
	}


	
}
