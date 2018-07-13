package com.example.ahmedsayed.a7esbat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import Adapter.pagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.pager1);
        setupAdpter(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    public void setupAdpter(ViewPager viewPager) {

        pagerAdapter adapter = new pagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new Main_fragmenet(), "top");
        adapter.addFragments(new second_fragment(), "second");
        viewPager.setAdapter(adapter);
    }


}
