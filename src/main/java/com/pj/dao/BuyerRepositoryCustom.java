package com.pj.dao;

import com.pj.model.Buyer;

public interface BuyerRepositoryCustom {
	boolean deactivate(String id);
	boolean activate(String id);
	void create(Buyer buyer);
}
