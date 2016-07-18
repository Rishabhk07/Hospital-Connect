package com.example.hptouchsmart.hospitalconnect;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText pincode;
    Button getHos;
    public static ArrayList<Hospitals> hospitalsFinalArray;
    DownloadTask task;
    String pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pincode = (EditText) findViewById(R.id.pin);
        getHos = (Button) findViewById(R.id.button);
        task = new DownloadTask();

        getHos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               pin = String.valueOf(pincode.getText());
                //task.execute("https://data.gov.in/api/datastore/resource.json?resource_id=7d208ae4-5d65-47ec-8cb8-2a7a7ac89f8c&api-key=4bbf940bb8aa27630eb8fb0dfd998eb1&filters[pincode]=110085");
          if(!pin.isEmpty()) {

                  Log.i("Button Clicked !! ", "first ");
                  checkNetworkStatus();


          }else{
              Toast.makeText(MainActivity.this, "Pincode is Empty !!", Toast.LENGTH_SHORT).show();
          }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        task = new DownloadTask();
    }

    public void checkNetworkStatus(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){

            task.setContext(MainActivity.this);
            task.setOnHospitalArrayFetched(new DownloadTask.onHospitalArrayFetched() {
                @Override
                public void onPostArrayFetched(ArrayList<Hospitals> hospitalList) {
                    hospitalsFinalArray = hospitalList;
                }
            });

            String url = "https://data.gov.in/api/datastore/resource.json?resource_id=7d208ae4-5d65-47ec-8cb8-2a7a7ac89f8c&api-key=4bbf940bb8aa27630eb8fb0dfd998eb1&filters[pincode]=" +pin;
            task.execute(url);

        }else{
            Toast.makeText(MainActivity.this, "Network not Available !! ", Toast.LENGTH_SHORT).show();
        }
    }
}
