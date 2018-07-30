package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedsayed.a7esbat.Add_detailsActivity;
import com.example.ahmedsayed.a7esbat.DeatilsActivity;
import com.example.ahmedsayed.a7esbat.R;

import java.util.ArrayList;
import java.util.List;

import model.CustomersData;

public class CustomersListAdapter extends RecyclerView.Adapter<CustomersListAdapter.ViewHolder> {


    private List<CustomersData> customerList = new ArrayList<>();
    private int layoutResource;
    private Context mContext;

    public CustomersListAdapter(Context mcContext, List<CustomersData> customerList, int layoutResource) {
        this.customerList = customerList;
        this.layoutResource = layoutResource;
        this.mContext = mcContext;
    }


    @Override
    public CustomersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(mContext).inflate(layoutResource, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(CustomersListAdapter.ViewHolder holder, final int position) {

        CustomersData mCustomers = customerList.get(position);

        holder.name.setText(mCustomers.getName());
        holder.credit.setText(Double.toString(mCustomers.getCustomerDetailsList().get(0).getCredit()));
        holder.count.setText("25");


        holder.mroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DeatilsActivity.class);
                intent.putExtra(DeatilsActivity.EXTRA_CUSTOMER_ID, position);
                mContext.startActivity(intent);
            }
        });

        holder.add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "ADD CLICK !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, Add_detailsActivity.class);
                intent.putExtra(Add_detailsActivity.EXTRA_CUSTOMER_ID, position);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, credit, count;
        ImageView status, add_btn;
        RelativeLayout mroot;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.customer_name);
            credit = itemView.findViewById(R.id.credit_tv);
            count = itemView.findViewById(R.id.item_count);
            status = itemView.findViewById(R.id.status_IV);
            add_btn = itemView.findViewById(R.id.add_btn2);
            mroot = itemView.findViewById(R.id.root);

        }
    }


}
