package com.tk4218.grocerylistr1_0;



import com.tk4218.grocerylistr1_0.adapters.CalendarAdapter;
import com.tk4218.grocerylistr1_0.model.ActivityCommunicator;
import com.tk4218.grocerylistr1_0.model.CalendarDay;
import com.tk4218.grocerylistr1_0.model.Recipe;
import com.tk4218.grocerylistr1_0.model.RecipeBook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;
 
public class CalendarFragment extends ListFragment {
 
	CalendarAdapter calAdapter;
	RecipeBook calRecipes;
	ActivityCommunicator calendarCommunicator;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
    	super.onActivityCreated(savedInstanceState);
    	
    	/*
    	 * Get the recipes set in the calendar for the week
    	 */
    	calendarCommunicator = (ActivityCommunicator) getActivity();
    	calRecipes = calendarCommunicator.shareCalendarRecipes();
    	this.registerForContextMenu(this.getListView());
    	
    	/*
    	 * Set the List adapter to populate the recipes
    	 */
    	calAdapter = new CalendarAdapter(getActivity(),calRecipes);
    	setListAdapter(calAdapter);
    }
    @Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    	super.onCreateContextMenu(menu, v, menuInfo);  
    	menu.setHeaderTitle("Choose Action"); 
    	AdapterContextMenuInfo cmi = (AdapterContextMenuInfo) menuInfo;
    	TextView day = (TextView)cmi.targetView.findViewById(R.id.dayOfWeek);
    	int id = CalendarDay.dayIndex(day.getText().toString()); 
    	menu.add(0, id, 0, "Remove");
    }
    
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
    	final int index = item.getItemId();
        if(item.getTitle()=="Remove"){
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					calRecipes.setRecipe(index, new Recipe());
					calRecipes.getRecipe(index).setName("No Recipe");
					calRecipes.getRecipe(index).setDescription("No Recipe Selected For Today");
					calRecipes.getRecipe(index).setImageURL("");
					try{
						calendarCommunicator.setCalendarRecipes(calRecipes);
					} catch(Exception e){}
					calendarCommunicator.refreshCalendarFragment();
				}
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
	               // User cancelled the dialog
				}
			});
			builder.setTitle("Confirm");
			builder.setMessage("Are you sure you want to remove this recipe?");
			AlertDialog dialog = builder.create();
			dialog.show();
        }  
        else {
        	return false;
        }  
        return true;  
    }
}
