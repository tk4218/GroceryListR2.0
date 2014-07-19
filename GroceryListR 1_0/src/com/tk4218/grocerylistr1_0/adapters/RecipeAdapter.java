package com.tk4218.grocerylistr1_0.adapters;

import java.io.File;
import java.util.ArrayList;

import com.tk4218.grocerylistr1_0.R;
import com.tk4218.grocerylistr1_0.model.BitmapHandler;
import com.tk4218.grocerylistr1_0.model.Recipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class RecipeAdapter extends ArrayAdapter<Recipe>{

	Context context;
	ArrayList<Recipe> recipeBook;
	public RecipeAdapter(Context context, int resource, ArrayList<Recipe> allRecipes) {
		super(context, resource, allRecipes);
		this.context = context;
		recipeBook = allRecipes;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = convertView;
		if(convertView == null)
			view = inflater.inflate(R.layout.recipe_grid_view, null);
		String pathName = Environment.getExternalStorageDirectory().toString()
				+ "/Pictures/RecipeImages/recipeImage_"+recipeBook.get(position).getId()+".jpg";
		ImageView recipeImage = (ImageView)view.findViewById(R.id.list_image);
		try{
			recipeImage.setImageBitmap(BitmapHandler.loadFromFile(pathName));
		} catch(Exception e){
			recipeImage.setImageResource(R.drawable.ic_default_image);
		}
		recipeImage.setScaleType(ScaleType.FIT_XY);
		recipeImage.setContentDescription(""+recipeBook.indexOf(recipeBook.get(position)));
		
		TextView recipeName = (TextView) view.findViewById(R.id.recipe_name);
		recipeName.setText(recipeBook.get(position).getName());
		
		return view;
	}
}
