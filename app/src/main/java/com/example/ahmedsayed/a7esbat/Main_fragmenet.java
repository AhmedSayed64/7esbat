package com.example.ahmedsayed.a7esbat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import Adapter.CustomersListAdapter;
import data.DataBaseHelper;
import model.CustomersData;


public class Main_fragmenet extends Fragment implements myInterface {

    List<CustomersData> customersData;
    CustomersListAdapter adapter;
    DataBaseHelper dbh;

    public Main_fragmenet() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("LOG_f", "from create");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dbh = new DataBaseHelper(getActivity().getApplicationContext());
        customersData = dbh.get_customers();
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        adapter = new CustomersListAdapter(getActivity(), customersData, R.layout.list_row);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
//        Log.d("LOG_f", "from createView");

//        adapter.notifyDataSetChanged();


        return recyclerView;
    }


    @Override
    public void onSaved() {
        Log.d("LOG_f", "from B_save");
        //customersData.clear();
        customersData = dbh.get_customers();
        //Log.d("t_cus", customersData.get(customersData.size() - 1).getCustomerList().get(0).getCustomer_name());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLongPressed(int pos) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_btn:
                Custom_Dialog dialog = new Custom_Dialog();
                dialog.show(getFragmentManager(), "dialog");
                dialog.setLiset(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }
}
