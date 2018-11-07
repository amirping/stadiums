package com.example.pingghost.stadiums;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private RecyclerView menu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menu = findViewById(R.id.menu);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        List<Menu_item> menu_list = new ArrayList<>();
        menu_list.add(new Menu_item("Search Stadiums",1,R.drawable.search_stadiums,R.drawable.stadium));
        menu_list.add(new Menu_item("User Contract",1,R.drawable.contract,R.drawable.writeletter));
        menu_list.add(new Menu_item("Access Profile",1,R.drawable.profile,R.drawable.user));
        menu_list.add(new Menu_item("Log-out",1,R.drawable.exit,R.drawable.logout));
        MenuAdapter adapter = new MenuAdapter(this,menu_list);
        menu.setAdapter(adapter);
        menu.setLayoutManager(new LinearLayoutManager(this));
    }

}
