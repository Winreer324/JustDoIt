package com.example.justdoit;

import android.app.FragmentTransaction;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.justdoit.adapter.SectionsPageAdapter;
import com.example.justdoit.connect.CheckConnection;
import com.example.justdoit.connect.FlickrFetchr;
import com.example.justdoit.connect.ThumbnailDownloader;
import com.example.justdoit.fragment.AllGalleryFragment;
import com.example.justdoit.fragment.NewGalleryFragment;
import com.example.justdoit.fragment.NotConnection;
import com.example.justdoit.fragment.PopularGalleryFragment;

import static com.example.justdoit.connect.CheckConnection.hasConnection;

public class MainActivity extends SingleFragmentActivity {

    private static final String TAG = "MainActivity";
    private static   Toolbar toolbar;
    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    // timer
    private final int interval = 2500; //   Second
    private Handler handler = new Handler();

    @Override
    protected Fragment createFragment() {
        return  AllGalleryFragment.newInstance() ;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_all);
        Button btn = new Button(this);
        btn.setText("click");
        int check=1;
        btn.setId(check);
        btn.setWidth(30);
        btn.setHeight(30);
        toolbar.addView(btn);
        setSupportActionBar(toolbar);

        final CoordinatorLayout mainLayout = (CoordinatorLayout)findViewById(R.id.main_content);
        final ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.not_connect);

        //timer
        Runnable runnable = new Runnable(){
            public void run() {
                // check fot connect
                if( !hasConnection(MainActivity.this) ){
                    Toast.makeText(MainActivity.this,"MainActivity connection is not found",Toast.LENGTH_SHORT).show();
                }
            }
        };
        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
        handler.postDelayed(runnable, interval);

        Log.d(TAG, "OnCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if( !hasConnection(MainActivity.this) ){
//
////                    LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(
////                            LinearLayout.LayoutParams.MATCH_PARENT,
////                            LinearLayout.LayoutParams.MATCH_PARENT);
////                    imageView.setLayoutParams(imageViewLayoutParams);
//
////                    ViewPager viewPager = findViewById(R.id.container);
////                    LinearLayout linearLayout = findViewById(R.id.fragment_container);
////                    mainLayout.removeView(viewPager);
////                    mainLayout.removeView(linearLayout);
////                    mainLayout.removeAllViews();
//                    mainLayout.addView(imageView);
//                    Toast.makeText(MainActivity.this,"MainActivity connection is not found",Toast.LENGTH_SHORT).show();
//                }else {
//                    new FlickrFetchr();
//                    mainLayout.removeView(imageView);
//                    Toast.makeText(MainActivity.this,"click connect reload",Toast.LENGTH_SHORT).show();
//                }
            }
        };

        btn.setOnClickListener(listener);

        if( !hasConnection(MainActivity.this) ){

//                    LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.MATCH_PARENT);
//                    imageView.setLayoutParams(imageViewLayoutParams);

//                    ViewPager viewPager = findViewById(R.id.container);
//                    LinearLayout linearLayout = findViewById(R.id.fragment_container);
//                    mainLayout.removeView(viewPager);
//                    mainLayout.removeView(linearLayout);
//                    mainLayout.removeAllViews();
            mainLayout.addView(imageView);
            Toast.makeText(MainActivity.this,"MainActivity connection is not found",Toast.LENGTH_SHORT).show();
        }else {
            new FlickrFetchr();
            mainLayout.removeView(imageView);
            Toast.makeText(MainActivity.this,"click connect reload",Toast.LENGTH_SHORT).show();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
//        adapter.addFragment(new AllGalleryFragment(), "All");
        adapter.addFragment(new PopularGalleryFragment(), "Popular");
        adapter.addFragment(new NewGalleryFragment(), "New");
        viewPager.setAdapter(adapter);
    }
}


//public class MainActivity extends AppCompatActivity {
//    protected static final String TAG = "WebAnt";
//    Button btnNewMain;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//        setContentView(R.layout.tab);
////        Button btn = (Button) findViewById(R.id.btn);
//        ViewPager viewPager = findViewById(R.id.viewpager);
//        viewPager.setAdapter(
//                new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
//
//        // Передаём ViewPager в TabLayout
//        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
////        btnNewMain = (Button) findViewById(R.id.btnPopularyMain);
////
////        View.OnClickListener clickPopulary = new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this,PopularyActivity.class);
////                startActivity(intent);
//////                  new ParseTask().execute();
//////                 Toast.makeText(MainActivity.this, "Hello Android 7",Toast.LENGTH_LONG).show();
////            }
////        };
////        btnNewMain.setOnClickListener(clickPopulary);
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
//}
