package com.example.phenomenon.faultrank.presenters;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.phenomenon.faultrank.FaultListFragment;
import com.example.phenomenon.faultrank.MainActivity;
import com.example.phenomenon.faultrank.R;
import com.example.phenomenon.faultrank.model.Fault;
import com.example.phenomenon.faultrank.provider.FaultProvider;
import com.example.phenomenon.faultrank.views.IFaultListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 */

public class FaultListPresenter /*implements LoaderManager.LoaderCallbacks<Cursor>*/{
    private IFaultListView view;
    //private FaultListFragment fragment;
    //private Context context;
    //private static final int FAULT_LOADER = 11;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ArrayList<Fault> faultArray= new ArrayList<>();

    public FaultListPresenter(){
        //this.view=  view;
        //this.fragment= fragment;
        //this.context= context;
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference().child("faults");

    }

    public void setView(IFaultListView view){
        this.view=  view;
    }

    public void initData(){
        //fragment.getActivity().getSupportLoaderManager().initLoader(FAULT_LOADER, null, this);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot faultSnapshot: dataSnapshot.getChildren()) {
                    // handle the faults
                    Fault fault= faultSnapshot.getValue(Fault.class);
                    faultArray.add(fault);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        view.loadData(faultArray);
    }

    /*@Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, FaultProvider.Faults.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.loadData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }*/
}
