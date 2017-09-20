package com.example.phenomenon.faultrank.dagger;

import com.example.phenomenon.faultrank.FaultListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by PHENOMENON on 9/20/2017.
 */

@Singleton
@Component(modules={AppModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(FaultListFragment target);
}
