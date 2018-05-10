package com.bignerdranch.android.airplane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bignerdranch.android.airplane.Database.AirplaneDatabase;
import com.bignerdranch.android.airplane.Models.Flight;

import java.util.ArrayList;

public class ReserveSeatActivity extends AppCompatActivity {

    private AirplaneDatabase mAirplaneDatabase;
    private ArrayList<Flight> arrayOfFlights;
    private Spinner departureSpinner;
    private Spinner arrivalSpinner;
    private Spinner ticketSpinner;

    private FlightAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        departureSpinner = (Spinner) findViewById(R.id.departure);
        arrivalSpinner = (Spinner) findViewById(R.id.arrival);
        ticketSpinner = (Spinner) findViewById(R.id.tickets);

        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};


        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        ticketSpinner.setAdapter(spinnerAdapter);

        // Get access to our database singleton
        mAirplaneDatabase = AirplaneDatabase.getInstance(this);

        // Construct the data source
        arrayOfFlights = (ArrayList<Flight>) mAirplaneDatabase.flightDao().getAllFlights();

        // Create the adapter to convert the array to views
         adapter = new FlightAdapter(this, arrayOfFlights);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list_view);

        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.header, listView,false);
        // Add header view to the ListView
        listView.addHeaderView(headerView,null,false);
        listView.setAdapter(adapter);


        // Set an item click listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Flight selectedFlight = (Flight) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),String.valueOf(selectedFlight.getId()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        departureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatedData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        arrivalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatedData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        ticketSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatedData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }


    private void updatedData() {

        String arrival = arrivalSpinner.getSelectedItem().toString();
        String departure = departureSpinner.getSelectedItem().toString();
        int tickets = Integer.parseInt(ticketSpinner.getSelectedItem().toString());

        adapter.clear();
        arrayOfFlights = (ArrayList<Flight>) mAirplaneDatabase.flightDao().getFlights(arrival,departure,tickets);

        if (arrayOfFlights != null){

            for (Flight flight : arrayOfFlights) {

                adapter.insert(flight, adapter.getCount());
            }
        }

        adapter.notifyDataSetChanged();

    }
}
