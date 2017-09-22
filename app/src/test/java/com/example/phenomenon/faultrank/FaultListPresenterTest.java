package com.example.phenomenon.faultrank;

import android.content.Context;

import com.example.phenomenon.faultrank.model.Fault;
import com.example.phenomenon.faultrank.presenters.FaultListPresenter;
import com.example.phenomenon.faultrank.views.IFaultListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
