package com.example.justdoit;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.justdoit.connect.FlickrFetchr;

import static com.example.justdoit.connect.CheckConnection.hasConnection;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();
//    // timer
//    private final int interval = 2500; //   Second
//    private Handler handler = new Handler();
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();

        }


//        //timer
//        Runnable runnable = new Runnable(){
//            public void run() {
//                // check fot connect
//                if( !hasConnection(SingleFragmentActivity.this) ){
//                    Toast.makeText(SingleFragmentActivity.this,"SingleFragmentActivity connection is not found",Toast.LENGTH_SHORT).show();
//                }else {
//                    new FlickrFetchr();
//                }
//            }
//        };
//        handler.postAtTime(runnable, System.currentTimeMillis()+interval);
//        handler.postDelayed(runnable, interval);
    }
}