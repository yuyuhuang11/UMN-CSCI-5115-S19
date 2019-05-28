package com.example.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Results extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View inf = inflater.inflate(R.layout.fragment_menu_results, container, false);
        TextView beds = (TextView) inf.findViewById(R.id.beds);
        beds.setText("Beds: " + getArguments().getString("bedrooms"));
        TextView location = (TextView) inf.findViewById(R.id.location);
        location.setText("Location: " + getArguments().getString("location"));
        TextView baths = (TextView) inf.findViewById(R.id.baths);
        baths.setText("Baths: " + getArguments().getString("baths"));
        TextView furnished = (TextView) inf.findViewById(R.id.furnished);
        furnished.setText(getArguments().getString("furnished"));
        TextView price = (TextView) inf.findViewById(R.id.price);
        price.setText("Max Price: " + getArguments().getString("price"));
        return inf;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Results");
    }

}
