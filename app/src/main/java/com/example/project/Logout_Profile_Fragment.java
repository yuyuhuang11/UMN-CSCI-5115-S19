package com.example.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Logout_Profile_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logout_profile, container, false);

        Button but_fb=(Button)view.findViewById(R.id.button_fb);
        but_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Login_Profile_Fragment());
                fr.commit();
            }
        });

        Button but_wechat=(Button)view.findViewById(R.id.button_wechat);
        but_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Login_Profile_Fragment());
                fr.commit();
            }
        });

        Button but_gmail=(Button)view.findViewById(R.id.button_gmail);
        but_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Login_Profile_Fragment());
                fr.commit();
            }
        });
        return view;
    }
}
