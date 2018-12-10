package com.example.pingghost.stadiums;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pingghost.stadiums.model.User;
import com.example.pingghost.stadiums.remote.APIUtils;
import com.example.pingghost.stadiums.remote.UserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Personal Information",
                        "Security Information",
                }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                if(position == 0 )
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, PersonalDataFragment.newInstance(position + 1))
                            .commit();
                }
                else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                            .commit();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Help Section will be added soon", Snackbar.LENGTH_LONG)
                       .show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                // Inflate the drop down using the helper's LayoutInflater
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private Button saveEmail,saveChanges;
        private EditText newEmail,old_passwd,new_passwd;
        private Switch enableEdit;
        private static final String ARG_SECTION_NUMBER = "section_number";
        private UserService userService;
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
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            userService =  APIUtils.getUserService();
            saveEmail = rootView.findViewById(R.id.email_change);
            saveChanges = rootView.findViewById(R.id.saveSecurity);
            newEmail = rootView.findViewById(R.id.new_email);
            old_passwd = rootView.findViewById(R.id.old_pass);
            new_passwd = rootView.findViewById(R.id.new_pass);
            enableEdit = rootView.findViewById(R.id.enableEditSecurity);
            saveChanges.setEnabled(false);
            saveEmail.setEnabled(false);
            newEmail.setEnabled(false);
            old_passwd.setEnabled(false);
            new_passwd.setEnabled(false);
            String locid = checkUser();
            enableEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        saveChanges.setEnabled(true);
                        saveEmail.setEnabled(true);
                        newEmail.setEnabled(true);
                        old_passwd.setEnabled(true);
                        new_passwd.setEnabled(true);
                    }else{
                        saveChanges.setEnabled(false);
                        saveEmail.setEnabled(false);
                        newEmail.setEnabled(false);
                        old_passwd.setEnabled(false);
                        new_passwd.setEnabled(false);
                    }
                }
            });
            saveEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // change the email
                    //userService.updateUserPassword();
                    String email = newEmail.getText().toString();
                    if(!email.isEmpty() && email.length()>=5){
                        Call<JsonObject> call = userService.updateUserEmail(locid,email);
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                JsonElement je = response.body();
                                String value = je.getAsJsonObject().get("message").getAsString();
                                Log.e("HELLO OBJECT",value);
                                if(value.equals("update_success")){
                                    Toast.makeText(getActivity(), "Email Changed Dude ", Toast.LENGTH_SHORT).show();
                                }
                                else if(value.equals("used_mail")){
                                    Toast.makeText(getActivity(), "This is Used email !!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getActivity(), "We go bugy , move move !!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(getActivity(), "We got internet Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(getActivity(), "Make sure you provide a correct email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            saveChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // change the password
                    String old_pass,new_pass;
                    old_pass = old_passwd.getText().toString();
                    new_pass = new_passwd.getText().toString();
                    if(!old_pass.isEmpty() && old_pass.length()>=6 && !new_pass.isEmpty() && new_pass.length()>=6){
                        // call the service 
                        Call<JsonObject> call = userService.updateUserPassword(locid,new_pass,old_pass);
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                if(response.isSuccessful()){
                                    JsonElement je = response.body();
                                    String value = je.getAsJsonObject().get("message").getAsString();
                                    Log.e("HELLO OBJECT",value);
                                    if(value.equals("update_success")){
                                        Toast.makeText(getActivity(), "Password Has been Changed", Toast.LENGTH_SHORT).show();
                                        // clear edit box
                                        new_passwd.setText("");
                                        old_passwd.setText("");
                                    }else if(value.equals("wrong_password")){
                                        Toast.makeText(getActivity(), "Wrong Password , check again", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getActivity(), "We got Problem here , call slim shady", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else{
                                    Toast.makeText(getActivity(), "We got problem Here , try later", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Toast.makeText(getActivity(), "Connection Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(getActivity(), "Password length must be 6 chars or more", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return rootView;
        }
        public String checkUser(){
            Intent i = new Intent(getActivity(), MainActivity.class);
            SharedPreferences prefs = getActivity().getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY",Context.MODE_PRIVATE);
            String locid = prefs.getString("current_user_id","00_no_id_00");
            if(!prefs.contains("current_user_id") || locid.equals("00_no_id_00")){
                prefs.edit().remove("token_app_acc").apply();
                prefs.edit().remove("current_user_id").apply();
                Toast.makeText(getActivity(), "login again please", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
            return locid;
        }
    }
    // PERSONAL USER DATA 
    public static class PersonalDataFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private Button save;
        private EditText last_name,first_name,b_date,location,phone;
        private Switch enable_edit;
        private static final String ARG_SECTION_NUMBER = "section_number";
        private  User user_c ;
        UserService userService ;


        public PersonalDataFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PersonalDataFragment newInstance(int sectionNumber) {
            PersonalDataFragment fragment = new PersonalDataFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.personal_information_fragment, container, false);
            userService = APIUtils.getUserService();
            user_c = new User();
            save = rootView.findViewById(R.id.savePersonal);
            last_name = rootView.findViewById(R.id.last_name);
            first_name = rootView.findViewById(R.id.first_name);
            b_date = rootView.findViewById(R.id.b_date);
            location = rootView.findViewById(R.id.location);
            phone = rootView.findViewById(R.id.phone);
            enable_edit = rootView.findViewById(R.id.enableEditSecurity);
            //----- readonly at start
            save.setEnabled(false);
            last_name.setEnabled(false);
            first_name.setEnabled(false);
            b_date.setEnabled(false);
            location.setEnabled(false);
            phone.setEnabled(false);
            enable_edit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        save.setEnabled(true);
                        last_name.setEnabled(true);
                        first_name.setEnabled(true);
                        b_date.setEnabled(true);
                        location.setEnabled(true);
                        phone.setEnabled(true);
                    }else{
                        save.setEnabled(false);
                        last_name.setEnabled(false);
                        first_name.setEnabled(false);
                        b_date.setEnabled(false);
                        location.setEnabled(false);
                        phone.setEnabled(false);
                    }
                }
            }); // fix here
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // check -> updating
                    String username_s,lastname_s,phone_s,location_s,birthdate_s;
                    try{
                        lastname_s = last_name.getText().toString();
                        phone_s = phone.getText().toString();
                        username_s = first_name.getText().toString();
                        location_s = location.getText().toString();
                        birthdate_s = b_date.getText().toString();
                        if(!lastname_s.isEmpty() && lastname_s.length()>=1 && !username_s.isEmpty()&& username_s.length()>=1 && !phone_s.isEmpty() && phone_s.length()>=8){
                            // start update
                            user_c.setPhone_number(phone_s);
                            user_c.setLast_name(lastname_s);
                            user_c.setFirst_name(username_s);
                            user_c.setLocation(location_s);
                            user_c.setBirthday(birthdate_s);
                            updateUser(user_c);
                        }
                        else{
                            Toast.makeText(getActivity(), "Don't let any required empty", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Log.e("update", "onClick: "+e.getMessage() );
                        Toast.makeText(getActivity(), "Make Sure to fill all required fields", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            // load data
            loadUser(user_c);
            return rootView;
        }
        public void updateView(){
            last_name.setText(user_c.getLast_name());
            first_name.setText(user_c.getFirst_name());
            phone.setText(user_c.getPhone_number());
            b_date.setText(user_c.getBirthday());
            location.setText(user_c.getLocation());
        }
        public void loadUser(User u){
            Intent i = new Intent(getActivity(), MainActivity.class);
            String locid = checkUser();
            Call<JsonObject> call = userService.getUser(locid);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){

                        JsonElement je = response.body();
                        String value = je.getAsJsonObject().get("message").getAsString();
                        Log.e("HELLO OBJECT",value);
                        if(value.equals("ok")){
                            JsonObject g_user = je.getAsJsonObject().get("user_found").getAsJsonObject();
                            Log.i("HHAAMMMM", "onResponse: "+g_user.toString());
                            User us = new User(g_user.get("_id").getAsString(),g_user.get("first_name").getAsString(),g_user.get("last_name").getAsString(),g_user.get("email").getAsString(),"",g_user.get("phone_number").getAsString(),g_user.get("birthday").getAsString(),g_user.get("location").getAsString());
                            //PersonalDataFragment.user_c = new User(g_user.get("_id").getAsString(),g_user.get("first_name").getAsString(),g_user.get("last_name").getAsString(),g_user.get("email").getAsString(),"",g_user.get("phone_number").getAsString(),g_user.get("birthday").getAsString(),g_user.get("location").getAsString());
                            u.set_id(us.get_id());
                            u.setBirthday(us.getBirthday());
                            u.setEmail(us.getEmail());
                            u.setFirst_name(us.getFirst_name());
                            u.setLast_name(us.getLast_name());
                            u.setLocation(us.getLocation());
                            u.setPassword(us.getPassword());
                            u.setPhone_number(us.getPhone_number());
                            updateView();
                        }
                        else{
                            Intent i = new Intent(getActivity(), HomeActivity.class);

                            if(value.equals("no user found")){
                                // auth fail
                                Toast.makeText(getActivity(), "Hell yeah , no user there", Toast.LENGTH_LONG).show();
                            }
                            else{
                                // unknwon error
                                Toast.makeText(getActivity(), "We have troubles , try later", Toast.LENGTH_LONG).show();
                            }
                            startActivity(i);
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Connection Problem", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getActivity(), HomeActivity.class);
                        startActivity(i);
                    }
                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.e("ERROR: ", t.getMessage());
                    Toast.makeText(getActivity(), "Connection Problem", Toast.LENGTH_LONG).show();
                }
            });
        }
        public void updateUser(User u){
            String locid = checkUser();
            Call<JsonObject> call  = userService.updateUser(locid,user_c);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){
                        JsonElement je = response.body();
                        String value = je.getAsJsonObject().get("message").getAsString();
                        if(value.equals("update_success")){
                            Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
                            updateView();
                        }
                        else{
                            Toast.makeText(getActivity(), "For dome reason we are sucks", Toast.LENGTH_SHORT).show();
                            String err = je.getAsJsonObject().get("error").getAsString();
                            Log.e("BALAM", "onResponse: "+err );
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "We Got a Problem", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(getActivity(), "We Got a connection Problem", Toast.LENGTH_SHORT).show();
                }
            });
        }
        public String checkUser(){
            Intent i = new Intent(getActivity(), MainActivity.class);
            SharedPreferences prefs = getActivity().getSharedPreferences("STADIUMS_USER_PREF_SEC_ONLY",Context.MODE_PRIVATE);
            String locid = prefs.getString("current_user_id","00_no_id_00");
            if(!prefs.contains("current_user_id") || locid.equals("00_no_id_00")){
                prefs.edit().remove("token_app_acc").apply();
                prefs.edit().remove("current_user_id").apply();
                Toast.makeText(getActivity(), "login again please", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
            return locid;
        }

    }



}
