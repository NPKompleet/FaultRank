package com.example.phenomenon.faultrank.dagger;

import android.content.Context;

import com.example.phenomenon.faultrank.presenters.FaultListPresenter;
import com.example.phenomenon.faultrank.views.IFaultListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PHENOMENON on 9/20/2017.
 *
 */

@Module
public class PresenterModule {

    @Singleton
    @Provides
    public FaultListPresenter provideFaultListPresenter(){
        return new FaultListPresenter();
    }
}
