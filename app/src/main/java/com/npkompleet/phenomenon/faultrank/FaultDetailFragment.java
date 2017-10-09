package com.npkompleet.phenomenon.faultrank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.npkompleet.phenomenon.faultrank.model.Fault;
import com.npkompleet.phenomenon.faultrank.presenters.FaultDetailPresenter;
import com.npkompleet.phenomenon.faultrank.views.IFaultDetailView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;



public class FaultDetailFragment extends Fragment implements IFaultDetailView{
    //private static Cursor cursor;
    Unbinder unbinder;
    FaultDetailPresenter presenter;

    @BindView(R.id.detail_image)
    ImageView imageView;

    @BindView(R.id.fault_detail_undertaking)
    TextView utView;

    @BindView(R.id.fault_detail_faultType)
    TextView ftView;

    @BindView(R.id.fault_detail_location)
    TextView locView;

    @BindView(R.id.fault_detail_revenue)
    TextView revView;

    @BindView(R.id.fault_detail_cost)
    TextView costView;

    @BindView(R.id.fault_detail_time)
    TextView dateView;

    @BindView(R.id.fault_detail_porev)
    TextView porevView;

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
        setHasOptionsMenu(true);
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Are you sure the fault is cleared?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        presenter.deleteFault(fault);
                        Snackbar.make(ftView, "Fault cleared..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        getActivity().getSupportFragmentManager()
                                .popBackStack();
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();

        final FloatingActionButton fab= getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_done_all);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* AddFaultFragment addFaultFragment= AddFaultFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainViewContainer, addFaultFragment)
                        .addToBackStack("addFault")
                        .commit();
*/
                alertDialog.show();

                /*Snackbar.make(fab, "Save", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
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
        Picasso.with(getActivity())
                .load("https://firebasestorage.googleapis.com/v0/b/faultrankproject.appspot.com/o/" +strings[6]+
                        "?alt=media&token=9ab52356-7570-451b-a489-ed1d0e4f907b")
                .resize(480, 480)
                .error(R.drawable.substation)
                .placeholder(R.drawable.substation)
                .into(imageView);

        ftView.setText(strings[0]);
        locView.setText(strings[1]);
        revView.setText(strings[2]);
        costView.setText(strings[3]);
        dateView.setText(DateUtils.getRelativeTimeSpanString(getContext(), Long.valueOf(strings[4]), true));
        utView.setText(strings[5]);
        porevView.setText(strings[7]);

    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fault_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_share_fault) {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String text = "Hi, here is a " + fault.getFaultType()
                    + " that you might be able to help resolve."
                    + " The fault occured " + DateUtils.getRelativeTimeSpanString(getContext(), fault.getDate(), true)
                    + " at " + fault.getLocation()+ "." +"\n"
                    + "Currently, "+ fault.getCustomers()+ " customers are affected";

            share.putExtra(Intent.EXTRA_TEXT, text);
            getContext().startActivity(share);
        }
        return super.onOptionsItemSelected(item);
    }
}
