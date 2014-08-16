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

public class IngredientListAdapter extends ArrayAdapter<Ingredient>{

	ArrayList<Ingredient> ingredientList;
	Context context;
	int pos;
	
	public IngredientListAdapter(Context context, ArrayList<Ingredient> ingredients) {
		super(context, R.layout.add_ingredient_list_view, ingredients);
		
		ingredientList = ingredients;
		this.context = context;
	}
	
	@Override
	public int getCount(){
		return ingredientList.size();
	}

	public Ingredient getItem(int position){
		return ingredientList.get(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = convertView;
		pos = position;
		if(convertView == null)
			view = inflater.inflate(R.layout.add_ingredient_list_view, null);
		String amount1 = "", amount2 = "", preparation = "", optional = "";
		if(ingredientList.get(position).getAmount() != 0){
			amount1 = ingredientList.get(position).getAmount()+" ";
		}
		if(!ingredientList.get(position).getAmount2().equals("0/16"))
			amount2 = ingredientList.get(position).getAmount2();
		if(!ingredientList.get(position).getPreparation().equals(""))
			preparation = ingredientList.get(position).getPreparation() + " ";
		if(ingredientList.get(position).isOptional())
			optional = " (If Desired)";
		TextView name = (TextView) view.findViewById(R.id.new_ingredient_name);
		name.setText(amount1 + amount2 + " " + ingredientList.get(position).getMeasureType()
				+ " " + preparation + ingredientList.get(position).getName() + optional);
		return view;
	}
}
