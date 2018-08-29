package com.pj.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pj.constants.AppConstants;
import com.pj.model.Bill;
import com.pj.model.Payment;


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
	public boolean pay(Payment payment) {
		final Query query = new Query();
		query.addCriteria(Criteria.where("id").is(payment.getId()));
		
		Bill bill = mongoTemplate.findOne(query, Bill.class);
		if(bill != null) {
			bill.setAmountPaid(bill.getAmountPaid()+ payment.getAmountPaid());
			if(bill.getAmountPaid() >= bill.getTotal()) {
				bill.setStatus(AppConstants.BILL_PAID);
			}
		}
		
		mongoTemplate.save(bill);
		
		return true;
	}
	
}
