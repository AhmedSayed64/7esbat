package com.example.ahmedsayed.a7esbat;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomerRecyclerAdapter;
import data.DataBaseHelper;
import model.CustomersData;

public class DeatilsActivity extends AppCompatActivity {

    CustomerRecyclerAdapter recyclerAdapter;
    List<CustomersData> customersData, C_Data;
    DataBaseHelper dbh;
    int customer_pos;
    public static final String EXTRA_CUSTOMER_ID = "customerId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);

        forceRTLIfSupported();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customer_pos = extras.getInt(EXTRA_CUSTOMER_ID);
        }
        Log.d("test_id", String.valueOf(customer_pos));
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        dbh = new DataBaseHelper(this);
        customersData = dbh.get_customers();
        RecyclerView recyclerView = findViewById(R.id.details_list);
        C_Data = new ArrayList<>();
        C_Data.add(customersData.get(customer_pos));
        getSupportActionBar().setTitle(C_Data.get(0).getCustomerList().get(0).getCustomer_name());

        recyclerAdapter = new CustomerRecyclerAdapter(this, C_Data);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}
