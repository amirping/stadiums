package com.example.pingghost.stadiums;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pingghost.stadiums.adapter.ContractAdapter;
import com.example.pingghost.stadiums.model.Contract;
import com.example.pingghost.stadiums.remote.APIUtils;
import com.example.pingghost.stadiums.remote.ContractService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ContractService contractService;
    private List<Contract> list_of_contracts;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        contractService = APIUtils.getContractService();
        prefs =  this.getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        // get the user contracts
        String user_id = prefs.getString("current_user_id","00_no_id_00");
        if(prefs.contains("current_user_id") && !user_id.equals("00_no_id_00")){
            Call<JsonObject> call = contractService.getUserContracts(user_id);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){
                        JsonElement je = response.body();
                        String value = je.getAsJsonObject().get("message").getAsString();
                        Log.e("CONTRACTS", "onResponse: "+value );
                        if(value.equals("ok")){
                            JsonArray cons = je.getAsJsonObject().get("contracts").getAsJsonArray();
                            for (JsonElement cr : cons) {
                                JsonObject contractObj = cr.getAsJsonObject();
                                String  id = contractObj.get("_id").getAsString();
                                String stadium_id = contractObj.get("stade").getAsString();
                                String stat = contractObj.get("stat").getAsString();
                                String user_id = contractObj.get("user").getAsString();
                                String match_date = contractObj.get("match_date").getAsString();
                                String date = contractObj.get("date").getAsString();
                                Contract c = new Contract(id,stadium_id,stat,new Date(date),new Date(match_date),user_id);
                                list_of_contracts.add(c);
                            }


                        }else if(value.equals("no_contract")){
                            //Toast.makeText(ContractActivity.this, "You don't have any Contract", Toast.LENGTH_SHORT).show();
                            Snackbar.make(getCurrentFocus(),"You don't have contracts",Snackbar.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ContractActivity.this, "We have problems dude , sorry", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(ContractActivity.this, "We got troubles , try later", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(ContractActivity.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    //startActivity(i);
                }
            });
        }
        else{
            Intent i = new Intent(this, MainActivity.class);
            prefs.edit().remove("token_app_acc").apply();
            prefs.edit().remove("current_user_id").apply();
            Toast.makeText(this, "login again please", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contract, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private RecyclerView rs;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_contract, container, false);
            rs = rootView.findViewById(R.id.r_v);
            List<Contract> _list = new ArrayList<>();
            _list.add(new Contract("s","Solos,Manzah","ok",new Date(),new Date(),"00"));
            ContractAdapter adapter = new ContractAdapter(this.getContext(),_list);
            rs.setAdapter(adapter);
            rs.setLayoutManager(new LinearLayoutManager(this.getContext()));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
// TODO: 10/12/18 Create cancel button when the contract still open  
// TODO: 10/12/18 show date in correct way  
// TODO: 10/12/18 add click event to show all details about contract & stadium  
