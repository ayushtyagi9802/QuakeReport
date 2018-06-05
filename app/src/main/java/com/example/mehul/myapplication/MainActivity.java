package com.example.mehul.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute();

        //ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


    }
     class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {

            URL url = QueryUtils.createUrl(USGS_REQUEST_URL);
            String jsonresponse="";
            try {
                jsonresponse = QueryUtils.makehttpRequest(url);
            }catch (IOException e)
            {

            }
            ArrayList<Earthquake> earthquake = QueryUtils.extractEarthquakes(jsonresponse);
            return earthquake;

        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            ListView earthquakeView = (ListView)findViewById(R.id.list);
            EarthquakeAdapter adapter = new EarthquakeAdapter(MainActivity.this, earthquakes);
            earthquakeView.setAdapter(adapter);

        }
    }
}
