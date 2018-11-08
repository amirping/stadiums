package com.example.pingghost.stadiums;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pingghost on 07/11/18.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    Context mContext;
    List<Menu_item> mList;
    public SharedPreferences prefs;
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.r_back.setImageResource(mList.get(position).getBack_img());
        holder.menu_item_name.setText(mList.get(position).getItemName());
        holder.icon.setImageResource(mList.get(position).getIcon());
        holder.descrep.setText(mList.get(position).getDescrip());
        holder.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mList.get(position).getDirection()){
                    case "search":{
                        Toast.makeText(mContext, "Still in dev MODE", Toast.LENGTH_SHORT).show();
                    }break;
                    case  "profile":{
                        Intent i = new Intent(mContext, ProfileActivity.class);
                        mContext.startActivity(i);
                    }break;
                    case "logout":{
                        // delete token -> return to main
                        prefs =  mContext.getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY",Context.MODE_PRIVATE);
                        prefs.edit().remove("token_app_acc").apply();
                        Intent i = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(i);
                        
                    }break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView r_back,icon;
        TextView menu_item_name,descrep;
        ConstraintLayout all;
        public MyViewHolder(View itemView) {
            super(itemView);
            r_back = itemView.findViewById(R.id.rBack);
            menu_item_name = itemView.findViewById(R.id.menu_name);
            icon = itemView.findViewById(R.id.icon_menu);
            descrep = itemView.findViewById(R.id.descrip);
            all = itemView.findViewById(R.id.all);
        }
    }
}
