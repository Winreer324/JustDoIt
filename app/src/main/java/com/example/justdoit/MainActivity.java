package com.example.justdoit;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.justdoit.adapter.SampleFragmentPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected static final String TAG = "WebAnt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.tab);
//        Button btn = (Button) findViewById(R.id.btn);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));

        // Передаём ViewPager в TabLayout
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

//        View.OnClickListener click = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                  new ParseTask().execute();
//                 Toast.makeText(MainActivity.this, "Hello Android 7",Toast.LENGTH_LONG).show();
//            }
//        };
//        btn.setOnClickListener(click);
//    }

//    private class ParseTask extends AsyncTask<Void, Void, String> {
//
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String resultJson = "";
//
//        @Override
//        protected String doInBackground(Void... params) {
//            // получаем данные с внешнего ресурса
//            try {
//                URL url = new URL("http://gallery.dev.webant.ru/api/photos?page=1&limit=23");
////                URL url = new URL("http://androiddocs.ru/api/friends.json");
//
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//                urlConnection.connect();
//
//                InputStream inputStream = urlConnection.getInputStream();
//                StringBuffer buffer = new StringBuffer();
//
//                reader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line);
//                }
//
//                resultJson = buffer.toString();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return resultJson;
//        }
//
//        @Override
//        protected void onPostExecute(String strJson) {
//            super.onPostExecute(strJson);
//            // выводим целиком полученную json-строку
//            Log.d(TAG, strJson);
//
//            JSONObject dataJsonObj = null;
//            String secondName = "";
//            String datas = "";
//            String img = "";
//
//            try {
//                dataJsonObj = new JSONObject(strJson);
////
////                String totalItems = dataJsonObj.getString("totalItems");
////                String itemsPerPage = dataJsonObj.getString("itemsPerPage");
////                String countOfPages = dataJsonObj.getString("countOfPages");
//                //     находим из массива под массив data [3]
//                JSONArray data = dataJsonObj.getJSONArray("data");
//
////                Log.d(TAG, "            главный массив\r" );
////                Log.d(TAG, "totalItems: " + totalItems);
////                Log.d(TAG, "itemsPerPage: " + itemsPerPage);
////                Log.d(TAG, "countOfPages: " + countOfPages);
////                Log.d(TAG, "data: " + data);
////                Log.d(TAG, "\r\r" );
//
//                for (int i = 0; i < data.length(); i++) {
//                    //     возращаем едемент под массива data
//                    JSONObject datafriend = data.getJSONObject(i);
////                    Log.d(TAG, "        итерация\r" +i);
////                    Log.d(TAG, "    подмассив массив data\r" );
////                    String dataid = datafriend.getString("id");
////                    String dataname = datafriend.getString("name");
////                    String datadescription = datafriend.getString("description");
////                    String datanew = datafriend.getString("new");
////                    String datapopular = datafriend.getString("popular");
////
////                    Log.d(TAG, "\r dataid: " + dataid);
////                    Log.d(TAG, "\r dataname: " + dataname);
////                    Log.d(TAG, "\r datadescription: " + datadescription);
////                    Log.d(TAG, "\r datanew: " + datanew);
////                    Log.d(TAG, "\r datapopular: " + datapopular);
////
////                    Log.d(TAG, "\r\r" );
//
//                    JSONObject imagefriend = datafriend.getJSONObject("image");
////                    for (int j = 0; j < imagefriend.length();j++) {
//                        //     находим из массива под массив image [5]
////                        JSONObject dataimage = imagefriend.getJSONObject(j);
//
////                        Log.d(TAG, "подмассив массив image\r" );
//                        String imgid = imagefriend.getString("id");
//                        String contentUrl = imagefriend.getString("contentUrl");
//
//                        Log.d(TAG, "\r id: " + imgid);
//                        Log.d(TAG, "\r contentUrl: " + contentUrl);
//                        Log.d(TAG, "\r i: " + i);
////                    }
//                }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//        }
//    }
}