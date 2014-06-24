package com.tk4218.grocerylistr1_0.model;

public class Ingredient {

	private String name;
	private int amount;
	private String measureType;
	private String preparation;
	
	public Ingredient(){
		setName("");
		setAmount(0);
		setMeasureType("");
		setPreparation("");
	}
	/**
	 * Constructor Ingredient() - creates a new Ingredient with given parameters.
	 * @param n - Ingredient Name
	 * @param a - Amount 
	 * @param m - Measure Type
	 * @param p - Preparation
	 */
	public Ingredient(String n, int a, String m, String p){
		setName(n);
		setAmount(a);
		setMeasureType(m);
		setPreparation(p);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public String getPreparation() {
		return preparation;
	}
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}
}
