/** 
 * This class is used to store ingredients. Any attributes an ingredient may have can be
 * stored and retrieved from this class. 
 */
package model;

import java.io.Serializable;

import android.util.Log;

public class Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1976158169163065247L;
	private String name;
	private int amount;
	private String amount2;
	private double amount2fraction;
	private double totalAmount;
	private String measureType;
	private String preparation;
	private boolean optional;
	
	public Ingredient(){
		setName("No Ingredient");
		setAmount2("0/16");
		setMeasureType("");
		setPreparation("");
		setOptional(false);
		setTotalAmount(0);
	}
	/**
	 * Constructor Ingredient() - creates a new Ingredient with given parameters.
	 * @param n - Ingredient Name
	 * @param a - Amount 
	 * @param m - Measure Type
	 * @param p - Preparation
	 */
	public Ingredient(String n, int a, String a2, String m, String p, boolean opt){
		setName(n);
		setAmount(a);
		setAmount2(a2);
		setMeasureType(m);
		setPreparation(p);
		setOptional(opt);
		setTotalAmount(amount+amount2fraction);
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
	
	public String getAmount2(){
		return amount2;
	}
	
	public void setAmount2(String amount){
		amount2 = amount;
		String split [] = amount.split("/");
		amount2fraction = Double.parseDouble(split[0])/Double.parseDouble(split[1]);
		Log.d("DEBUG", "Fraction: " +amount2fraction );
	}
	
	public double getAmount2Fraction(){
		return amount2fraction;
	}
	public void setTotalAmount(double total){
		totalAmount = total;
		amount = (int) (total/1);
		amount2fraction = total - amount;
	}
	public double getTotalAmount(){
		return totalAmount;
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
	
	public void setOptional(boolean opt){
		optional = opt;
	}
	
	public boolean isOptional(){
		return optional;
	}
}
