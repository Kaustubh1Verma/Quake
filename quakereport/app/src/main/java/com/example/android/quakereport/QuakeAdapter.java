package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class QuakeAdapter extends ArrayAdapter<Quake> {

    private static final String LOG_TAG = QuakeAdapter.class.getSimpleName();

    public QuakeAdapter(Activity context, ArrayList<Quake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Quake currentQuake = getItem(position);
        TextView magTextView = (TextView)listItemView.findViewById(R.id.mag);
        DecimalFormat format = new DecimalFormat("0.0");
        String output = format.format(currentQuake.getMagnitude());
        magTextView.setText(output);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentQuake.getQuake_date());

        String formatDate = formatDate(dateObject);
        String formatTime = formatTime(dateObject);

        TextView DateTextView = (TextView)listItemView.findViewById(R.id.date);
        DateTextView.setText(formatDate);

        TextView TimeTextView = (TextView)listItemView.findViewById(R.id.time);
        TimeTextView.setText(formatTime);

        String offset = "Near the";
        String primary;

        String totalLocation = currentQuake.getLocation();
        if(totalLocation.contains("of")){
            int endIndex = totalLocation.indexOf("of");
            endIndex = endIndex + 2;

            offset = totalLocation.substring(0,endIndex);
            primary = totalLocation.substring(endIndex,totalLocation.length());

        }
        else{
            primary = totalLocation;
        }

        TextView placeTextView = (TextView)listItemView.findViewById(R.id.offset);
        placeTextView.setText(offset);

        TextView place2TextView = (TextView)listItemView.findViewById(R.id.primary);
        place2TextView.setText(primary);


        GradientDrawable magnitudeCircle = (GradientDrawable) listItemView.findViewById(R.id.mag).getBackground();
        int magnitudeColor = getMagnitudeColor(currentQuake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
