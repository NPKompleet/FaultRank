package com.example.phenomenon.faultrank.presenters;

import android.app.Fragment;

import android.content.ContentValues;

import com.example.phenomenon.faultrank.provider.FaultContract;
import com.example.phenomenon.faultrank.service.FaultUpdateService;
import com.example.phenomenon.faultrank.views.IAddFaultView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 *
 */

public class AddFaultPresenter {
    private Fragment view;

    /**
     *
     * @param view a Fragment that implemennts the @link IAddFaultView interface
     */
    public AddFaultPresenter(IAddFaultView view){
        this.view= (Fragment)  view;

    }

    public void saveFaultData(String... values){
        ContentValues cv= new ContentValues();
        cv.put(FaultContract.COLUMN_BU, values[0]);
        cv.put(FaultContract.COLUMN_UNDERTAKING, values[1]);
        cv.put(FaultContract.COLUMN_REVENUE, Float.parseFloat(values[2]));
        cv.put(FaultContract.COLUMN_ENERGY, Float.parseFloat(values[3]));
        cv.put(FaultContract.COLUMN_REVENUE_PER_ENERGY, Float.parseFloat(values[2])/Float.parseFloat(values[3]));
        cv.put(FaultContract.COLUMN_NUM_OF_CUSTOMERS, Long.parseLong(values[4]));
        cv.put(FaultContract.COLUMN_FAULT_TYPE, values[5]);
        cv.put(FaultContract.COLUMN_COST, Float.parseFloat(values[6]));
        SimpleDateFormat fmt= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        Date date=null;
        try {
            date=fmt.parse(values[7]);
        }catch (ParseException e){
            e.printStackTrace();
        }
        //Calendar c=Calendar.getInstance();
        //c.setTime(date);
        cv.put(FaultContract.COLUMN_DATE, date.getTime());

        FaultUpdateService.insertNewFault(view.getActivity(), cv);
    }
}
