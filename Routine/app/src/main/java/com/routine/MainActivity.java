package com.routine;

import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.*;
import com.routine.fragments.*;
import org.jetbrains.annotations.NotNull;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            setDefaultFragmentBasedOnDay(navigationView);
        }
    }

    private void setDefaultFragmentBasedOnDay(NavigationView navigationView) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SunFragment()).commit();
                navigationView.setCheckedItem(R.id.sun);
                break;
            case Calendar.MONDAY:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MonFragment()).commit();
                navigationView.setCheckedItem(R.id.mon);
                break;
            case Calendar.TUESDAY:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TueFragment()).commit();
                navigationView.setCheckedItem(R.id.tue);
                break;
            case Calendar.WEDNESDAY:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WedFragment()).commit();
                navigationView.setCheckedItem(R.id.wed);
                break;
            case Calendar.THURSDAY:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThuFragment()).commit();
                navigationView.setCheckedItem(R.id.thu);
                break;

            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SunFragment()).commit();
                navigationView.setCheckedItem(R.id.sun);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sun) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SunFragment()).commit();
        } else if (id == R.id.mon) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MonFragment()).commit();
        } else if (id == R.id.tue) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TueFragment()).commit();
        } else if (id == R.id.wed) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WedFragment()).commit();
        } else if (id == R.id.thu) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ThuFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SunFragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
