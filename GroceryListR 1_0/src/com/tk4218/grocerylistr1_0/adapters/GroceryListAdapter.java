package com.tk4218.grocerylistr1_0.adapters;

import java.util.ArrayList;

import com.tk4218.grocerylistr1_0.R;
import com.tk4218.grocerylistr1_0.model.Ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GroceryListAdapter extends ArrayAdapter<Ingredient>{

	Context context;
	ArrayList<Ingredient> ingredients;
	public GroceryListAdapter(Context context, int resource,
			ArrayList<Ingredient> ingredients) {
		super(context, resource, ingredients);
		this.context = context;
		this.ingredients = ingredients;
	}
	@Override
	public int getCount(){
		return ingredients.size();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = convertView;
		if(convertView == null)
			view = inflater.inflate(R.layout.grocery_list_layout, null);
		TextView ingredientName = (TextView) view.findViewById(R.id.ingredient_name);
		ingredientName.setText(ingredients.get(position).getTotalAmount() + " " + ingredients.get(position).getMeasureType()
				+ " " + ingredients.get(position).getName());
		return view;
	}
}
