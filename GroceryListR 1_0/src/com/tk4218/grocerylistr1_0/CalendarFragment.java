package com.tk4218.grocerylistr1_0;

import java.util.ArrayList;

import com.tk4218.grocerylistr1_0.adapters.CalendarAdapter;
import com.tk4218.grocerylistr1_0.model.Recipe;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
 
public class CalendarFragment extends ListFragment {
 
	CalendarAdapter calAdapter;
	ArrayList<Recipe> calRecipes;
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
    	super.onActivityCreated(savedInstanceState);
    	calRecipes = new ArrayList<Recipe>();
    	for(int i = 0; i < 7; i++)
    		calRecipes.add(new Recipe());
    	calAdapter = new CalendarAdapter(getActivity(),calRecipes);
    	setListAdapter(calAdapter);
    }
}
