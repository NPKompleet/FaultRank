package com.example.phenomenon.faultrank.views;

import android.database.Cursor;

/**
 * Created by PHENOMENON on 7/19/2017.
 *
 */

public interface IFaultDetailView {
    void loadDetail(Cursor cursor);
    void fillDetail(String... strings);
}
