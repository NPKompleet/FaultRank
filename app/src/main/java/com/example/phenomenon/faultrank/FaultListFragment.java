package com.example.phenomenon.faultrank;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phenomenon.faultrank.adapter.FaultListAdapter;
import com.example.phenomenon.faultrank.presenters.FaultListPresenter;
import com.example.phenomenon.faultrank.views.IFaultListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FaultListFragment extends Fragment implements IFaultListView, FaultListAdapter.FaultClickListener{
    FaultListPresenter presenter;
    Unbinder unbinder;

    @BindView(R.id.rv_fault_list) RecyclerView recyclerView;

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
        unbinder=ButterKnife.bind(this, v);
        adapter= new FaultListAdapter(null, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        presenter= new FaultListPresenter(this, this, getActivity());

        Toast.makeText(getActivity(), "do", Toast.LENGTH_SHORT).show();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.initData();

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
    public void loadData(Cursor data) {
        Toast.makeText(getActivity(), "count "+ data.getCount(), Toast.LENGTH_SHORT).show();
        adapter.setCursor(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void faultSelected() {
        Toast.makeText(getActivity(), "fault", Toast.LENGTH_SHORT).show();
        mListener.onListItemInteraction();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ListFragmentInteractionListener {
        void onListItemInteraction();
    }
}
