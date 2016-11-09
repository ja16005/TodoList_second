package com.example.jahnavi.todolist;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    ViewPager viewpager;
    SwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewpager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new SwipeAdapter(this);
        viewpager.setAdapter(adapter);
        Intent i = getIntent();
        int pos = i.getExtras().getInt("position");
        viewpager.setCurrentItem(pos);

        Log.d("SecondActivity", "second");
    }


}
