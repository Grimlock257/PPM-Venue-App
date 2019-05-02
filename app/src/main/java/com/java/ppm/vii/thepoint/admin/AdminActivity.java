package com.java.ppm.vii.thepoint.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.user.EventOverviewActivity;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Use Toolbar as ActionBar due to default ActionBar removed
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the hamburger icon for the drawer
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Create a reference to the navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Launch a default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventOverviewFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_admin_events);
        }
    }

    @Override
    public void onBackPressed() {
        // If the drawer is open when back pressed, close it, otherwise perform the default action
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // See what the MenuItem was selected and perform the appropriate action
        switch (menuItem.getItemId()) {
            case R.id.nav_admin_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventOverviewFragment()).commit();
                break;
            case R.id.nav_admin_x:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CreateEventActivity()).commit();
                Toast.makeText(this, "You've selected create event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_admin_user_view:
                // Toast.makeText(this, "You've selected user view", Toast.LENGTH_SHORT).show();

                // Open the user view event page
                Intent Load = new Intent(getApplicationContext(), EventOverviewActivity.class);
                startActivity(Load);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
