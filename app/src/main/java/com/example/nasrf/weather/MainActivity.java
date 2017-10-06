package com.example.nasrf.weather;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nasrf.weather.Temperature.UpcomingDaysTemp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvCity,tvWeatherCondition,tvTempHigh,tvToday,tvForcast;
    private ListView lvUpcomingDays;
    private com.example.nasrf.weather.Adapter.Adapter myAdapter;
    private List<UpcomingDaysTemp> listUpcomg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initalizer();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22cairo%2C%20eg%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject all = new JSONObject(response);
                            JSONObject query = all.getJSONObject("query");
                            JSONObject results = query.getJSONObject("results");
                            JSONObject channel = results.getJSONObject("channel");
                            JSONObject location = channel.getJSONObject("location");
                            String city = location.getString("city");
                            tvCity.setText(city);
                            JSONObject item = channel.getJSONObject("item");
                            JSONObject condition = item.getJSONObject("condition");
                            String cond = condition.getString("text");
                            tvWeatherCondition.setText(cond);
                            //test from here!
                            JSONArray forecast = item.getJSONArray("forecast");
                            String date="", high="",low="",day="",cond_up="";
                            for(int i=0;i<=forecast.length();i++){
                                if(i==0){
                                    JSONObject member = forecast.getJSONObject(i);
                                    high = member.getString("high");
                                    low = member.getString("low");
                                    day = member.getString("day");
                                    tvTempHigh.setText(high);
                                    tvForcast.setText("forcast: "+high+" / "+low);
                                    tvToday.setText(day+"day");

                                }
                                else {
                                    JSONObject member = forecast.getJSONObject(i);
                                    high = member.getString("high");
                                    low = member.getString("low");
                                    date = member.getString("date");
                                    day = member.getString("day");
                                    cond_up = member.getString("text");
                                    //myTasks.add(new Tasks(name,note,startDate,endDate));
                                    listUpcomg.add(new UpcomingDaysTemp(day, date, cond_up, high, low));
                                }
                                myAdapter = new com.example.nasrf.weather.Adapter.Adapter(getApplicationContext(),listUpcomg);
                                lvUpcomingDays.setAdapter(myAdapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true; //return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== R.id.action_refresh){
            finish();
            startActivity(getIntent());
            //Do refresh
        }

        return super.onOptionsItemSelected(item);
    }

    private void initalizer() {
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvTempHigh = (TextView) findViewById(R.id.tvTempHigh);
        tvForcast = (TextView) findViewById(R.id.tvForcast);
        tvWeatherCondition = (TextView) findViewById(R.id.tvWeatherCondtion);
        tvToday = (TextView) findViewById(R.id.tvToday);
        lvUpcomingDays = (ListView) findViewById(R.id.list_viewUpcome);
        listUpcomg = new ArrayList<>();

    }


}
