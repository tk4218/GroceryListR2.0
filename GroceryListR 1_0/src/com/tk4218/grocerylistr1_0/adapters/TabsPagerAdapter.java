package com.tk4218.grocerylistr1_0.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tk4218.grocerylistr1_0.CalendarFragment;
import com.tk4218.grocerylistr1_0.GroceryListFragment;
import com.tk4218.grocerylistr1_0.RecipeFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
        switch (index) {
        case 0:
            return (Fragment) new CalendarFragment();
        case 1:
            return new RecipeFragment();
        case 2:
            return new GroceryListFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}
