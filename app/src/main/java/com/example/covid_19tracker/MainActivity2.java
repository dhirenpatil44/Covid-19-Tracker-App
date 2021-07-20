package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    ProgressBar progressBar;
    EditText editText;

    public static List<CountryModel> countryModelList = new ArrayList<>();
    private CountryModel countryModel;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),CountryDetail.class).putExtra("position",position));
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customAdapter.getFilter().filter(s);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    private void fetchData() {
        String countryURL = "https://disease.sh/v3/covid-19/countries/";

        StringRequest request = new StringRequest(Request.Method.GET, countryURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String countryName = jsonObject.getString("country");
                        String cases = jsonObject.getString("cases");
                        String todayCases = jsonObject.getString("todayCases");
                        String deaths = jsonObject.getString("deaths");
                        String todayDeath = jsonObject.getString("todayDeaths");
                        String critical = jsonObject.getString("critical");
                        String active = jsonObject.getString("active");
                        String recovered = jsonObject.getString("recovered");
                        String continent = jsonObject.getString("continent");
                        String population = jsonObject.getString("population");

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagURL = object.getString("flag");

                        countryModel = new CountryModel(flagURL, countryName, cases, active, deaths,todayDeath, todayCases, critical, recovered, population, continent);
                        countryModelList.add(countryModel);
                    }


                    customAdapter = new CustomAdapter(MainActivity2.this,countryModelList);
                    listView.setAdapter(customAdapter);
                    progressBar.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
            new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}