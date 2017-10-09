package com.npkompleet.phenomenon.faultrank.dagger;

import com.npkompleet.phenomenon.faultrank.FaultDetailFragment;
import com.npkompleet.phenomenon.faultrank.FaultListFragment;
import com.npkompleet.phenomenon.faultrank.presenters.FaultListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by PHENOMENON on 9/20/2017.
 */

@Singleton
@Component(modules={AppModule.class, PresenterModule.class, FirebaseModule.class})
public interface AppComponent {
    //inject into fragments
    void inject(FaultListFragment target);
    void inject(FaultDetailFragment target);

    //inject into presenters
    void inject(FaultListPresenter target);
}
