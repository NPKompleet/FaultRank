package com.example.phenomenon.faultrank.presenters;

import android.database.Cursor;

import com.example.phenomenon.faultrank.model.Fault;
import com.example.phenomenon.faultrank.provider.FaultContract;
import com.example.phenomenon.faultrank.views.IFaultDetailView;

import static com.example.phenomenon.faultrank.provider.FaultContract.getColumnString;

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
        view.fillDetail(
                //getColumnString(c, FaultContract.COLUMN_UNDERTAKING)
                fault.getUndertaking()
        );
    }
}
