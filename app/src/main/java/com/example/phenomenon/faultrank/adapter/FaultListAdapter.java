package com.example.phenomenon.faultrank.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PHENOMENON on 7/16/2017.
 */

public class FaultListAdapter extends RecyclerView.Adapter<FaultListAdapter.FaultHolder>{
    Cursor cursor;

    public FaultListAdapter(Cursor cursor){
        this.cursor= cursor;

    }

    public void setCursor(Cursor cursor){
        this.cursor= cursor;
        this.notifyDataSetChanged();
    }

    @Override
    public FaultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FaultHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FaultHolder extends RecyclerView.ViewHolder{

        public FaultHolder(View itemView) {
            super(itemView);
        }
    }
}
