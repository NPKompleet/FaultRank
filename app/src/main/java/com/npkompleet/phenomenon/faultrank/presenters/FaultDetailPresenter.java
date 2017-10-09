package com.npkompleet.phenomenon.faultrank.presenters;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.npkompleet.phenomenon.faultrank.model.Fault;
import com.npkompleet.phenomenon.faultrank.views.IFaultDetailView;

import java.util.Calendar;

/**
 * Created by PHENOMENON on 7/19/2017.
 *
 */

public class FaultDetailPresenter {
    private IFaultDetailView view;
    public FaultDetailPresenter(IFaultDetailView view){
        this.view= view;
    }

    public void loadDetail(Fault fault){
        int hours= (int)(Calendar.getInstance().getTimeInMillis()-fault.getDate())/3600000;
        view.fillDetail(
                //getColumnString(c, FaultContract.COLUMN_UNDERTAKING)
                fault.getFaultType(),
                fault.getLocation(),
                "$"+String.valueOf(fault.getRevenue())+"Million",
                "$"+String.valueOf(fault.getCost())+"Million",
                String.valueOf(fault.getDate()),
                fault.getUndertaking(),
                fault.getKey(),
                "$"+String.valueOf((float)(fault.getRevenue()/fault.getAvailability()*hours))+"Million"
        );
    }

    public void deleteFault(Fault fault){
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getReference().child("faults").child(fault.getKey());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child(fault.getKey());
        databaseReference.removeValue();
        storageRef.delete();

    }
}
