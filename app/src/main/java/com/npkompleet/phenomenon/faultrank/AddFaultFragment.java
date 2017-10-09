package com.npkompleet.phenomenon.faultrank;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.npkompleet.phenomenon.faultrank.presenters.AddFaultPresenter;
import com.npkompleet.phenomenon.faultrank.views.IAddFaultView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {} interface
 * to handle interaction events.
 * Use the {@link AddFaultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFaultFragment extends Fragment implements IAddFaultView, DatePickerDialog.OnDateSetListener {
    private AddFaultPresenter presenter= new AddFaultPresenter(this);
    private Unbinder unbinder;

    @BindView(R.id.add_fault_image)
    ImageView fView;
    @BindView(R.id.add_bu_spinner) EditText buView;
    @BindView(R.id.add_undertaking) EditText utView;
    @BindView(R.id.add_revenue) EditText revView;
    @BindView(R.id.add_energy) EditText energyView;
    @BindView(R.id.add_market_efficiency) EditText mEffView;
    @BindView(R.id.add_num_customers) EditText customerView;
    @BindView(R.id.add_faultType) EditText faultView;
    @BindView(R.id.add_cost) EditText costView;
    @BindView(R.id.add_availability) EditText availView;
    @BindView(R.id.add_date) EditText dateView;
    @BindView(R.id.add_location) EditText locView;

   /* @BindView(R.id.fab)
    FloatingActionButton fab;*/

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static int RESULT_LOAD_IMG= 25;

    private Uri filePath;

    ProgressDialog dialog;


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    /*private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;*/

    public AddFaultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddFaultFragment.
     */
    public static AddFaultFragment newInstance() {
        //AddFaultFragment fragment = new AddFaultFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return new AddFaultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_add_fault, container, false);
        unbinder= ButterKnife.bind(this, v);
        // Inflate the layout for this fragment
        return v;
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
        fab.setImageResource(R.drawable.ic_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFaultInfo();
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


    @OnClick({R.id.goto_gallery, R.id.add_a_photo, R.id.add_bu_spinner, R.id.add_faultType, R.id.add_date})
    public void viewClicked(View view){

        switch (view.getId()){
            case R.id.goto_gallery:

                /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image*//*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);*/
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setFixAspectRatio(true)
                        .setAspectRatio(1, 1)
                        .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                        .setOutputCompressQuality(60)
                        .start(getContext(), this);


                break;

            case R.id.add_a_photo:
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setFixAspectRatio(true)
                        .setAspectRatio(1, 1)
                        .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                        .setOutputCompressQuality(60)
                        .start(getContext(), this);
                /*Snackbar.make(view, "Save", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                break;

            case R.id.add_bu_spinner:
                PopupMenu buPopUp = new PopupMenu(getContext(), buView);
                buPopUp.getMenuInflater().inflate(R.menu.business_unit_pop_up, buPopUp.getMenu());
                buPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        buView.setText(item.getTitle());
                        return true;
                    }
                });
                buPopUp.show();
                break;

            case R.id.add_faultType:
                PopupMenu faultPopUp = new PopupMenu(getContext(), faultView);
                faultPopUp.getMenuInflater().inflate(R.menu.fault_type_pop_up, faultPopUp.getMenu());
                faultPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        faultView.setText(item.getTitle());
                        return true;
                    }
                });
                faultPopUp.show();
                break;

            case R.id.add_date:
                DatePickerFragment dialogFragment = new DatePickerFragment();
                dialogFragment.setTargetFragment(this, 0);
                dialogFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                break;

            case R.id.fab:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_LOAD_IMG) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    filePath= imageUri;

                    fView.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }else if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();

                try {
                    final InputStream imageStream = getActivity().getContentResolver().openInputStream(resultUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    filePath= resultUri;

                    fView.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);

        if (c.after(Calendar.getInstance())) {
            Snackbar.make(dateView, "Cannot Set", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{
            SimpleDateFormat fmt= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
            dateView.setText(fmt.format(c.getTime()));
        }

    }

    @Override
    public void saveFaultInfo() {
        if (filePath != null) {
            dialog= new ProgressDialog(getActivity());
            dialog.setMessage("Uploading.....");
            presenter.saveFaultData(filePath,
                    buView.getText().toString(),
                    utView.getText().toString(),
                    revView.getText().toString(),
                    energyView.getText().toString(),
                    mEffView.getText().toString(),
                    customerView.getText().toString(),
                    faultView.getText().toString(),
                    costView.getText().toString(),
                    availView.getText().toString(),
                    dateView.getText().toString(),
                    locView.getText().toString()

            );
        }else{
            Snackbar.make(dateView, "Must upload an image", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(){
        dialog.show();
    }

    public void dismissProgressDialog(){
        dialog.dismiss();
    }

    public void resetViews(){
        filePath=null;
        fView.setImageDrawable(null);
        buView.setText("");
        utView.setText("");
        revView.setText("");
        energyView.setText("");
        mEffView.setText("");
        customerView.setText("");
        faultView.setText("");
        costView.setText("");
        availView.setText("");
        dateView.setText("");
        locView.setText("");


    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}
