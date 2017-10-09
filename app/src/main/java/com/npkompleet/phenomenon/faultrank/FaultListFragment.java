package com.npkompleet.phenomenon.faultrank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.npkompleet.phenomenon.faultrank.adapter.FaultListAdapter;
import com.npkompleet.phenomenon.faultrank.model.Fault;
import com.npkompleet.phenomenon.faultrank.presenters.FaultListPresenter;
import com.npkompleet.phenomenon.faultrank.views.IFaultListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FaultListFragment extends Fragment implements IFaultListView, FaultListAdapter.FaultClickListener,
    SwipeRefreshLayout.OnRefreshListener{

    @Inject
    FaultListPresenter presenter;

    Unbinder unbinder;
    ArrayList<Fault> faults;

    @BindView(R.id.rv_fault_list) RecyclerView recyclerView;
    @BindView(R.id.swipe_fault_list) SwipeRefreshLayout swipeRefreshLayout;

    FaultListAdapter adapter;


    private ListFragmentInteractionListener mListener;

    public FaultListFragment() {
        // Required empty public constructor
    }

    public static FaultListFragment newInstance() {
        return new FaultListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_fault_list, container, false);
        ((FaultRankApplication)getActivity().getApplication()).getAppComponent().inject(this);
        unbinder=ButterKnife.bind(this, v);
        presenter.setView(this);
        /*adapter= new FaultListAdapter(faults, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);*/

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.primary_dark),
                getResources().getColor(R.color.accent),
                getResources().getColor(R.color.primary_text)

        );
        adapter= new FaultListAdapter(faults, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);

        final FloatingActionButton fab= getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFaultFragment addFaultFragment= AddFaultFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainViewContainer, addFaultFragment)
                        .addToBackStack("addFault")
                        .commit();
                Snackbar.make(fab, "Save", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        onRefresh();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentInteractionListener) {
            mListener = (ListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void loadData(ArrayList<Fault> data) {
        Toast.makeText(getActivity(), "count "+ data.size(), Toast.LENGTH_SHORT).show();
        adapter.setData(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void faultSelected(Fault fault) {
        Toast.makeText(getActivity(), "fault", Toast.LENGTH_SHORT).show();
        mListener.onListItemInteraction(fault);
    }

    @Override
    public void onRefresh(){
        swipeRefreshLayout.setRefreshing(true);
        presenter.initData();
    }

    public void endRefresh(){
        swipeRefreshLayout.setRefreshing(false);
    }


    public interface ListFragmentInteractionListener {
        void onListItemInteraction(Fault f);
    }
}
