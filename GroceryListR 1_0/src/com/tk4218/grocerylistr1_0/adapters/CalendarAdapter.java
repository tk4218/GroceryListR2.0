package com.tk4218.grocerylistr1_0.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tk4218.grocerylistr1_0.R;
import com.tk4218.grocerylistr1_0.model.CalendarDay;
import com.tk4218.grocerylistr1_0.model.Recipe;

public class CalendarAdapter extends ArrayAdapter<Recipe>{
	Context context;
	ArrayList<Recipe> calendarRecipes;
	CalendarDay dayOfWeek;
	public CalendarAdapter(Context context, ArrayList<Recipe> items) {
		super(context, R.layout.calendar_list_item_layout, items);
		this.context = context;
		calendarRecipes = items;
		dayOfWeek = new CalendarDay();
	}
	
	@Override
	public int getCount(){
		return calendarRecipes.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = convertView;
		if(convertView == null)
			view = inflater.inflate(R.layout.calendar_list_item_layout, null);
		TextView weekday = (TextView)view.findViewById(R.id.dayOfWeek);
		weekday.setText(dayOfWeek.getWeekDay(position));
		
		TextView recipeName = (TextView)view.findViewById(R.id.calendarRecipeName);
		TextView recipeDescription = (TextView)view.findViewById(R.id.calendarRecipeDescription);
		if(!calendarRecipes.get(position).getName().equals("")){
			recipeName.setText(calendarRecipes.get(position).getName());
			recipeDescription.setText(calendarRecipes.get(position).getDescription());
		}
		return view;
	}
}
