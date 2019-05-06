package com.java.ppm.vii.thepoint.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.java.ppm.vii.thepoint.R;
import com.java.ppm.vii.thepoint.admin.AdminActivity;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Use Toolbar as ActionBar due to default ActionBar removed
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the hamburger icon for the drawer
        drawer = findViewById(R.id.drawer_layout_user);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Create a reference to the navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Launch a default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventOverviewFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_user_events);
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
            case R.id.nav_user_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EventOverviewFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_user_about_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_user_contact_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_user_instagram:
                Toast.makeText(this, "Opening Instagram page...", Toast.LENGTH_SHORT).show();
                goToSite("https://www.instagram.com/ntsuents");
                break;
            case R.id.nav_user_twitter:
                Toast.makeText(this, "Opening Twitter page...", Toast.LENGTH_SHORT).show();
                goToSite("https://twitter.com/NTSUents");
                break;
            case R.id.nav_user_facebook:
                Toast.makeText(this, "Opening Facebook page...", Toast.LENGTH_SHORT).show();
                goToSite("https://www.facebook.com/NTSUClifton");
                break;
            case R.id.nav_user_admin_area:
                // Open the admin area
                Intent intentAdminArea = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intentAdminArea);
                break;
        }

        // What ever action happened, close the drawer
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * Go to the specified web page
     *
     * @param site The web page of which to go to
     */
    private void goToSite(String site) {
        Uri webPage = Uri.parse(site);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);

        // Check if there is an activity to handle this intent, if so, open it
        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        }
    }
}
