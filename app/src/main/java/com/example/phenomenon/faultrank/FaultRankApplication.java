package com.example.phenomenon.faultrank;

import android.app.Application;

import com.example.phenomenon.faultrank.dagger.AppComponent;
import com.example.phenomenon.faultrank.dagger.AppModule;
import com.example.phenomenon.faultrank.dagger.DaggerAppComponent;

/**
 * Created by PHENOMENON on 9/20/2017.
 */

public class FaultRankApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= initDagger(this);
    }


    protected AppComponent initDagger(FaultRankApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }


}
