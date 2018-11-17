package com.example.pingghost.stadiums;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
            saveEmail = rootView.findViewById(R.id.email_change);
            saveChanges = rootView.findViewById(R.id.saveSecurity);
            newEmail = rootView.findViewById(R.id.new_email);
            old_passwd = rootView.findViewById(R.id.old_pass);
            enableEdit = rootView.findViewById(R.id.enableEditSecurity);
            saveChanges.setEnabled(false);
            saveEmail.setEnabled(false);
            newEmail.setEnabled(false);
            old_passwd.setEnabled(false);
            enableEdit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        saveChanges.setEnabled(true);
                        saveEmail.setEnabled(true);
                        newEmail.setEnabled(true);
                        old_passwd.setEnabled(true);
                    }else{
                        saveChanges.setEnabled(false);
                        saveEmail.setEnabled(false);
                        newEmail.setEnabled(false);
                        old_passwd.setEnabled(false);
                    }
                }
            });
            return rootView;
        }
    }
    public static class PersonalDataFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private Button save;
        private EditText last_name,first_name,b_date,location,phone;
        private Switch enable_edit;
        private static final String ARG_SECTION_NUMBER = "section_number";

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
            return rootView;
        }
    }
}
