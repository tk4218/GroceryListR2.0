package com.tk4218.grocerylistr1_0;


import java.util.ArrayList;

import com.tk4218.grocerylistr1_0.adapters.TabsPagerAdapter;
import com.tk4218.grocerylistr1_0.model.Ingredient;
import com.tk4218.grocerylistr1_0.model.Recipe;
import com.tk4218.grocerylistr1_0.model.RecipeBook;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

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
	
	RecipeBook recipeBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recipeBook = new RecipeBook();
    	recipeBook.addRecipe(new Recipe("Stuff",2, "Description","Instructions", new ArrayList<Ingredient>()));
    	recipeBook.addRecipe(new Recipe("More Stuff",2, "Description","Instructions", new ArrayList<Ingredient>()));
    	recipeBook.addRecipe(new Recipe("Good Stuff",2, "Description","Instructions", new ArrayList<Ingredient>()));
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
		startActivity(intent);
	}

}
