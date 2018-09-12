package com.pj.model;
import java.util.List;

public class BuyerGroupDTO {
	private String letter;
	private List<BuyerDTO> names;
	
	public BuyerGroupDTO(String letter, List<BuyerDTO> names) {
		super();
		this.letter = letter;
		this.names = names;
	}
	
	public String getLetter() {
		return letter;
	}
	public void setLetter(String letter) {
		this.letter = letter;
	}
	public List<BuyerDTO> getNames() {
		return names;
	}
	public void setNames(List<BuyerDTO> names) {
		this.names = names;
	}
	
}
