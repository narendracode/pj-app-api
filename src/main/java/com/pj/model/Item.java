package com.pj.model;

import java.util.UUID;

public class Item {
	private String id;
	private String desc;
	private double weight;
	private int type;
	private int quantity;
	private double unitCost;
	
	public Item() {
		super();
		if(id == null)
			this.id = UUID.randomUUID().toString();
	}

	public Item(String desc, double weight, int type, int quantity, double unitCost) {
		this();
		this.desc = desc;
		this.weight = weight;
		this.type = type;
		this.quantity = quantity;
		this.unitCost = unitCost;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getUnitCost() {
		return unitCost;
	}
	
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
