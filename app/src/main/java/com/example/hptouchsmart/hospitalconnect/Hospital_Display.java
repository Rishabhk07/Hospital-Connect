package com.example.hptouchsmart.hospitalconnect;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Hospital_Display extends Fragment {
    public Button mapsButton;
    private int position;
    static String key = "123";
    static String key2 = "456";
    ArrayList<Hospitals> hos;
    //setMapsButton listner ;

    public Hospital_Display() {
        // Required empty public constructor
    }

    public static Hospital_Display newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(key, position);
        Hospital_Display fragment = new Hospital_Display();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null) {
            this.position = getArguments().getInt(key);
        }

        if (HospitalList.hospitalObject != null && HospitalList.hospitalObject.size() > 0) {
            hos = HospitalList.hospitalObject;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital__display, container, false);
        TextView name = (TextView) view.findViewById(R.id.hospitalNametv);
        TextView ad = (TextView) view.findViewById(R.id.hospitalAdtv);
        TextView phone = (TextView) view.findViewById(R.id.phone);
        TextView fac = (TextView) view.findViewById(R.id.facilities);
        mapsButton = (Button) view.findViewById(R.id.mapsButton);
        final Hospitals thisHospital = hos.get(position);

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(thisHospital.lat.equals("NA")) {
                    Toast.makeText(getActivity(), "Location not available !! ", Toast.LENGTH_SHORT).show();
                }else {

                    Intent i = new Intent(getActivity(), MapsActivity.class);
                    i.putExtra("lat", thisHospital.lat);
                    i.putExtra("lng", thisHospital.lng);
                    Hospital_Display.this.startActivity(i);
                }
            }
        });

        name.setText(thisHospital.hospitalName);
        ad.setText(thisHospital.hospitalAddress);
        phone.setText(thisHospital.phone);
        fac.setText(thisHospital.facilities);


        //name.setText(HospitalList.);
        return view;
    }


}
