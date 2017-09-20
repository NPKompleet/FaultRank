package com.example.phenomenon.faultrank.views;

import android.database.Cursor;

import com.example.phenomenon.faultrank.model.Fault;

import java.util.ArrayList;

/**
 * Created by PHENOMENON on 7/16/2017.
 */

public interface IFaultListView {
    void loadData(ArrayList<Fault> data);
}
