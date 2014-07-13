/**
 * This class deals with handling tabs. It chooses the right fragment to display when a tab is chosen.
 */
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
 
    /**
     * Returns the corresponding fragment depending on the index.
     */
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
 
    /**
     * Returns the number of tabs.
     */
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}
