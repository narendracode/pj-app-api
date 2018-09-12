package com.pj.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pj.constants.AppConstants;
import com.pj.model.Buyer;
import com.pj.model.BuyerDTO;
import com.pj.model.BuyerGroupDTO;

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

	@Override
	public List<BuyerGroupDTO> getSortedGroupedBuyers() {
		final Query query = new Query().with(new Sort("firstName","1"));
		query.fields()
				.exclude("email")
				.exclude("phone")
				.exclude("status")
				.exclude("updatedAt")
				.exclude("createdAt")
				;
		List<Buyer> buyers = mongoTemplate.find(query, Buyer.class);
		Map<String, List<BuyerDTO>> sortedMap = new TreeMap<>();
		
		
		for (Buyer buyer : buyers) {
			String startChar = buyer.getFirstName().substring(0, 1);
			if(!sortedMap.containsKey(startChar.toUpperCase())) {
				sortedMap.put(startChar.toUpperCase(), new ArrayList<BuyerDTO>());
			}
			sortedMap
				.get(startChar.toUpperCase())
				.add(new BuyerDTO(buyer.getFirstName()+" "+buyer.getMiddleName()+" "+buyer.getLastName(), buyer.getId()));
		}
		
		Iterator<String> ite = sortedMap.keySet().iterator();
		List<BuyerGroupDTO> buyerGroups = new ArrayList<BuyerGroupDTO>();
		while(ite.hasNext()) {
			String key = ite.next();
			buyerGroups.add(new BuyerGroupDTO(key, sortedMap.get(key)));
		}
		
		return buyerGroups;
	}


	
}
