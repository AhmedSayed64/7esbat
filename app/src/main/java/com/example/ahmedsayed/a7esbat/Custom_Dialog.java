package com.example.ahmedsayed.a7esbat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import data.DataBaseHelper;
import model.Customer;


public class Custom_Dialog extends DialogFragment {


    public static Boolean save_clicked;
    final Calendar c = Calendar.getInstance();
    DatePickerDialog datePickerDialog;
    Boolean out_B, in_B;
    RelativeLayout c_in, c_out;
    TextView c_save, c_cancel;
    Customer myCustomer;
    DataBaseHelper dataBaseHelper;
    int c_status;
    List<Customer> customerList;
    private EditText c_name, c_cost, c_date, c_details;
    private myInterface myInterface;

    public void setLiset(myInterface myInterface) {
        this.myInterface = myInterface;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View mView = inflater.inflate(R.layout.add_dialog, null);
        c_name = mView.findViewById(R.id.c_name);
        c_cost = mView.findViewById(R.id.c_cost);
        c_name.requestFocus();
        c_details = mView.findViewById(R.id.c_details);
        c_date = mView.findViewById(R.id.c_date);
        c_out = mView.findViewById(R.id.c_out);
        c_in = mView.findViewById(R.id.c_in);
        c_save = mView.findViewById(R.id.c_save);
        c_cancel = mView.findViewById(R.id.c_cancel);

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


        c_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c_save.setBackgroundColor(Color.GRAY);
                myCustomer = new Customer(c_name.getText().toString(), c_status, Double.parseDouble(c_cost.getText().toString()),
                        500, c_details.getText().toString(), c_date.getText().toString());
                customerList = new ArrayList<>();
                customerList.add(myCustomer);
                for (int i = 0; i < customerList.size(); i++)
                    Log.d("customerArr", customerList.get(i).getCustomer_name().toString());
                dataBaseHelper = new DataBaseHelper(getActivity().getApplicationContext());
                dataBaseHelper.add_customer(myCustomer);
                myInterface.onSaved();
                dismiss();


            }
        });
        c_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c_cancel.setBackgroundColor(Color.GRAY);

                Toast.makeText(getActivity(), " Cancelled !", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });

        builder.setView(mView);

        return builder.create();
    }

    void show_date() {
        int mYear = c.get(Calendar.YEAR); // current year
        final int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        datePickerDialog = new DatePickerDialog(getActivity(),
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
