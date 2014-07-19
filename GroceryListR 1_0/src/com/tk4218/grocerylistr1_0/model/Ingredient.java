/** 
 * This class is used to store ingredients. Any attributes an ingredient may have can be
 * stored and retrieved from this class. 
 */
package com.tk4218.grocerylistr1_0.model;

import java.io.Serializable;

public class Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1976158169163065247L;
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
	/**
	 * getName()- Returns the name of the ingredient
	 * @return Ingredient.name
	 */
	public String getName() {
		return name;
	}
	/**
	 * setName()- Sets the name of the ingredient
	 * @param name - new name of the ingredient
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getAmount()- returns the quantity measurement for this ingredient. 
	 * For example, if a recipe called for 1 cup of this ingredient, this 
	 * method would return 1.
	 * @return Ingredient.amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * setAmount()- sets the quantity measurement for this ingredient. 
	 * @param amount - new quantity amount for this ingredient.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * getMeasureType()- returns the measurement type for this ingredient (cups, teaspoons, oz, etc.)
	 * @return Ingredient.measureType
	 */
	public String getMeasureType() {
		return measureType;
	}
	/**
	 * setMeasureType()- sets the measurement type for this ingredient (cups, teaspoons, oz, etc.)
	 * @param measureType - new measurement type for this ingredient
	 */
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	/**
	 * getPreparation() - returns the preparation of this ingredient (chopped, diced, melted, etc.)
	 * @return Ingredient.preparation
	 */
	public String getPreparation() {
		return preparation;
	}
	/**
	 * setPreparation()- sets the preparation for this ingredient (chopped, diced, melted, etc.)
	 * @param preparation - new preparation for this recipe.
	 */
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}
}
