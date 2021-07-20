package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvCases, tvRecover,  tvCritical, tvActive,  tvTodayCases,  tvTotalDeath,  tvTodayDeath , tvCountry;
    PieChart pieChart;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases = findViewById(R.id.tvCases);
        tvRecover = findViewById(R.id.tvRecover);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeath = findViewById(R.id.tvTotalDeath);
        tvTodayDeath = findViewById(R.id.tvTodayDeath);
        tvCountry = findViewById(R.id.tvCountry);

        pieChart = findViewById(R.id.pieChart);
        button = findViewById(R.id.button);

        fetchData();

    }

    private void fetchData(){
        String url = "https://disease.sh/v3/covid-19/all";
        StringRequest request = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecover.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeath.setText(jsonObject.getString("deaths"));
                            tvTodayDeath.setText(jsonObject.getString("todayDeaths"));
                            tvCountry.setText(jsonObject.getString("affectedCountries"));

                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFB74D")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecover.getText().toString()), Color.parseColor("#4CAF50")));
                            pieChart.addPieSlice(new PieModel("Death",Integer.parseInt(tvTotalDeath.getText().toString()), Color.parseColor("#FA3C3C")));
                            pieChart.addPieSlice(new PieModel("Active Cases",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#4FC3F7")));

                            pieChart.startAnimation();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }


    public void countryDetails(View view) {

        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
    }
}