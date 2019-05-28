package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.widget.TextView;
import android.widget.ListView;

import android.support.design.widget.Snackbar;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ListView listView;
    ImageView unfill, fill;
    String check = "unfill";



    private int bedroomNum = -1;
    private String bathroomStatus = "";
    private String furnishedStatus = "";
    private Fragment fragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = new Home_Fragment();
                    break;
                case R.id.nav_chatroom:
                    fragment = new Search_chat();
                    break;
                case R.id.nav_search:
                    fragment = new Search();
                    break;
                case R.id.nav_profile:
                    fragment = new Logout_Profile_Fragment();
                    break;
                case R.id.nav_post:
                    fragment = new Create_Post_Fragment();
                    break;
            }

            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.commit();
            }

            return true;
        }
    };


    public void onSearchClicked (View view) {
        Bundle bundle = new Bundle();
        EditText price = findViewById(R.id.price);
        EditText location = findViewById(R.id.location);
        if(bedroomNum == 0) {
            bundle.putString("bedrooms", "Any");
        }
        else {
            bundle.putString("bedrooms", Integer.toString(bedroomNum));
        }
        bundle.putString("baths", bathroomStatus);
        bundle.putString("furnished", furnishedStatus);
        bundle.putString("price", price.getText().toString());
        bundle.putString("location", location.getText().toString());
        Results resultInfo = new Results();
        resultInfo.setArguments(bundle);
        fragment = resultInfo;
        FragmentTransaction ftRes = getSupportFragmentManager().beginTransaction();
        ftRes.replace(R.id.fragment_container,fragment);
        ftRes.commit();
    }


    public void picClicked (View view) {
        Apartment_page post = new Apartment_page();
        fragment = post;
        FragmentTransaction ftRes = getSupportFragmentManager().beginTransaction();
        ftRes.replace(R.id.fragment_container,fragment);
        ftRes.commit();
    }

    public void moveToChat (View view) {
        Chat_room chat = new Chat_room();
        fragment = chat;
        FragmentTransaction ftRes = getSupportFragmentManager().beginTransaction();
        ftRes.replace(R.id.fragment_container,fragment);
        ftRes.commit();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.anyBeds:
                if (checked)
                    bedroomNum = 0;
                break;
            case R.id.oneBed:
                if (checked)
                    bedroomNum = 1;
                break;
            case R.id.twoBed:
                if (checked)
                    bedroomNum = 2;
                break;
            case R.id.threeBed:
                if (checked)
                    bedroomNum = 3;
                break;
            case R.id.fourBed:
                if (checked)
                    bedroomNum = 4;
                break;
            case R.id.anyBath:
                if (checked)
                    bathroomStatus = "Any";
                break;
            case R.id.sharedBath:
                if (checked)
                    bathroomStatus = "Shared";
                break;
            case R.id.privateBath:
                if (checked)
                    bathroomStatus = "Private";
                break;
            case R.id.furnished:
                if (checked)
                    furnishedStatus = "Furnished";
                break;
            case R.id.notFurnished:
                if (checked)
                    furnishedStatus = "Not Furnished";
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,fragment);
        fragmentTransaction.commit();



        fill = (ImageView) findViewById(R.id.filled);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_m);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new Home_Fragment());
        ft.commit();



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void unfilled(View view){
        if (check.equals("unfill"))
        {
            animateHeart(fill);
            fill.setVisibility(View.VISIBLE);
            unfill.setVisibility(View.GONE);
        }
        Snackbar.make(view,"Like it", Snackbar.LENGTH_LONG).setAction("Action",null).show();
        check = "fill";
    }
    public void filled(View view){
        if (check.equals("fill"))
        {
            animateHeart(unfill);
            fill.setVisibility(View.GONE);
            unfill.setVisibility(View.VISIBLE);
        }
        Snackbar.make(view,"Dislike it", Snackbar.LENGTH_LONG).setAction("Action",null).show();
        check = "unfill";
    }
    private static Animation preparation (Animation animation){
        animation.setRepeatCount(0);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }

    public static void dislikeheart(final ImageView view){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        prepareAnimation(scaleAnimation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        prepareAnimation(alphaAnimation);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(700);
        view.startAnimation(animation);
    }

    public static void animateHeart(final ImageView view){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        prepareAnimation(scaleAnimation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        prepareAnimation(alphaAnimation);

        AnimationSet animation = new AnimationSet(true);
        animation.addAnimation(alphaAnimation);
        animation.addAnimation(scaleAnimation);
        animation.setDuration(500);
        view.startAnimation(animation);
    }

    public static Animation prepareAnimation(Animation animation){
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }

    public void toPost(View view){
        Intent intent = new Intent(MainActivity.this,Apartment_page.class);
        startActivity(intent);
    }


}
