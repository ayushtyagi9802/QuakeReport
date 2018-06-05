package com.example.mehul.myapplication;

import com.example.mehul.myapplication.Earthquake;

import com.example.mehul.myapplication.Earthquake;




import android.util.Log;


import com.example.mehul.myapplication.Earthquake;

import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;



/**

 * Helper methods related to requesting and receiving earthquake data from USGS.

 */

public final class QueryUtils {



    /** Sample JSON response for a USGS query */


    /**

     * Create a private constructor because no one should ever create a {@link QueryUtils} object.

     * This class is only meant to hold static variables and methods, which can be accessed

     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).

     */

    private QueryUtils() {

    }

public static URL createUrl(String StringUrl)
{
    URL url = null;
    try
    {
        url = new URL(StringUrl);
    }
    catch (MalformedURLException exception)
    {
        Log.e("MainActivity.this","Error creating url",exception);
        return null;
    }
    return url;
}
public static String makehttpRequest(URL url)throws IOException{
    String jsonResponse = "";
    HttpURLConnection urlConnection = null;
    InputStream inputStream = null;
    try {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.connect();
        inputStream = urlConnection.getInputStream();
        jsonResponse = readFromStream(inputStream);
    }catch (IOException e)
    {}
    finally {
        if(urlConnection!=null)
        {
            urlConnection.disconnect();
        }
        if(inputStream!=null)
        {
           inputStream.close();
        }
    }
    return jsonResponse;
}



public static String readFromStream(InputStream inputStream) throws IOException{
    StringBuilder output = new StringBuilder();
    if(inputStream!=null)
    {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = reader.readLine();
        while(line!= null)
        {
            output.append(line);
            line = reader.readLine();
        }
    }
    return output.toString();
}





    public static ArrayList<Earthquake> extractEarthquakes(String jsonResponse) {



        // Create an empty ArrayList that we can start adding earthquakes to

        ArrayList<Earthquake> earthquakes = new ArrayList<>();



        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON

        // is formatted, a JSONException exception object will be thrown.

        // Catch the exception so the app doesn't crash, and print the error message to the logs.

        try {



            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject baseJosonResponse = new JSONObject(jsonResponse);
            JSONArray earthquakeArray = baseJosonResponse.getJSONArray("features");
            for(int i=0;i<earthquakeArray.length();i++)
            {
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                JSONObject properties = currentEarthquake.getJSONObject("properties");
                String magnitude = properties.getString("mag");
                String location = properties.getString("place");
                String time = properties.getString("time");
                Earthquake earthquakee = new Earthquake(magnitude,location,time);
                earthquakes.add(earthquakee);
            }




        } catch (JSONException e) {

            // If an error is thrown when executing any of the above statements in the "try" block,

            // catch the exception here, so the app doesn't crash. Print a log message

            // with the message from the exception.

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);

        }



        // Return the list of earthquakes

        return earthquakes;

    }



}