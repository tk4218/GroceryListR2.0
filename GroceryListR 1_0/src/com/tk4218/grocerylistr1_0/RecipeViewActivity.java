package com.tk4218.grocerylistr1_0;

import com.tk4218.grocerylistr1_0.adapters.IngredientListAdapter;
import com.tk4218.grocerylistr1_0.adapters.ListExpander;
import com.tk4218.grocerylistr1_0.model.BitmapHandler;
import com.tk4218.grocerylistr1_0.model.Recipe;
import com.tk4218.grocerylistr1_0.model.RecipeBook;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeViewActivity extends Activity {
	Recipe recipe;
	RecipeBook calendarRecipes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_view);
		// Show the Up button in the action bar.
		Intent intent = getIntent();
		recipe = (Recipe) intent.getSerializableExtra("recipe");
		calendarRecipes = (RecipeBook) intent.getSerializableExtra("calendarRecipes");
		setupActionBar();

		for(int i = 0; i < recipe.getIngredients().size(); i++){
			Log.d("DEBUG", recipe.getIngredients().get(i).getName());
		}
		this.setContentView(R.layout.activity_recipe_view);
		
		ImageView recipeImage = (ImageView) findViewById(R.id.single_recipe_image);
		String pathName = recipe.getImageURL();
		try{
			if(pathName != null)
				recipeImage.setImageBitmap(BitmapHandler.loadFromFile(pathName));
			else
				recipeImage.setImageResource(R.drawable.ic_default_image);
		} catch(Exception e){
			recipeImage.setImageResource(R.drawable.ic_default_image);
		}
		TextView recipeName = (TextView) findViewById(R.id.single_recipe_name);
		recipeName.setText(recipe.getName());
		
		TextView recipeDescription = (TextView) findViewById(R.id.single_recipe_description);
		recipeDescription.setText(recipe.getDescription());
		
		ListView ingredientsList = (ListView) findViewById(R.id.single_recipe_ingredients_list);
		ingredientsList.setAdapter(new IngredientListAdapter(this, recipe.getIngredients()));
		ListExpander.getListViewSize(ingredientsList);
		
		TextView recipeInstructions = (TextView) findViewById(R.id.single_recipe_instructions);
		recipeInstructions.setText(recipe.getInstructions()); 
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle(recipe.getName());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("calendarRecipes", calendarRecipes);
			startActivity(intent);
			finish();
			return true;
		case R.id.action_add_recipe_to_calendar:
			popUpCalendarSelection();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed(){
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("calendarRecipes", calendarRecipes);
		Log.d("DEBUG", "Back Pressed");
		startActivity(intent);
		finish();
	}
	private void popUpCalendarSelection(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View popupLayout = inflater.inflate(R.layout.popup_calendar_selection_layout, null);
		
		builder.setView(popupLayout).setTitle("Add Recipe To Calendar")
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				CheckBox monday = (CheckBox) popupLayout.findViewById(R.id.checkbox_monday);
				if(monday.isChecked())
					calendarRecipes.setRecipe(0, recipe);
				CheckBox tuesday = (CheckBox) popupLayout.findViewById(R.id.checkbox_tuesday);
				if(tuesday.isChecked())
					calendarRecipes.setRecipe(1, recipe);
				CheckBox wednesday = (CheckBox) popupLayout.findViewById(R.id.checkbox_wednesday);
				if(wednesday.isChecked())
					calendarRecipes.setRecipe(2, recipe);
				CheckBox thursday = (CheckBox) popupLayout.findViewById(R.id.checkbox_thursday);
				if(thursday.isChecked())
					calendarRecipes.setRecipe(3, recipe);
				CheckBox friday = (CheckBox) popupLayout.findViewById(R.id.checkbox_friday);
				if(friday.isChecked())
					calendarRecipes.setRecipe(4, recipe);
				CheckBox saturday = (CheckBox) popupLayout.findViewById(R.id.checkbox_saturday);
				if(saturday.isChecked())
					calendarRecipes.setRecipe(5, recipe);
				CheckBox sunday = (CheckBox) popupLayout.findViewById(R.id.checkbox_sunday);
				if(sunday.isChecked())
					calendarRecipes.setRecipe(6, recipe);
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create();
		builder.show();
	}
}
