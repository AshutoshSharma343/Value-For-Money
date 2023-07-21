package com.example.price_tracker_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.work.NetworkType;
import androidx.work.Constraints;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<SiteToggler> siteTogglers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_search) {
                    selectedFragment = new SearchFragment();
                    setTitle("Price Tracker");
                } else if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                    setTitle("Home");
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }

                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
        siteTogglers = getSiteTogglers();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
    }
    private ArrayList<SiteToggler> getSiteTogglers() {
        ArrayList<SiteToggler> temp = new ArrayList<>();
        temp.add(new SiteToggler("Amazon", "https://www.amazon.in/", "", true, R.drawable.ic_amazon));
        temp.add(new SiteToggler("Flipkart", "https://www.flipkart.com/", "", true, R.drawable.ic_flipkart));
        temp.add(new SiteToggler("Bigbasket", "https://www.bigbasket.com/", "", false, R.drawable.ic_bigbasket));
        temp.add(new SiteToggler("JioMart", "https://www.jiomart.com/", "", false, R.drawable.ic_jiomart));
        //temp.add(new SiteToggler("Myntra", "https://www.myntra.com/", "", false, R.drawable.ic_myntra)); // TODO myntra scraping issue
        temp.add(new SiteToggler("Paytm Mall", "https://paytmmall.com/", "", false, R.drawable.ic_paytmmall));
        temp.add(new SiteToggler("Snapdeal", "https://www.snapdeal.com/", "", false, R.drawable.ic_snapdeal));
        return temp;
    }

}