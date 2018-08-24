package com.pj.dao;
import java.util.List;

import com.pj.model.Bill;

public interface BillRepositoryCustom {
	List<Bill> getBillsByBuyerID(String buyerId);
	boolean paid(String id);
}
