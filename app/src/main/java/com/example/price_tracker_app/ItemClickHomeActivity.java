package com.example.price_tracker_app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ItemClickHomeActivity extends AppCompatActivity {
    String url;
    String marketPlace;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_item_click_home);
        getIntentMethod();
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar_item_click_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                Bundle bundle = new Bundle();
                String url = ""; // Replace with your desired URL or obtain it from an appropriate source.

                int itemId = item.getItemId(); // Get the ID of the selected navigation item.

                if (itemId == R.id.nav_web_view) {
                    bundle.putString("url", url);
                    selectedFragment = new WebViewHomeFragment();
                } else if (itemId == R.id.nav_graph) {
                    bundle.putString("url", url);
                    selectedFragment = new GraphFragment();
                }

                if (selectedFragment != null) {
                    selectedFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.item_click_container, selectedFragment).commit();
                }

                return true;
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Fragment selectedFragment1 = new WebViewHomeFragment();
        selectedFragment1.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.item_click_container, selectedFragment1).commit();
    }

    private void getIntentMethod() {
        this.url = getIntent().getStringExtra("url");
        this.marketPlace = getIntent().getStringExtra("marketplace");
    }
}