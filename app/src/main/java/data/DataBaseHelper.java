package data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.CustomerDetails;
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
                "(" + Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Constants.Customer_Name + " TEXT," +
                Constants.Customer_Details + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String drop = "DROP TABLE IF EXISTS" + Constants.TABLE_CUSTOMER;
        db.execSQL(drop);
        onCreate(db);
    }

    public void add_customer(CustomersData customersData) {

        SQLiteDatabase db = this.getWritableDatabase();

        Gson gson = new Gson();
        String Customer_details = gson.toJson(customersData.getCustomerDetailsList());

        ContentValues values = new ContentValues();
        values.put(Constants.Customer_Name, customersData.getName());
        values.put(Constants.Customer_Details, Customer_details);

        db.insert(Constants.TABLE_CUSTOMER, null, values);

        Log.d("customer_name", customersData.getName());
        Log.d("customer_details", Customer_details);

        db.close();

    }

    public ArrayList<CustomersData> get_customers() {


        customersDataList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Gson gson = new Gson();
        Cursor cursor = db.query(Constants.TABLE_CUSTOMER, new String[]{Constants.KEY_ID, Constants.Customer_Name, Constants.Customer_Details},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {

                CustomersData customersData = new CustomersData();
                int id = cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(Constants.Customer_Name));
                String details = cursor.getString(cursor.getColumnIndex(Constants.Customer_Details));

                Type type = new TypeToken<List<CustomerDetails>>() {
                }.getType();
                List<CustomerDetails> finalOutputString = gson.fromJson(details, type);

                customersData.setId(id);
                customersData.setName(name);
                customersData.setCustomerDetailsList(finalOutputString);

                customersDataList.add(customersData);


                Log.d("name_", name);
//                Type collectionType = new TypeToken<Collection<Customer>>(){}.getType();
//                Collection<Customer> enums = gson.fromJson(name, collectionType);
//
//                customerList = Arrays.asList(gson.fromJson(name, Customer[].class));
//                customersData.setCustomerList(customerList);
//                customersData.setId(id);
//                for (int i = 0; i < customerList.size(); i++) {
//                    Log.d("m_customer", customersData.getCustomerList().get(i).getCustomer_name());
//                    Log.d("m_customer", String.valueOf(customersData.getId()));
//
//                }

            } while (cursor.moveToNext());
        }

//        for (int i = 0; i < customersDataList.size(); i++) {
//            Log.d("Data_customer", customersDataList.get(i).getCustomerList().get(0).getCustomer_name());
//            Log.d("Data_customer", String.valueOf(customersDataList.get(i).getId()));
//
//        }

        cursor.close();
        db.close();

        return customersDataList;
    }

//    public void customer_deatils(CustomersData customersData, int position) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        position += 1;
//
//        customersData.setCustomerDetailsList();
//        ContentValues values = new ContentValues();
//        values.put(Constants.Customer_Details, customerDetails);
//
//
//    }

    public void update_details(List<CustomersData> customersDataList, int pos) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        List<CustomerDetails> details = customersDataList.get(pos).getCustomerDetailsList();
        String Customer_details = gson.toJson(details);

        values.put(Constants.Customer_Details, Customer_details);
        Log.d("new_customer", Customer_details);

        pos += 1;
        Log.d("new_customerID", Integer.toString(pos));
        db.update(Constants.TABLE_CUSTOMER, values, Constants.KEY_ID + " = ?", new String[]{Integer.toString(pos)});


    }


}
