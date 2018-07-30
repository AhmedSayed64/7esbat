package com.example.ahmedsayed.a7esbat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomerRecyclerAdapter;
import data.DataBaseHelper;
import model.CustomersData;

public class DeatilsActivity extends AppCompatActivity implements myInterface {

    public static final String EXTRA_CUSTOMER_ID = "customerId";
    CustomerRecyclerAdapter recyclerAdapter;
    List<CustomersData> customersData, C_Data;
    DataBaseHelper dbh;
    int customer_pos;
    Button btn;
    ActionMode actionMode;
    int details_pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customer_pos = extras.getInt(EXTRA_CUSTOMER_ID);
        }
        Log.d("test_id", String.valueOf(customer_pos));
        btn = findViewById(R.id.btn);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        dbh = new DataBaseHelper(this);
        customersData = dbh.get_customers();
        RecyclerView recyclerView = findViewById(R.id.details_list);
        C_Data = new ArrayList<>();
        C_Data.add(customersData.get(customer_pos));
        getSupportActionBar().setTitle(C_Data.get(0).getName());

        recyclerAdapter = new CustomerRecyclerAdapter(this, C_Data.get(0).getCustomerDetailsList());
        recyclerAdapter.setLiset(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();


    }

    @Override
    public void onSaved() {

    }

    @Override
    public void onLongPressed(int pos) {
        details_pos = pos;
        actionMode = DeatilsActivity.this.startSupportActionMode(new ContextualCallback());
        Toast.makeText(this, "Long", Toast.LENGTH_SHORT).show();

    }


    class ContextualCallback implements ActionMode.Callback {


        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            mode.getMenuInflater().inflate(R.menu.test, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("EDittt");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.edit:
                    Toast.makeText(getApplicationContext(), "from Edit", Toast.LENGTH_SHORT).show();
                    Edit_Dialog dialog = new Edit_Dialog();
                    dialog.show(getFragmentManager(), "dialog");
                    dialog.setLiset(DeatilsActivity.this, customer_pos, details_pos);

                    mode.finish();
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }

}
