package com.example.phenomenon.faultrank.presenters;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.phenomenon.faultrank.FaultListFragment;
import com.example.phenomenon.faultrank.FaultRankApplication;
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

import javax.inject.Inject;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 */

public class FaultListPresenter {
    private IFaultListView view;


    @Inject
    @Nullable
    DatabaseReference databaseReference;

    ArrayList<Fault> faultArray= new ArrayList<>();

    public FaultListPresenter(){
        //default constructor for test
    }

    public FaultListPresenter(@Nullable Context context){
        if (context != null) {
            ((FaultRankApplication) context).getAppComponent().inject(this);
        }
    }

    public void setView(IFaultListView view){
        this.view=  view;
    }

    public void initData(){
        if(databaseReference != null) {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot faultSnapshot : dataSnapshot.getChildren()) {
                        // handle the faults
                        Fault fault = faultSnapshot.getValue(Fault.class);
                        faultArray.add(fault);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        view.loadData(faultArray);

    }

}
