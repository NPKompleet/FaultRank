package com.npkompleet.phenomenon.faultrank.views;


/**
 * Created by PHENOMENON on 7/16/2017.
 *
 */

public interface IAddFaultView {
    void saveFaultInfo();
    void showProgressDialog();
    void dismissProgressDialog();
    void showToast(String message);
}
