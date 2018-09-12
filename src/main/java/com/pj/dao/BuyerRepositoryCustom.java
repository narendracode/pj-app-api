package com.pj.dao;

import com.pj.model.Buyer;
import com.pj.model.BuyerDTO;
import com.pj.model.BuyerGroupDTO;

import java.util.List;
import java.util.Map;

public interface BuyerRepositoryCustom {
	boolean deactivate(String id);
	boolean activate(String id);
	void create(Buyer buyer);
	List<BuyerGroupDTO> getSortedGroupedBuyers();
}
