package com.tk4218.grocerylistr1_0.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.tk4218.grocerylistr1_0.model.Recipe;
public class CalendarAdapter extends ArrayAdapter<Recipe>{

	public CalendarAdapter(Context context, int resource, int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}

}
