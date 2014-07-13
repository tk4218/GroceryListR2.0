package com.tk4218.grocerylistr1_0;

import java.util.ArrayList;


import com.tk4218.grocerylistr1_0.adapters.RecipeAdapter;
import com.tk4218.grocerylistr1_0.model.Ingredient;
import com.tk4218.grocerylistr1_0.model.Recipe;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class RecipeFragment extends Fragment{
	
	RecipeBook recipeBook = new RecipeBook();

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	this.setHasOptionsMenu(true);
       
    	GridView gridView = new GridView(getActivity());
    	Configuration config = getResources().getConfiguration();
    	if(config.orientation == Configuration.ORIENTATION_PORTRAIT)
    		gridView.setNumColumns(2);
    	else 
    		gridView.setNumColumns(3);
    	
    	recipeBook.addRecipe(new Recipe("Stuff",2, "Description","Instructions", new ArrayList<Ingredient>()));
    	recipeBook.addRecipe(new Recipe("More Stuff",2, "Description","Instructions", new ArrayList<Ingredient>()));
    	recipeBook.addRecipe(new Recipe("Good Stuff",2, "Description","Instructions", new ArrayList<Ingredient>()));
    	
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
    	menu.add(0, id, 0, "Remove");
    }
    
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
    	int index = item.getItemId();
        if(item.getTitle()=="Edit"){
			Intent intent = new Intent(getActivity(), RecipeViewActivity.class);
			intent.putExtra("recipe", recipeBook.getRecipe(index));
			startActivity(intent);
        }  
        else if(item.getTitle()=="Remove"){
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// User clicked OK button
					//Intent intent = new Intent(RecipeFragment.this.getActivity(), MainActivity.class);
					//intent.putExtra(MainActivity.tab, "1");
					//RecipeFragment.this.startActivity(intent);
					//RecipeFragment.this.getActivity().finish();
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
