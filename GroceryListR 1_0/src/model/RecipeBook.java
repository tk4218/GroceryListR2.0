package model;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

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
	public void setRecipe(int index, Recipe recipe){
		recipeBook.set(index, recipe);
	}
	public void addRecipe(Recipe recipe){
		recipeBook.add(recipe);
		recipeCount++;
		Log.d("DEBUG", "Recipe added!");
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
