package com.npkompleet.phenomenon.faultrank.presenters;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.firebase.database.Query;
import com.npkompleet.phenomenon.faultrank.FaultRankApplication;
import com.npkompleet.phenomenon.faultrank.model.Fault;
import com.npkompleet.phenomenon.faultrank.views.IFaultListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.inject.Inject;

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
            Query myQuery= databaseReference.orderByChild("priority_index");
            myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    faultArray.clear();
                    for (DataSnapshot faultSnapshot : dataSnapshot.getChildren()) {
                        // handle the faults
                        Fault fault = faultSnapshot.getValue(Fault.class);
                        fault.setKey(faultSnapshot.getKey());
                        faultArray.add(fault);

                    }
                    view.endRefresh();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    view.endRefresh();
                }
            });
        }
        view.loadData(faultArray);

    }

}
