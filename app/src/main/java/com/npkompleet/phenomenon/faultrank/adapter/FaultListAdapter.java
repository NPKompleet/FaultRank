package com.npkompleet.phenomenon.faultrank.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.npkompleet.phenomenon.faultrank.R;
import com.npkompleet.phenomenon.faultrank.model.Fault;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 */

public class FaultListAdapter extends RecyclerView.Adapter<FaultListAdapter.FaultHolder>{
    private Cursor cursor;
    private FaultClickListener listener;
    private ArrayList<Fault> faults;
    private Context context;

    public interface FaultClickListener{
        void faultSelected(Fault f);
    }

    public FaultListAdapter(Cursor cursor, FaultClickListener listener){
        this.cursor= cursor;
        this.listener= listener;

    }

    public FaultListAdapter(ArrayList<Fault> faults, FaultClickListener listener){
        this.faults= faults;
        this.listener= listener;

    }

    public void setCursor(Cursor cursor){
        this.cursor= cursor;
        notifyDataSetChanged();
        //Toast.makeText(context, "cursor set", Toast.LENGTH_LONG).show();
    }

    public void setData(ArrayList<Fault> faults){
        this.faults= faults;
        notifyDataSetChanged();
    }

    @Override
    public FaultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.fault_list_content, parent, false);

        return new FaultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FaultHolder holder, int position) {
        //cursor.moveToPosition(position);

        //holder.textView.setText(getColumnString(cursor, FaultContract.COLUMN_UNDERTAKING));
        Fault fault= faults.get(position);
        Picasso.with(context)
                .load("https://firebasestorage.googleapis.com/v0/b/faultrankproject.appspot.com/o/" +fault.getKey()+
                        "?alt=media&token=9ab52356-7570-451b-a489-ed1d0e4f907b")
                .resize(50, 50)
                .error(R.drawable.substation)
                .placeholder(R.drawable.substation)
                .into(holder.imageView);
        holder.faultView.setText(fault.getFaultType() + " Reported at " + fault.getLocation());
        holder.utView.setText(fault.getUndertaking()+", "+ fault.getBusinessUnit());
        holder.ctView.setText(fault.getCustomers() + " customers affected");
        holder.durationView.setText(DateUtils.getRelativeTimeSpanString(context, fault.getDate(), true));

        int hours= (int)(Calendar.getInstance().getTimeInMillis()-fault.getDate())/3600000;

        holder.revenueView.setText("$"+String.valueOf((int)(fault.getRevenue()/fault.getAvailability()*hours))+"M");

    }

    @Override
    public int getItemCount() {
        return faults != null ? faults.size() : 0;
    }

    public class FaultHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.listImage) ImageView imageView;
        @BindView(R.id.list_faultType) TextView faultView;
        @BindView(R.id.list_undertakingName) TextView utView;
        @BindView(R.id.list_customerPopulation) TextView ctView;
        @BindView(R.id.list_duration) TextView durationView;
        @BindView(R.id.list_revenueImplication) TextView revenueView;

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
            //cursor.moveToPosition(getAdapterPosition());

            //listener.faultSelected(cursor);
            listener.faultSelected(faults.get(getAdapterPosition()));
            Toast.makeText(context, String.valueOf((int)(Calendar.getInstance().getTimeInMillis()-faults.get(getAdapterPosition()).getDate())/3600000),
                    Toast.LENGTH_LONG).show();
        }
    }
}
