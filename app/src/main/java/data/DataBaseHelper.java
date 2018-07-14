package data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Customer;
import model.CustomersData;

public class DataBaseHelper extends SQLiteOpenHelper {

    private List<Customer> customerList;
    private ArrayList<CustomersData> customersDataList = new ArrayList<>();

    public DataBaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_CUSTOMER +
                "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Constants.Customer_Name + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String drop = "DROP TABLE IF EXISTS" + Constants.TABLE_CUSTOMER;
        db.execSQL(drop);
        onCreate(db);
    }

    public void add_customer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        Gson gson = new Gson();
        String Customer_JSON = gson.toJson(customerList);
        ContentValues values = new ContentValues();
        values.put(Constants.Customer_Name, Customer_JSON);
        db.insert(Constants.TABLE_CUSTOMER, null, values);
        Log.d("customer_tt", Customer_JSON.toString());
        db.close();

    }

    public ArrayList<CustomersData> get_customers() {


        customersDataList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Gson gson = new Gson();
        Cursor cursor = db.query(Constants.TABLE_CUSTOMER, new String[]{Constants.KEY_ID, Constants.Customer_Name},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {

                CustomersData customersData = new CustomersData();
                int id = cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(Constants.Customer_Name));
                Log.d("name_", name);
//                Type collectionType = new TypeToken<Collection<Customer>>(){}.getType();
//                Collection<Customer> enums = gson.fromJson(name, collectionType);

                customerList = Arrays.asList(gson.fromJson(name, Customer[].class));
                customersData.setCustomerList(customerList);
                customersData.setId(id);
                for (int i = 0; i < customerList.size(); i++) {
                    Log.d("m_customer", customersData.getCustomerList().get(i).getCustomer_name());
                    Log.d("m_customer", String.valueOf(customersData.getId()));

                }

                customersDataList.add(customersData);
            } while (cursor.moveToNext());
        }

        for (int i = 0; i < customersDataList.size(); i++) {
            Log.d("Data_customer", customersDataList.get(i).getCustomerList().get(0).getCustomer_name());
            Log.d("Data_customer", String.valueOf(customersDataList.get(i).getId()));

        }

        cursor.close();
        db.close();

        return customersDataList;
    }

    public void customer_deatils(Customer customer, int position) {

        SQLiteDatabase db = this.getReadableDatabase();
        position += 1;

        Cursor cursor = db.query(Constants.TABLE_CUSTOMER, new String[]{Constants.Customer_Name},
                Constants.KEY_ID + " = ?", new String[]{Integer.toString(position)}, null, null, null);

        if (cursor.moveToFirst()) {
            String JsonName = cursor.getString(0);

            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            Gson gson = new Gson();
            String Customer_JSON = gson.toJson(customerList);


            List<String> customers_strings = new ArrayList<>();
            customers_strings.add(JsonName);
            customers_strings.add(Customer_JSON);

            update_details(customers_strings.toString(), position);

            Log.d("test_arr", customers_strings.toString());

            Log.d("aysyy", JsonName);
            Log.d("c_name", customer.getCustomer_name());
        }

    }

    public void update_details(String customerStrings, int pos) {
        SQLiteDatabase db = this.getWritableDatabase();

        pos += 1;
        ContentValues values = new ContentValues();
        values.put(Constants.Customer_Name, customerStrings);
        Log.d("new_customer", customerStrings);

        db.update(Constants.TABLE_CUSTOMER, values, Constants.KEY_ID + " = ?", new String[]{Integer.toString(pos)});


    }


}
