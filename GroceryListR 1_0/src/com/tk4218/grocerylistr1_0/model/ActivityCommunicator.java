package com.tk4218.grocerylistr1_0.model;

public interface ActivityCommunicator {
	public RecipeBook shareCalendarRecipes();
	public RecipeBook shareRecipes();
	public void setCalendarRecipes(RecipeBook recipes);
	public void setRecipes(RecipeBook recipes);
	public void refreshCalendarFragment();
	public void refreshRecipeFragment();
	public void changeTab(int index);
}
