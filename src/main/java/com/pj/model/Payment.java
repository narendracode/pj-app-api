package com.pj.model;

public class Payment {
	private String id;
	private double amountPaid;
	

	public Payment(String id, double amountPaid) {
		super();
		this.id = id;
		this.amountPaid = amountPaid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	
}
