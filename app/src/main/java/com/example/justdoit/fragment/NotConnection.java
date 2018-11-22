package com.example.justdoit.fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.justdoit.R;

public class NotConnection extends Fragment {

    final String LOG_TAG = "myLogs";
//    public static NotConnection newInstance() {
//        return new NotConnection();
//    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.not_connection, null);

        return v;
    }
}
