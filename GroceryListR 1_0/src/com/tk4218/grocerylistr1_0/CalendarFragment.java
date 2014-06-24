package com.tk4218.grocerylistr1_0;

import com.tk4218.grocerylistr1_0.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class CalendarFragment extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        return inflater.inflate(R.layout.calendar_layout, container, false);
    }
}
