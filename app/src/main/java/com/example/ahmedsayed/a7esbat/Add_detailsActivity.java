package com.example.ahmedsayed.a7esbat;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapter.CustomerRecyclerAdapter;
import data.DataBaseHelper;
import model.Customer;
import model.CustomerDetails;
import model.CustomersData;

public class Add_detailsActivity extends AppCompatActivity {

    public static final String EXTRA_CUSTOMER_ID = "customerId";
    final Calendar c = Calendar.getInstance();
    Boolean out_B, in_B;
    DatePickerDialog datePickerDialog;
    int c_status;
    Customer myCustomer;
    List<Customer> customerList;
    private CustomerRecyclerAdapter recyclerAdapter;
    CustomersData customersData;
    private DataBaseHelper dbh;
    private int customer_pos;
    private EditText c_name, c_cost, c_date, c_details;
    private RelativeLayout c_in, c_out;
    private TextView c_save, c_cancel, add_customer;
    CustomerDetails customerDetails;
    List<CustomerDetails> customerDetailsList;
    private List<CustomersData> customersDataList, C_Data, testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        c_name = findViewById(R.id.c_name);
        c_cost = findViewById(R.id.c_cost);
        c_cost.requestFocus();
        c_details = findViewById(R.id.c_details);
        c_date = findViewById(R.id.c_date);
        c_out = findViewById(R.id.c_out);
        c_in = findViewById(R.id.c_in);
        c_save = findViewById(R.id.c_save);
        c_cancel = findViewById(R.id.c_cancel);
        add_customer = findViewById(R.id.add_customer_TV);
        add_customer.setVisibility(View.GONE);

        c_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in_B = true;
                if (in_B) {
                    c_in.setBackground(getResources().getDrawable(R.drawable.bghover_raduis));
                    c_out.setBackground(getResources().getDrawable(R.drawable.bg_raduis));
                    c_status = 0;
                    out_B = false;
                }
            }
        });
        c_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                out_B = true;
                if (out_B) {
                    c_out.setBackground(getResources().getDrawable(R.drawable.bghover_raduis));
                    c_in.setBackground(getResources().getDrawable(R.drawable.bg_raduis));
                    c_status = 1;
                    in_B = false;

                }
            }
        });
        c_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_date();
            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customer_pos = extras.getInt(EXTRA_CUSTOMER_ID);
        }

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        dbh = new DataBaseHelper(this);
        customersDataList = dbh.get_customers();
        RecyclerView recyclerView = findViewById(R.id.customer_details);
        C_Data = new ArrayList<>();
        C_Data.add(customersDataList.get(customer_pos));

        getSupportActionBar().setTitle(C_Data.get(0).getName());
        c_name.setText(C_Data.get(0).getName());
        c_name.setEnabled(false);

        c_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customerDetails = new CustomerDetails(c_status, Double.parseDouble(c_cost.getText().toString()),
                        500, c_details.getText().toString(), c_date.getText().toString());

                Log.d("new_customerID2", Integer.toString(customer_pos));

                customersDataList.get(customer_pos).getCustomerDetailsList().add(customerDetails);

                dbh.update_details(customersDataList, customer_pos);
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        testList = dbh.get_customers();

        List<CustomerDetails> details = testList.get(customer_pos).getCustomerDetailsList();
        recyclerAdapter = new CustomerRecyclerAdapter(this, details);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    void show_date() {
        int mYear = c.get(Calendar.YEAR); // current year
        final int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        int month = monthOfYear + 1;
                        c_date.setText(dayOfMonth + "/"
                                + month + "/" + year);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }
}
