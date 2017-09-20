package com.example.phenomenon.faultrank.presenters;

import android.app.Fragment;

import android.content.ContentValues;

import com.example.phenomenon.faultrank.AddFaultFragment;
import com.example.phenomenon.faultrank.model.Fault;
import com.example.phenomenon.faultrank.provider.FaultContract;
import com.example.phenomenon.faultrank.service.FaultUpdateService;
import com.example.phenomenon.faultrank.views.IAddFaultView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 *
 */

public class AddFaultPresenter {
    private AddFaultFragment view;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    /**
     * @param view a Fragment that implemennts the @link IAddFaultView interface
     */
    public AddFaultPresenter(IAddFaultView view){
        this.view= (AddFaultFragment)  view;
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference().child("faults");

    }

    public void saveFaultData(String... values){
        /*ContentValues cv= new ContentValues();
        cv.put(FaultContract.COLUMN_BU, values[0]);
        cv.put(FaultContract.COLUMN_UNDERTAKING, values[1]);
        cv.put(FaultContract.COLUMN_REVENUE, Float.parseFloat(values[2]));
        cv.put(FaultContract.COLUMN_ENERGY, Float.parseFloat(values[3]));
        cv.put(FaultContract.COLUMN_REVENUE_PER_ENERGY, Float.parseFloat(values[2])/Float.parseFloat(values[3]));
        cv.put(FaultContract.COLUMN_NUM_OF_CUSTOMERS, Long.parseLong(values[4]));
        cv.put(FaultContract.COLUMN_FAULT_TYPE, values[5]);
        cv.put(FaultContract.COLUMN_COST, Float.parseFloat(values[6]));*/

        String bUnit= values[0];
        String uT= values[1];
        double revenue= Double.parseDouble(values[2]);
        double energy= Double.parseDouble(values[3]);
        double mEff= Double.parseDouble(values[4]);
        long customers= Long.parseLong(values[5]);
        String fType= values[6];
        double cost= Double.parseDouble(values[7]);
        double avail=Double.parseDouble(values[8]);


        SimpleDateFormat fmt= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        Date date=null;
        try {
            date=fmt.parse(values[9]);
        }catch (ParseException e){
            e.printStackTrace();
        }

        long dt= date.getTime();

        String loc= values[10];
        //Calendar c=Calendar.getInstance();
        //c.setTime(date);
        //cv.put(FaultContract.COLUMN_DATE, date.getTime());

        //FaultUpdateService.insertNewFault(view.getActivity(), cv);

        Fault fault= new Fault(bUnit, uT, revenue, energy, mEff, customers, fType, cost, avail, dt, loc);

        databaseReference.push().setValue(fault);

    }
}
