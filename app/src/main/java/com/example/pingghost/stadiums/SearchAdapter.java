package com.example.pingghost.stadiums;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pingghost on 08/12/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    Context mContext;
    List<search_item> mList;
    public SearchAdapter(Context mContext, List<search_item> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.contract_item,parent,false);
        return new SearchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView s_location,s_name,s_open,s_open_time,s_score;
        public MyViewHolder(View itemView) {
            super(itemView);
            s_location = itemView.findViewById(R.id.s_location);
            s_name = itemView.findViewById(R.id.s_name);
            s_open = itemView.findViewById(R.id.s_open);
            s_open_time = itemView.findViewById(R.id.s_open_time);
            s_score = itemView.findViewById(R.id.s_score);
        }
    }
}
