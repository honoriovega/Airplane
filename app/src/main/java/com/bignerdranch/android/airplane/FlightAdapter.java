package com.bignerdranch.android.airplane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bignerdranch.android.airplane.Models.Flight;

import java.util.ArrayList;

public class FlightAdapter extends ArrayAdapter<Flight> {
    public FlightAdapter(Context context, ArrayList<Flight> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Flight flight = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView flightNumber = (TextView) convertView.findViewById(R.id.flightNumber);
        TextView departure = (TextView) convertView.findViewById(R.id.departure);
        TextView arrival = (TextView) convertView.findViewById(R.id.arrival);
        TextView departureTime = (TextView) convertView.findViewById(R.id.departureTime);
        TextView flightCapacity = (TextView) convertView.findViewById(R.id.flightCapacity);
        TextView price = (TextView) convertView.findViewById(R.id.price);

        // Populate the data into the template view using the data object
        flightNumber.setText(flight.getFlightNumber());
        departureTime.setText(flight.getDepartureTime());
        flightCapacity.setText(String.valueOf(flight.getFlightCapacity()));
        price.setText(String.format("$%.2f",flight.getPrice()));
        // Return the completed view to render on screen


        return convertView;
    }
}