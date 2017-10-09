package com.npkompleet.phenomenon.faultrank.views;

import com.npkompleet.phenomenon.faultrank.model.Fault;

import java.util.ArrayList;

/**
 * Created by PHENOMENON on 7/16/2017.
 */

public interface IFaultListView {
    void loadData(ArrayList<Fault> data);
    void endRefresh();
}
