package com.tk4218.grocerylistr1_0;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GroceryListFragment extends Fragment{
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    	this.setHasOptionsMenu(true);
       
    	return inflater.inflate(R.layout.calendar_list_item_layout, container, false);
	}

}
