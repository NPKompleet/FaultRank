package com.example.phenomenon.faultrank;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.phenomenon.faultrank.model.Fault;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FaultListFragment.ListFragmentInteractionListener,
        FaultDetailFragment.OnFragmentInteractionListener,
        AddFaultFragment.OnFragmentInteractionListener{

    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.fab) FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState==null) {
            FaultListFragment listFragment = FaultListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainViewContainer, listFragment)
                    .commit();
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FirebaseMessaging.getInstance().subscribeToTopic("faults");
    }


    @OnClick(R.id.fab)
    public void clickView(View view){
        /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

        AddFaultFragment addFaultFragment= AddFaultFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainViewContainer, addFaultFragment)
                .commit();
        fab.setImageResource(R.drawable.ic_save);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fault_list) {
            // display fault list
            FaultListFragment listFragment = FaultListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainViewContainer, listFragment)
                    .commit();

        } else if (id == R.id.nav_add_fault) {
            AddFaultFragment addFaultFragment= AddFaultFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainViewContainer, addFaultFragment)
                    .commit();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(){
        //do nne
    }

    @Override
    public void onListItemInteraction(Fault fault) {
        FaultDetailFragment detailFragment= FaultDetailFragment.newInstance(fault);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainViewContainer, detailFragment)
                .addToBackStack("detail")
                .commit();

    }
}
