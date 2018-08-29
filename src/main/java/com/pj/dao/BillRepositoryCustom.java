package com.pj.dao;
import java.util.List;

import com.pj.model.Bill;
import com.pj.model.Payment;

public interface BillRepositoryCustom {
	List<Bill> getBillsByBuyerID(String buyerId);
	boolean pay(Payment payment);

}
