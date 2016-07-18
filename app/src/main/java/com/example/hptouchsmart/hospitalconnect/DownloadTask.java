package com.example.hptouchsmart.hospitalconnect;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by hp TouchSmart on 7/2/2016.
 */
public class DownloadTask extends AsyncTask<String,Void,ArrayList<Hospitals>> {
    //public final String TAG = "tag";
    private int pin;
    private onHospitalArrayFetched listner;
    Context context;
    ///setContext myListner;

    public void setContext(Context context){
        this.context = context;
    }

//    public  interface setContext{
//        public void setCContext();
//    }
//
//
//    public void setOnSetContext(setContext setContextListner){
//
//        myListner = setContextListner;
//
//    }

    public DownloadTask() {
        //this.context = context;
    }
    public void setOnHospitalArrayFetched(onHospitalArrayFetched listner){
                this.listner = listner;
    }

    public  interface onHospitalArrayFetched{
        public void onPostArrayFetched(ArrayList<Hospitals> hospitalList );
    }

    @Override
    protected ArrayList<Hospitals> doInBackground(String... urls) {

        ArrayList<Hospitals> retHospital = null;


        try {


            URL url = new URL(urls[0]);


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(20000);

            urlConnection.connect();

//            InputStream inputStream = urlConnection.getInputStream();
//
//            url = new URL(urls[0]);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            InputStreamReader reader = new InputStreamReader(inputStream);
//            int data = reader.read();
//
//            while(data != -1){
//                char current = (char) data;
//
//                result+= current;
//
//                data = reader.read();


            if(urlConnection.getResponseCode() == 200) {
                String res = isToString(urlConnection.getInputStream());
                retHospital =  getHospitalArrayFromResponse(res);
            }else {
                //Toast.makeText(DownloadTask.this, "Response of url is : " + urlConnection.getResponseCode() , Toast.LENGTH_SHORT).show();
            }


            } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return retHospital;

    }

    @Override
    protected void onPostExecute(ArrayList<Hospitals> result) {
        super.onPostExecute(result);

            listner.onPostArrayFetched(result);

            Intent intent = new Intent(context,HospitalList.class);
            //intent.putExtra("hosObject",hospitalObject);
            //intent.putStringArrayListExtra();
            context.startActivity(intent);
                        //Log.i("Tag",hosName);

    }


    public String isToString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is , "utf-8"));
        StringBuilder sb = new StringBuilder();
        String line = r.readLine();

        while(line != null && !line.isEmpty()){
            sb.append(line);
            line = r.readLine();
        }
     return sb.toString();
    }

    public ArrayList<Hospitals> getHospitalArrayFromResponse(String res) throws JSONException {

        JSONObject jsonObject = new JSONObject(res);
        String hosName = jsonObject.getString("records");

        JSONArray jsonArray = new JSONArray(hosName);

        ArrayList<String> hospitalNames = new ArrayList<>();
        ArrayList<String> hospitalAd = new ArrayList<>();
        ArrayList<Hospitals> hospitalObject = new ArrayList<>();

        for(int i = 0 ;i < jsonArray.length() ; i++){
            JSONObject thisHospital = jsonArray.getJSONObject(i);

            String hospitalName = thisHospital.getString("Hospitalname");
            String hospitalads = thisHospital.getString("AddressFirstLine");
            String facilities = thisHospital.getString("Specialties");
            String phone = thisHospital.getString("Telephone");
            String lat = thisHospital.getString("Googlemapcorridinatelati");
            String lng = thisHospital.getString("Googlemapcorridinatelongi");
            String website = thisHospital.getString("Website");



            Log.i("TAG", hospitalName);



            hospitalNames.add(hospitalName);
            hospitalAd.add(hospitalads);

            hospitalObject.add(new Hospitals(hospitalName,hospitalads,facilities,phone,lat,lng,website));


        }

        return hospitalObject;
    }

}
