package com.npkompleet.phenomenon.faultrank;


import com.npkompleet.phenomenon.faultrank.presenters.FaultListPresenter;
import com.npkompleet.phenomenon.faultrank.views.IFaultListView;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by PHENOMENON on 9/22/2017.
 */

public class FaultListPresenterTest {
    private IFaultListView view;
    private FaultListPresenter presenter;


    @Before
    public void mockUp(){
        view = mock(IFaultListView.class);

        presenter= new FaultListPresenter();
        presenter.setView(view);
    }

    @Test
    public void viewDataShouldBeLoaded(){
        presenter.initData();
        verify(view, times(1)).loadData(any(ArrayList.class));
    }
}
