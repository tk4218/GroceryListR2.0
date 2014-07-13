package com.tk4218.grocerylistr1_0;

import com.tk4218.grocerylistr1_0.model.Recipe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class RecipeViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe_view);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent = getIntent();
		Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");
		
		this.setContentView(R.layout.activity_recipe_view);
		
		TextView recipeName = (TextView) findViewById(R.id.single_recipe_name);
		recipeName.setText(recipe.getName());
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

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
