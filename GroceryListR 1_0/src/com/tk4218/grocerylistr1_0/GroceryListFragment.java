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
import android.view.View;
import android.view.ViewGroup;

public class GroceryListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
		    Bundle savedInstanceState) {
		ActivityCommunicator communicator = (ActivityCommunicator) getActivity();
		RecipeBook calendarRecipes = communicator.shareCalendarRecipes();
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		
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
				R.layout.grocery_list_layout, ingredientList));
		return super.onCreateView(inflater, container, savedInstanceState);  
	}

	private double Round(double amt){
		return (double)Math.round(amt *100) / 100;
	}
}
