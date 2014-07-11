package com.tk4218.grocerylistr1_0.model;

import java.util.ArrayList;

public class Recipe {

	private String name;
	private int servingSize;
	private String description;
	private String instructions;
	private ArrayList<Ingredient> ingredients;
	
	/**
	 *  Constructor Recipe() - creates a new empty recipe.
	 */
	public Recipe(){
		name = "";
		servingSize = 0;
		description = "";
		instructions = "";
		ingredients = new ArrayList<Ingredient>();
	}
	/**
	 *  Constructor Recipe()- Creates a new Recipe with given parameters.
	 * @param n - Recipe Name
	 * @param ss - Serving Size
	 * @param d - Description
	 * @param i - Instructions
	 * @param in - List of Ingredients
	 */
	public Recipe(String n, int ss, String d, String i, ArrayList<Ingredient> in){
		name = n;
		servingSize = ss;
		description = d;
		instructions = i;
		ingredients = in;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getServingSize(){
		return servingSize;
	}
	public void setServingSize(int size){
		servingSize = size;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getInstructions(){
		return instructions;
	}
	public void setInstructions(String instructions){
		this.instructions = instructions;
	}
	public ArrayList<Ingredient> getIngredients(){
		return ingredients;
	}
	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}
	
}
