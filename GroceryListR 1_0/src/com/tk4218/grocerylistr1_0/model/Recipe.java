/**
 *  This class is used to store recipes. Any attributes a recipe can have can be stored and retrieved from
 *  this class. Ingredients are stored in an ArrayList. Instructions are currently stored in a single string,
 *  but this may change later.
 */
package com.tk4218.grocerylistr1_0.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable{

	/**
	 * Serializable ID for Recipe
	 */
	private static final long serialVersionUID = 6330147596577614371L;
	private String name;
	private int servingSize;
	private String description;
	private String instructions;
	private ArrayList<Ingredient> ingredients;
	
	/**
	 *  Constructor Recipe()- Creates a new Recipe with given parameters. Can be left empty to create empty recipe.
	 * @param n - Recipe Name
	 * @param ss - Serving Size
	 * @param d - Description
	 * @param i - Instructions
	 * @param in - List of Ingredients
	 * 
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
	
	/**
	 * getName()- Returns the name of the recipe.
	 * @return Recipe.name
	 */
	public String getName(){
		return name;
	}
	/**
	 * setName()- Sets the name of a recipe
	 * @param name - new name of recipe.
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * getServingSize()- Returns the serving size for this recipe.
	 * @return Recipe.servingSize
	 */
	public int getServingSize(){
		return servingSize;
	}
	/**
	 * setServingSize()- set the serving size for this recipe.
	 * @param size - new serving size for this recipe.
	 */
	public void setServingSize(int size){
		servingSize = size;
	}
	/**
	 * getDescription()- Returns the description for this recipe
	 * @return Recipe.description
	 */
	public String getDescription(){
		return description;
	}
	/**
	 * setDescription()- sets the description for this recipe.
	 * @param description - new description for this recipe
	 */
	public void setDescription(String description){
		this.description = description;
	}
	/**
	 * getInstructions()- returns the instructions for this recipe
	 * @return Recipe.instructions
	 */
	public String getInstructions(){
		return instructions;
	}
	/**
	 * setInstructions()- set the instructions for this recipe.
	 * Must include all instructions for this recipe. This method overwrites current instructions, not amends them.
	 * @param instructions - new instructions for this recipe
	 */
	public void setInstructions(String instructions){
		this.instructions = instructions;
	}
	/**
	 * getIngredients() - returns the list of ingredients for this recipe
	 * @return Recipe.ingredients (ArrayList<Ingredient>)
	 */
	public ArrayList<Ingredient> getIngredients(){
		return ingredients;
	}
	/**
	 * addIngredient()- add an ingredient to the list of ingredients.
	 * @param ingredient - new ingredient to add to recipe.
	 */
	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}

}
