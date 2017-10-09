package com.npkompleet.phenomenon.faultrank.dagger;

import android.content.Context;

import com.npkompleet.phenomenon.faultrank.presenters.FaultListPresenter;
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
    public FaultListPresenter provideFaultListPresenter(Context context){
        return new FaultListPresenter(context);
    }
}
