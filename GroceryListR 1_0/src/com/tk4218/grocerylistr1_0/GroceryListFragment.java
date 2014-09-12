package com.tk4218.grocerylistr1_0;

import java.util.ArrayList;

import com.tk4218.grocerylistr1_0.adapters.GroceryListAdapter;
import com.tk4218.grocerylistr1_0.model.ActivityCommunicator;
import com.tk4218.grocerylistr1_0.model.Ingredient;
import com.tk4218.grocerylistr1_0.model.MeasurementConverter;
import com.tk4218.grocerylistr1_0.model.RecipeBook;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class GroceryListFragment extends ListFragment {
	ArrayList<Ingredient> ingredientList;
	boolean hideQuantities;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
		    Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		ActivityCommunicator communicator = (ActivityCommunicator) getActivity();
		RecipeBook calendarRecipes = communicator.shareCalendarRecipes();
		ingredientList = new ArrayList<Ingredient>();
		hideQuantities = false;
		boolean duplicate = false;
		for (int i = 0; i < calendarRecipes.getRecipeBookSize(); i++) {
			
			for (int j = 0; j < calendarRecipes.getRecipe(i).getIngredients().size(); j++) {
				duplicate = false;
				Log.d("DEBUG", "Groceries: "+ingredientList.size());
				for (int k = 0; k < ingredientList.size(); k++) {
					
					if (calendarRecipes.getRecipe(i).getIngredients().get(j).getName()
							.equalsIgnoreCase(ingredientList.get(k).getName())) {
						Log.d("DEBUG", ingredientList.get(k).getTotalAmount()+" " + calendarRecipes.getRecipe(i).getIngredients().get(j).getTotalAmount());

						double amount = MeasurementConverter
								.addMeasurements(ingredientList.get(k).getTotalAmount(), 
												 ingredientList.get(k).getMeasureType(), 
												 calendarRecipes.getRecipe(i).getIngredients().get(j).getTotalAmount(),
												 calendarRecipes.getRecipe(i).getIngredients().get(j).getMeasureType());
						Log.d("DEBUG", "New Amount: " + amount);
						if(amount != 0){
							ingredientList.get(k).setTotalAmount(Round(amount));
							if(MeasurementConverter.unitWeight(ingredientList.get(k).getMeasureType()) 
								< MeasurementConverter.unitWeight(calendarRecipes.getRecipe(i).getIngredients().get(j).getMeasureType()))
								ingredientList.get(k).setMeasureType(calendarRecipes.getRecipe(i).getIngredients().get(j).getMeasureType());
								duplicate = true;
						}
					}
				}
				if (!duplicate){
					Ingredient ingredient = new Ingredient(
							calendarRecipes.getRecipe(i).getIngredients().get(j).getName(),
							calendarRecipes.getRecipe(i).getIngredients().get(j).getAmount(),
							calendarRecipes.getRecipe(i).getIngredients().get(j).getAmount2(),
							calendarRecipes.getRecipe(i).getIngredients().get(j).getMeasureType(),
							"", false);
					
					ingredientList.add(ingredient);
				}
			}
		}
		setListAdapter(new GroceryListAdapter(getActivity(),
				R.layout.grocery_list_layout, ingredientList, false));
		return super.onCreateView(inflater, container, savedInstanceState);  
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
	    menu.add(Menu.NONE,  /** group ID.. not really needed unless you're working with groups **/
	              Menu.FIRST,         /** this is the items ID (get this in onOptionsItemSelected to determine what was clicked) **/
	              Menu.NONE, /** ORDER.. this is what you want to change **/
	              "Hide Quantities") /** title **/
	              .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
	   // inflater.inflate(R.menu.main, menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST:
			hideQuantities = !hideQuantities;
			setListAdapter(new GroceryListAdapter(getActivity(),
					R.layout.grocery_list_layout, ingredientList, hideQuantities));
		}
		return super.onOptionsItemSelected(item);
	}
	private double Round(double amt){
		return (double)Math.round(amt *100) / 100;
	}
}
