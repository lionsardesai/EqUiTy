package com.lionsardesai.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class ComboBoxAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6154285702765287675L;

	private List<String> fruits;

	private String yourFruits;
	private String yourMonth;

	public String getYourMonth() {
		return yourMonth;
	}

	public void setYourMonth(String yourMonth) {
		this.yourMonth = yourMonth;
	}

	public List<String> getFruits() {
		return fruits;
	}

	public void setFruits(List<String> fruits) {
		this.fruits = fruits;
	}

	public String getYourFruits() {
		return yourFruits;
	}

	public void setYourFruits(String yourFruits) {
		this.yourFruits = yourFruits;
	}

	public ComboBoxAction() {

		fruits = new ArrayList<String>();
		fruits.add("Apple");
		fruits.add("Banana");
		fruits.add("Orange");
		fruits.add("Watermelon");
	}

	@Override
	public String execute() {
		return SUCCESS;
	}

	public String display() {
		return NONE;
	}

}
