package com.tk4218.grocerylistr1_0;

import java.io.File;

import com.tk4218.grocerylistr1_0.adapters.RecipeAdapter;
import com.tk4218.grocerylistr1_0.model.ActivityCommunicator;
import com.tk4218.grocerylistr1_0.model.RecipeBook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class RecipeFragment extends Fragment{
	
	RecipeBook recipeBook = new RecipeBook();
	ActivityCommunicator activityCommunicator;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	this.setHasOptionsMenu(true);
    	
    	try{
    		activityCommunicator = (ActivityCommunicator) getActivity();
        	recipeBook = activityCommunicator.shareRecipes();
    	} catch(Exception e){}
    	
    	GridView gridView = new GridView(getActivity());
    	Configuration config = getResources().getConfiguration();
    	if(config.orientation == Configuration.ORIENTATION_PORTRAIT)
    		gridView.setNumColumns(2);
    	else 
    		gridView.setNumColumns(3);

    	gridView.setAdapter(new RecipeAdapter(getActivity(),R.layout.recipe_grid_view, recipeBook.getAllRecipes()));
        registerForContextMenu(gridView);

    	return gridView;
	}

    @Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
    	super.onCreateContextMenu(menu, v, menuInfo);  
    	menu.setHeaderTitle("Choose Action"); 
    	AdapterContextMenuInfo cmi = (AdapterContextMenuInfo) menuInfo;
    	ImageView image = (ImageView)cmi.targetView.findViewById(R.id.list_image);
    	int id = Integer.parseInt(image.getContentDescription().toString());
    	menu.add(0, id, 0, "Edit");  
    	menu.add(0, id, 0, "Delete");
    }
    
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
    	final int index = item.getItemId();
        if(item.getTitle()=="Edit"){
			Intent intent = new Intent(getActivity(), RecipeViewActivity.class);
			intent.putExtra("recipe", recipeBook.getRecipe(index));
			startActivity(intent);
        }  
        else if(item.getTitle()=="Delete"){
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					String pathName = recipeBook.getRecipe(index).getImageURL();
					recipeBook.removeRecipe(recipeBook.getRecipe(index));
					File file = new File(pathName);
					file.delete();
					try{
						activityCommunicator = (ActivityCommunicator) getActivity();
						activityCommunicator.setRecipes(recipeBook);
					} catch(Exception e){}
					activityCommunicator.refreshRecipeFragment();
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
