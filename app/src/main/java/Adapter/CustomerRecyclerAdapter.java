package Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmedsayed.a7esbat.R;

import java.util.ArrayList;
import java.util.List;

import model.CustomersData;

public class CustomerRecyclerAdapter extends RecyclerView.Adapter<CustomerRecyclerAdapter.myViewHolder> {


    private Context context;
    private List<CustomersData> customerList = new ArrayList<>();

    public CustomerRecyclerAdapter(Context context, List<CustomersData> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView date, credit, cost, details;
        ImageView status;

        public myViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.details_date2);
            credit = itemView.findViewById(R.id.details_credit2);
            cost = itemView.findViewById(R.id.details_cost2);
            details = itemView.findViewById(R.id.details_details2);
            status = itemView.findViewById(R.id.details_status2);


        }
    }

    @Override
    public CustomerRecyclerAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.details_row, parent, false);
        return new myViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final CustomerRecyclerAdapter.myViewHolder holder, int position) {

        CustomersData mCustomer = customerList.get(position);
        holder.date.setText(mCustomer.getCustomerList().get(0).getDate());


        holder.details.setText(mCustomer.getCustomerList().get(0).getDetails());

        holder.cost.setText(String.valueOf(mCustomer.getCustomerList().get(0).getCost()));

        holder.credit.setText(String.valueOf(mCustomer.getCustomerList().get(0).getCredit()));


    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }
}
