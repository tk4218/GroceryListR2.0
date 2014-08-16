package com.tk4218.grocerylistr1_0;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.tk4218.grocerylistr1_0.adapters.TabsPagerAdapter;
import com.tk4218.grocerylistr1_0.model.ActivityCommunicator;
import com.tk4218.grocerylistr1_0.model.Recipe;
import com.tk4218.grocerylistr1_0.model.RecipeBook;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, ActivityCommunicator {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	TabsPagerAdapter tabsAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	RecipeBook recipeBook, calendarRecipes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("DEBUG", "Activity Created");
		recipeBook = new RecipeBook();
		calendarRecipes = setCalendar();
		loadContent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try{
			Intent intent = getIntent();
			Recipe newRecipe = (Recipe) intent.getSerializableExtra("newRecipe");
			if(newRecipe != null)
				recipeBook.addRecipe(newRecipe);
			intent.removeExtra("newRecipe");
			saveContent();
		} catch(Exception e){ Log.d("DEBUG", "Unable to load new Recipe");}

		try{
			Intent intent = getIntent();
			Log.d("DEBUG", "Load Calendar");
			RecipeBook calendarRecipestemp = (RecipeBook)intent.getSerializableExtra("calendarRecipes");
			if(calendarRecipestemp != null)
				calendarRecipes = calendarRecipestemp;
			intent.removeExtra("calendarRecipes");
			saveContent();
		} catch(Exception e){ Log.d("DEBUG","Unable to get recipes from RecipeView"); }
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		tabsAdapter = new TabsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(tabsAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
					@Override
					public void onPageScrollStateChanged(int arg0) {
					}
					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {						
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		// Also specify this Activity object, which implements
		// the TabListener interface, as the callback (listener) for when
		// this tab is selected.
		actionBar.addTab(actionBar.newTab().setText("Calendar").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Recipes").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Grocery List").setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_recipe:
			Intent intent = new Intent(this, NewRecipeActivity.class);
			Log.d("DEBUG", "Recipe Count: " +recipeBook.getRecipeBookCount());
			intent.putExtra("recipeBookCount", recipeBook.getRecipeBookCount());
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}
	
	public void viewRecipe(View view){
		ImageView image = (ImageView) view.findViewById(R.id.list_image);
		int index= Integer.parseInt((String) image.getContentDescription());
		Intent intent = new Intent(this, RecipeViewActivity.class);
		intent.putExtra("recipe", recipeBook.getRecipe(index));
		intent.putExtra("calendarRecipes", calendarRecipes);
		startActivity(intent);
		finish();
	}
	
	private void saveContent(){
		Log.d("DEBUG", "Saving Recipe Book...");
		FileOutputStream out, out1;
		try{
			out = openFileOutput("AllRecipes", Context.MODE_PRIVATE);
			ObjectOutputStream oOut = new ObjectOutputStream(out);
			Log.d("DEBUG", "Book Size: "+recipeBook.getRecipeBookSize());
			oOut.writeObject(recipeBook);
			oOut.close();
		} catch(Exception e){}
		try{
			out1 = openFileOutput("Calendar", Context.MODE_PRIVATE);
			ObjectOutputStream oOut = new ObjectOutputStream(out1);
			oOut.writeObject(calendarRecipes);
			oOut.close();
		} catch(Exception e){}
	}
	
	private void loadContent(){
		Log.d("DEBUG", "Attempting to Load Recipe Book...");
		try {
			FileInputStream in = openFileInput("AllRecipes");
			ObjectInputStream oIn = new ObjectInputStream(in);
			recipeBook = (RecipeBook) oIn.readObject();
			oIn.close();
			FileInputStream in1 = openFileInput("Calendar");
			oIn = new ObjectInputStream(in1);
			calendarRecipes = (RecipeBook) oIn.readObject();
			oIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private RecipeBook setCalendar(){
		RecipeBook calendar = new RecipeBook();
		for(int i = 0; i < 7; i++){
			calendar.addRecipe(new Recipe());
			calendar.getRecipe(i).setName("No Recipe");
			calendar.getRecipe(i).setDescription("No Recipe Selected For Today");
			calendar.getRecipe(i).setImageURL("");
		}
		return calendar;
	}

	@Override
	public RecipeBook shareCalendarRecipes() {
		return calendarRecipes;
	}

	@Override
	public RecipeBook shareRecipes() {
		return recipeBook;
	}
	@Override
	public void setCalendarRecipes(RecipeBook recipes) {
		calendarRecipes = recipes;
		saveContent();
	}

	@Override
	public void setRecipes(RecipeBook recipes) {
		recipeBook = recipes;
		saveContent();
	}

	@Override
	public void refreshCalendarFragment() {
		recreate();
		mViewPager.setCurrentItem(0);
	}

	@Override
	public void refreshRecipeFragment() {
		recreate();
		mViewPager.setCurrentItem(1);
	}

}
