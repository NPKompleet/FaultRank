package com.example.phenomenon.faultrank.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PHENOMENON on 7/25/2017.
 *
 */

public class Fault implements Parcelable{
    private String businessUnit;
    private String undertaking;
    private double revenue;
    private double energy;
    private double market_efficiency;
    private long customers;
    private String faultType;
    private double cost;
    private double availability;
    private long date;
    private double priority_index;
    private String location;


    public Fault(){

    }

    public Fault(String bUnit, String ut, double rev, double ener, double mEff, long cus, String fType,
                 double cst, double avail, long dt, String loc){
        businessUnit= bUnit;
        undertaking= ut;
        revenue= rev;
        energy= ener;
        market_efficiency= mEff;
        customers= cus;
        faultType= fType;
        cost= cst;
        availability= avail;
        date= dt;
        location= loc;

        setPriority_index(rev, mEff, cst, avail);
    }

    protected Fault(Parcel in) {
        businessUnit = in.readString();
        undertaking = in.readString();
        revenue = in.readDouble();
        energy = in.readDouble();
        market_efficiency = in.readDouble();
        customers = in.readLong();
        faultType = in.readString();
        cost = in.readDouble();
        availability = in.readDouble();
        date = in.readLong();
        location= in.readString();
        priority_index = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(businessUnit);
        dest.writeString(undertaking);
        dest.writeDouble(revenue);
        dest.writeDouble(energy);
        dest.writeDouble(market_efficiency);
        dest.writeLong(customers);
        dest.writeString(faultType);
        dest.writeDouble(cost);
        dest.writeDouble(availability);
        dest.writeLong(date);
        dest.writeString(location);
        dest.writeDouble(priority_index);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fault> CREATOR = new Creator<Fault>() {
        @Override
        public Fault createFromParcel(Parcel in) {
            return new Fault(in);
        }

        @Override
        public Fault[] newArray(int size) {
            return new Fault[size];
        }
    };

    public String getBusinessUnit() {
        return businessUnit;
    }

    public String getUndertaking() {
        return undertaking;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getEnergy() {
        return energy;
    }

    public double getMarket_efficiency() {
        return market_efficiency;
    }

    public long getCustomers() {
        return customers;
    }

    public String getFaultType() {
        return faultType;
    }

    public double getCost() {
        return cost;
    }

    public double getAvailability() {
        return availability;
    }

    public long getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public double getPriority_index() {
        return priority_index;
    }

    public void setBusinessUnit(String bUnit) {
        businessUnit = bUnit;
    }

    public void setUndertaking(String ut) {
        undertaking = ut;
    }

    public void setRevenue(double rev) {
        revenue = rev;
    }

    public void setEnergy(double e) {
        energy = e;
    }

    public void setMarket_efficiency(double me) {
        market_efficiency = me;
    }

    public void setCustomers(long cus) {
        customers = cus;
    }

    public void setFaultType(String fType) {
        faultType = fType;
    }

    public void setCost(double c) {
        cost = c;
    }

    public void setAvailability(double avail) {
        availability = avail;
    }

    public void setDate(long d) {
        date = d;
    }

    public void setLocation(String loc) {
        location = loc;
    }

    public void setPriority_index(double pI){ priority_index = pI;}

    private void setPriority_index(double revenue, double market_efficiency, double cost, double availability) {
        priority_index= calculatePriorityIndex(revenue, market_efficiency, cost, availability);
    }

    public static double calculatePriorityIndex(double revenue, double market_efficiency, double cost, double availability){
        return (market_efficiency * Math.pow(revenue, 2))/(availability * cost);
    }

}
