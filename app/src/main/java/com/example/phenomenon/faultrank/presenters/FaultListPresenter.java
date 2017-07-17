package com.example.phenomenon.faultrank.presenters;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.phenomenon.faultrank.FaultListFragment;
import com.example.phenomenon.faultrank.provider.FaultProvider;
import com.example.phenomenon.faultrank.views.IFaultListView;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 */

public class FaultListPresenter implements LoaderManager.LoaderCallbacks<Cursor>{
    private IFaultListView view;
    private FaultListFragment fragment;
    private Context context;
    private static final int FAULT_LOADER = 11;

    public FaultListPresenter(IFaultListView view, FaultListFragment fragment, Context context){
        this.view=  view;
        this.fragment= fragment;
        this.context= context;
    }

    public void initData(){
        fragment.getActivity().getSupportLoaderManager().initLoader(FAULT_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(context, FaultProvider.Faults.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.loadData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
