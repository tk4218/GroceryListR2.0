package com.tk4218.grocerylistr1_0;

import com.tk4218.grocerylistr1_0.adapters.IngredientListAdapter;
import com.tk4218.grocerylistr1_0.adapters.ListExpander;
import com.tk4218.grocerylistr1_0.model.BitmapHandler;
import com.tk4218.grocerylistr1_0.model.Recipe;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RecipeViewActivity extends Activity {
	Recipe recipe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_view);
		// Show the Up button in the action bar.
		
		Intent intent = getIntent();
		recipe = (Recipe) intent.getSerializableExtra("recipe");
		setupActionBar();

		for(int i = 0; i < recipe.getIngredients().size(); i++){
			Log.d("DEBUG", recipe.getIngredients().get(i).getName());
		}
		this.setContentView(R.layout.activity_recipe_view);
		
		ImageView recipeImage = (ImageView) findViewById(R.id.single_recipe_image);
		String pathName = Environment.getExternalStorageDirectory().toString()
				+ "/Pictures/GroceryListR/recipeImage_"+recipe.getId()+".jpg";
		try{
			recipeImage.setImageBitmap(BitmapHandler.loadFromFile(pathName));
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
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
