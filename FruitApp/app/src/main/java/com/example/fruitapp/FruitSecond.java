package com.example.fruitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.math.BigDecimal;

public class FruitSecond extends AppCompatActivity {

    private TextView type1,price1,weight1;

    private String ty,pr,we;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        type1 = findViewById(R.id.typeinput);
        price1 = findViewById(R.id.priceinput);
        weight1 = findViewById(R.id.weightinput);

        getData();
        setData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
        { finish(); }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {

        Intent intent = getIntent();
        ty = intent.getStringExtra("type");
        pr = intent.getStringExtra("price");
        we = intent.getStringExtra("weight");
    }

    private void setData() {
        type1.setText(ty);
        price1.setText(getPrice());
        weight1.setText(getWeight());
    }

    // return in Kg
    private String getWeight()
    {
        double value = Double.parseDouble(we);
        double weightInKg = value / 100;

        return String.format("Price: "+"£"+weightInKg );
    }
    private String getPrice() {

        BigDecimal amount = new BigDecimal(pr).movePointLeft(2);

        return String.format("Weight: " + amount+"Kg");
    }

}
