package com.example.hptouchsmart.hospitalconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HospitalList extends AppCompatActivity {
    public static ArrayList<Hospitals> hospitalObject;
    ListView listView;
    FrameLayout frame ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

         listView = (ListView) findViewById(R.id.listView);
            hospitalObject = MainActivity.hospitalsFinalArray;

        hospitalAdapter adapter = new hospitalAdapter(hospitalObject);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("item", String.valueOf(position));
                Intent i = new Intent(HospitalList.this , Final.class);
                i.putExtra("p",position);
                startActivity(i);

            }
        });

    }


    //ADAPTER



    public class hospitalAdapter extends BaseAdapter{

        public class viewHolder{
            TextView hospitalName;
            TextView hospitalAddres;
            LinearLayout layout;



        }

       ArrayList<Hospitals> hospitalO = new ArrayList<>();

        public hospitalAdapter(ArrayList<Hospitals> hospitalO) {
            this.hospitalO = hospitalO;
        }



        @Override
        public int getCount() {
            return hospitalO.size();
        }

        @Override
        public Hospitals getItem(int position) {
            Hospitals thisHospital = hospitalO.get(position);
            return thisHospital;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater li = getLayoutInflater();
            viewHolder viewHolder;

            if(convertView == null){
               convertView =  li.inflate(R.layout.list_inflate,null);
                 viewHolder = new viewHolder();
                viewHolder.hospitalName = (TextView) convertView.findViewById(R.id.hospitalName);
                viewHolder.hospitalAddres = (TextView) convertView.findViewById(R.id.hospitalAd);
                viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layoutid);

                convertView.setTag(viewHolder);

            }else{
                viewHolder = (viewHolder) convertView.getTag();
            }

            Hospitals thisHospital = getItem(position);

            viewHolder.hospitalName.setText(thisHospital.hospitalName);
            viewHolder.hospitalAddres.setText(thisHospital.hospitalAddress);



            return convertView;
        }
    }

}
