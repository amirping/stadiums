package com.example.pingghost.stadiums;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pingghost on 17/11/18.
 */

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.MyViewHolder> {
    Context mContext;
    List<Contract> mList;
    public ContractAdapter(Context mContext, List<Contract> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.contract_item,parent,false);
        return new ContractAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView con_date,con_stad,con_stat;
        public MyViewHolder(View itemView) {
            super(itemView);
            con_date = itemView.findViewById(R.id.con_date);
            con_stad = itemView.findViewById(R.id.con_stadium);
            con_stat = itemView.findViewById(R.id.con_stat);
        }
    }
}
