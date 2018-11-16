package com.example.justdoit;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    protected abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());


//        initToolbar();
//        initNavigatioView();
//        initTabs();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

//        Button btn = (Button) findViewById(R.id.btn);
//
//        View.OnClickListener click = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // new ParseTask().execute();
//            }
//        };
//        btn.setOnClickListener(click);
    }

//    private void initToolbar() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.app_name);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//        toolbar.inflateMenu(R.menu.menu);
//    }
//    private void initNavigatioView() {
//        drawerLayout = (DrawerLayout) findViewById(R.id.fragment_container);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
//                drawerLayout, toolbar,
//                R.string.view_navigation_open,
//                R.string.view_navigation_close);
//
//        drawerLayout.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                drawerLayout.closeDrawers();
//                switch (menuItem.getItemId()){
////                    case R.id.actionNotificationItem: showNotificatioTab(); break;
////                    case R.id.actionFavoritesItem: showFavoritesTab(); break;
//                }
//                return true;
//            }
//        });
//    }

//    private void initTabs() {
////        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        TabsPagesFragmentAdapter adapter = new TabsPagesFragmentAdapter(this,getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//
////        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
////        tabLayout.setupWithViewPager(viewPager);
//    }
//    // для ссылок в меню  свайпа
//    private void showNotificatioTab(){
//        viewPager.setCurrentItem(Constants.TAB_ONE);
//    }
//    private void showFavoritesTab(){  viewPager.setCurrentItem(Constants.TAB_TWO); }
//    private void showArchiveTab(){
//        viewPager.setCurrentItem(Constants.TAB_THREE);
//    }
}