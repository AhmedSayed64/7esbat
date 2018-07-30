package Adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmedsayed.a7esbat.R;
import com.example.ahmedsayed.a7esbat.myInterface;

import java.util.List;

import model.CustomerDetails;

public class CustomerRecyclerAdapter extends RecyclerView.Adapter<CustomerRecyclerAdapter.myViewHolder> {


    private Context context;
    private myInterface myInterface;
    private List<CustomerDetails> customerDetailsList;

    public CustomerRecyclerAdapter(Context context, List<CustomerDetails> customerDetailsList) {
        this.context = context;
        this.customerDetailsList = customerDetailsList;
    }

    public void setLiset(myInterface myInterface) {
        this.myInterface = myInterface;
    }

    @Override
    public void onBindViewHolder(final CustomerRecyclerAdapter.myViewHolder holder, final int position) {

        CustomerDetails mCustomer = customerDetailsList.get(position);
        holder.date.setText(mCustomer.getDate());


        holder.details.setText(mCustomer.getDetails());

        holder.cost.setText(String.valueOf(mCustomer.getCost()));

        holder.credit.setText(String.valueOf(mCustomer.getCredit()));

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.root.setBackgroundColor(Color.GRAY);
                myInterface.onLongPressed(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });


    }

    @Override
    public CustomerRecyclerAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.details_row, parent, false);
        return new myViewHolder(mView);
    }

    @Override
    public int getItemCount() {
        return customerDetailsList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView date, credit, cost, details;
        ImageView status, edit_btn;
        LinearLayout root;

        public myViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.details_date2);
            credit = itemView.findViewById(R.id.details_credit2);
            cost = itemView.findViewById(R.id.details_cost2);
            details = itemView.findViewById(R.id.details_details2);
            status = itemView.findViewById(R.id.details_status2);
            root = itemView.findViewById(R.id.row);
            edit_btn = itemView.findViewById(R.id.edit_btn);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            myInterface.onLongPressed(getAdapterPosition());
            itemView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }

}
