package com.tk4218.grocerylistr1_0.model;

import java.io.Serializable;
import java.util.ArrayList;


public class RecipeBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8353333236869267519L;
	private ArrayList<Recipe> recipeBook;
	private int recipeCount;
	
	public RecipeBook(){
		recipeBook = new ArrayList<Recipe>();
		recipeCount = 0;
	}
	
	public Recipe getRecipe(int index){
		return recipeBook.get(index);
	}
	public Recipe getRecipeById(int id){
		for(int i = 0; i < recipeBook.size(); i++){
			if(recipeBook.get(i).getRecipeID() == id)
				return recipeBook.get(i);
		}
		return null;
	}
	public void setRecipeById(int id, Recipe recipe){
		for(int i = 0; i < recipeBook.size(); i++){
			if(recipeBook.get(i).getRecipeID() == id){
				recipeBook.set(i, recipe);
				return;
			}
		}
	}
	public void setRecipe(int index, Recipe recipe){
		recipeBook.set(index, recipe);
	}
	public void addRecipe(Recipe recipe){
		recipe.setRecipeID(recipeCount);
		recipeBook.add(recipe);
		recipeCount++;
	}
	public boolean removeRecipe(Recipe recipe){
		return recipeBook.remove(recipe);
	}
	public int getRecipeIndex(Recipe recipe){
		return recipeBook.indexOf(recipe);
	}
	public ArrayList<Recipe> getAllRecipes(){
		return recipeBook;
	}
	public int getRecipeBookSize(){
		return recipeBook.size();
	}
	public int getRecipeBookCount(){
		return recipeCount;
	}
}
