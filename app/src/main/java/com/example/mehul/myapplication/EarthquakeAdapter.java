package com.example.mehul.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mehul on 13-05-2018.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, List<Earthquake> Earthquakes)
    {
        super(context,0, Earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        if(ListItemView==null)
        {
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list,parent,false);

        }
        Earthquake currentQuake = getItem(position);
        TextView magnitudeView = (TextView)ListItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(currentQuake.getmagnitude());
        TextView locationView = (TextView)ListItemView.findViewById(R.id.location);
        locationView.setText(currentQuake.getlocation());
        TextView dateView = (TextView)ListItemView.findViewById(R.id.date);
        dateView.setText(currentQuake.getdate());
        return ListItemView;
    }

}
