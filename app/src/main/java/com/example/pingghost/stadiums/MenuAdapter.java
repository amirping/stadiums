package com.example.pingghost.stadiums;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pingghost on 07/11/18.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    Context mContext;
    List<Menu_item> mList;

    public MenuAdapter(Context mContext, List<Menu_item> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.home_menu_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.r_back.setImageResource(mList.get(position).getBack_img());
        holder.menu_item_name.setText(mList.get(position).getItemName());
        holder.icon.setImageResource(mList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView r_back,icon;
        TextView menu_item_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            r_back = itemView.findViewById(R.id.rBack);
            menu_item_name = itemView.findViewById(R.id.menu_name);
            icon = itemView.findViewById(R.id.icon_menu);
        }
    }
}
