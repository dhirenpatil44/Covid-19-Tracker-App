package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CountryDetail extends AppCompatActivity {
    private int countryPosition;

    TextView dCountry, dCases, dActive, dTodayCases, dTodayDeath, dDeath, dCritical, dRecover, dContinent, dPopulation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_daetail);


        Intent intent = getIntent();
        countryPosition = intent.getIntExtra("position",0);

//        getSupportActionBar().setTitle("Details of "+ MainActivity2.countryModelList.get(countryPosition).getCountry());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dCountry = findViewById(R.id.dCountry);
        dCases = findViewById(R.id.dCases);
        dActive = findViewById(R.id.dActive);
        dTodayCases = findViewById(R.id.dTodayCases);
        dTodayDeath = findViewById(R.id.dTodayDeath);
        dDeath = findViewById(R.id.dDeath);
        dCritical = findViewById(R.id.dCritical);
        dRecover = findViewById(R.id.dRecover);
        dPopulation = findViewById(R.id.dPopulation);
        dContinent = findViewById(R.id.dContinent);


        dCountry.setText(MainActivity2.countryModelList.get(countryPosition).getCountry());
        dCases.setText(MainActivity2.countryModelList.get(countryPosition).getCases());
        dActive.setText(MainActivity2.countryModelList.get(countryPosition).getActive());
        dTodayCases.setText(MainActivity2.countryModelList.get(countryPosition).getTodayCases());
        dTodayDeath.setText(MainActivity2.countryModelList.get(countryPosition).getTodayDeath());
        dDeath.setText(MainActivity2.countryModelList.get(countryPosition).getDeaths());
        dCritical.setText(MainActivity2.countryModelList.get(countryPosition).getCritical());
        dRecover.setText(MainActivity2.countryModelList.get(countryPosition).getRecovered());
        dContinent.setText(MainActivity2.countryModelList.get(countryPosition).getContinent());
        dPopulation.setText(MainActivity2.countryModelList.get(countryPosition).getPopulation());




    }
}