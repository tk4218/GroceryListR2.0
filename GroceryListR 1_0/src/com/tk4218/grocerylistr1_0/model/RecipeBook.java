package com.tk4218.grocerylistr1_0.model;

import java.util.ArrayList;

public class RecipeBook {
	private ArrayList<Recipe> recipeBook;
	
	public RecipeBook(){
		recipeBook = new ArrayList<Recipe>();
	}
	
	public Recipe getRecipe(int index){
		return recipeBook.get(index);
	}
	public void addRecipe(Recipe recipe){
		recipeBook.add(recipe);
	}
	public int getRecipeIndex(Recipe recipe){
		return recipeBook.indexOf(recipe);
	}
	public ArrayList<Recipe> getAllRecipes(){
		return recipeBook;
	}
}
