package com.example.fruitapp;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapt;

    private List<Fruits> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fruits=new ArrayList<>();
        new Parse().execute();

        FloatingActionButton f=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View fview) {
                Snackbar.make(fview, "Loading",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();


            }
        });

    }

    private String getURL(){
        return "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json";
    }
    private HttpURLConnection Connection() throws IOException{
        HttpURLConnection urlConnection;
        URL url=new URL(getURL());

        urlConnection=(HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        return urlConnection;
    }
    private String readUrl() throws IOException{
        BufferedReader br;
        String s;

        InputStream inputStream=Connection().getInputStream();
        StringBuffer stringBuffer=new StringBuffer();

        br=new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line=br.readLine())!=null){
            stringBuffer.append(line);
        }
        s=stringBuffer.toString();
        return s;
    }
    private class Parse extends AsyncTask<Void, Void, String> {

        String s = "";
        @Override
        protected String doInBackground(Void... params) {
            try {
                s = readUrl();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }
        protected void onPostExecute(String sj) {
            super.onPostExecute(sj);


            try {
                JSONObject json = new JSONObject(sj);
                JSONArray array = json.getJSONArray("fruit");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    Fruits f = new Fruits(object.getString("type"), object.getString("price"),
                            object.getString("weight"));

                    fruits.add(f);
                }

                adapt = new FruitClick(fruits, MainActivity.this);
                recyclerView.setAdapter(adapt);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
