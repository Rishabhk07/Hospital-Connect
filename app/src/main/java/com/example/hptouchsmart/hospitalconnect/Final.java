package com.example.hptouchsmart.hospitalconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Final extends AppCompatActivity {

    ArrayList<Hospitals> hospital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent i = getIntent();
        final int position = (int) i.getSerializableExtra("p");
        if (HospitalList.hospitalObject != null && HospitalList.hospitalObject.size() > 0) {
            hospital = HospitalList.hospitalObject;
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Hospital_Display frag = Hospital_Display.newInstance(position);

        fragmentTransaction.replace(R.id.frameLayout, frag, null);


        fragmentTransaction.commit();



    }
}

