package com.example.phenomenon.faultrank.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phenomenon.faultrank.R;
import com.example.phenomenon.faultrank.provider.FaultContract;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.phenomenon.faultrank.provider.FaultContract.getColumnString;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 */

public class FaultListAdapter extends RecyclerView.Adapter<FaultListAdapter.FaultHolder>{
    private Cursor cursor;
    private FaultClickListener listener;

    public interface FaultClickListener{
        void faultSelected();
    }

    public FaultListAdapter(Cursor cursor, FaultClickListener listener){
        this.cursor= cursor;
        this.listener= listener;

    }

    public void setCursor(Cursor cursor){
        this.cursor= cursor;
        notifyDataSetChanged();
        //Toast.makeText(context, "cursor set", Toast.LENGTH_LONG).show();
    }

    @Override
    public FaultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fault_list_content, parent, false);

        return new FaultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FaultHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.textView.setText(getColumnString(cursor, FaultContract.COLUMN_UNDERTAKING));

    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }

    public class FaultHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.list_undertakingName)
        TextView textView;

        public FaultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           /* if (Build.VERSION.SDK_INT >= 21 ){
                itemView.setElevation(8f);
                itemView.setBackgroundColor(Color.BLUE);
                textView.setTextColor(Color.RED);
            }*/
            listener.faultSelected();
        }
    }
}
