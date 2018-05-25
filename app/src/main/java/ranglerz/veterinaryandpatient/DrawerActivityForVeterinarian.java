package ranglerz.veterinaryandpatient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ranglerz.veterinaryandpatient.Preferences.Prefs;

public class DrawerActivityForVeterinarian extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    MenuItem navLoginRegister;
    MenuItem navUsername;
    MenuItem navPaymentMethods;
    // MenuItem navChosePlane;
    // MenuItem navFranchiser;
    MenuItem navContactUs;
    MenuItem navViewYourProperties;
    MenuItem navBuyerActvity;
    MenuItem navShowFranchiserList;
    MenuItem navTermsAndCondtion;
    MenuItem navShareApp;
    MenuItem navLiveSupport;
    MenuItem navHelp;
    MenuItem navChatRoom;

    View view;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_for_veterinarian);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        view = new View(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;
        mNavigationView.setItemIconTintList(null);

        // get menu from navigationView
        Menu menu = mNavigationView.getMenu();

        // find MenuItem you want to change
        navUsername = menu.findItem(R.id.nav_item_home);
        navPaymentMethods = menu.findItem(R.id.nav_item_help);


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                if (menuItem.getItemId() == R.id.nav_item_home) {
                    //home activity
                   startActivity(new Intent(DrawerActivityForVeterinarian.this, ProfileUpdateForVeterinary.class));

                }

                if (menuItem.getItemId() == R.id.nav_item_my_orders) {




                }
                if (menuItem.getItemId() == R.id.nav_item_help){



                }
                if (menuItem.getItemId() == R.id.nav_item_about){

                    Intent i = new Intent(DrawerActivityForVeterinarian.this, AboutUs.class);
                    startActivity(i);

                }
                if (menuItem.getItemId() == R.id.nav_item_contact_us){

                    Intent i = new Intent(DrawerActivityForVeterinarian.this, ContactUs.class);
                    startActivity(i);
                }

                if (menuItem.getItemId() == R.id.logout){

                    Prefs.clearPrefData(getApplicationContext());
                    startActivity(new Intent(DrawerActivityForVeterinarian.this, UserLoginActivity.class));

                    finish();

                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();



    }//end on Create

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String fullName = Prefs.getUserFullNameFromPref(getApplicationContext());
        navUsername.setTitle(fullName);

    }







}