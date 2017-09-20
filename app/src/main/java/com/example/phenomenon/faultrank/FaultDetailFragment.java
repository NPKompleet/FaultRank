package com.example.phenomenon.faultrank;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phenomenon.faultrank.adapter.FaultListAdapter;
import com.example.phenomenon.faultrank.model.Fault;
import com.example.phenomenon.faultrank.presenters.FaultDetailPresenter;
import com.example.phenomenon.faultrank.views.IFaultDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FaultDetailFragment extends Fragment implements IFaultDetailView{
    //private static Cursor cursor;
    Unbinder unbinder;
    FaultDetailPresenter presenter;

    @BindView(R.id.fault_detail_undertaking)
    TextView utView;

    // the fragment initialization parameters
    private static final String FAULT_PARAM = "fault";

    private Fault fault;


    private OnFragmentInteractionListener mListener;

    public FaultDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fault {@link Fault}.
     * @return A new instance of fragment FaultDetailFragment.
     */
    public static FaultDetailFragment newInstance(Fault fault) {
        FaultDetailFragment fragment = new FaultDetailFragment();
        //fault= fault;
        Bundle args = new Bundle();
        args.putParcelable(FAULT_PARAM, fault);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fault = getArguments().getParcelable(FAULT_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_fault_detail, container, false);
        unbinder=ButterKnife.bind(this, view);
        presenter = new FaultDetailPresenter(this);
        presenter.loadDetail(fault);
        //loadDetail(cursor);

        // Inflate the layout for this fragment
        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*@Override
    public void loadDetail(Cursor cursor) {
        presenter.loadDetail(cursor);
    }*/

    @Override
    public void fillDetail(String... strings) {
        utView.setText(strings[0]);
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction();
    }
}
