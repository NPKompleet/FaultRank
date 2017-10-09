package com.npkompleet.phenomenon.faultrank.presenters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.npkompleet.phenomenon.faultrank.AddFaultFragment;
import com.npkompleet.phenomenon.faultrank.MainActivity;
import com.npkompleet.phenomenon.faultrank.model.Fault;
import com.npkompleet.phenomenon.faultrank.views.IAddFaultView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PHENOMENON on 7/16/2017.
 *
 *
 */

public class AddFaultPresenter {
    private AddFaultFragment view;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageRef;

    /**
     * @param view a Fragment that implemennts the @link IAddFaultView interface
     */
    public AddFaultPresenter(IAddFaultView view){
        this.view= (AddFaultFragment)  view;
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference().child("faults");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

    }

    public void saveFaultData(Uri filePath, String... values){

        String bUnit= values[0];
        String uT= values[1];
        double revenue= Double.parseDouble(values[2]);
        double energy= Double.parseDouble(values[3]);
        double mEff= Double.parseDouble(values[4]);
        long customers= Long.parseLong(values[5]);
        String fType= values[6];
        double cost= Double.parseDouble(values[7]);
        double avail=Double.parseDouble(values[8]);


        SimpleDateFormat fmt= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        Date date=null;
        try {
            date=fmt.parse(values[9]);
        }catch (ParseException e){
            e.printStackTrace();
        }

        long dt= date.getTime();

        String loc= values[10];



        Fault fault= new Fault(bUnit, uT, revenue, energy, mEff, customers, fType, cost, avail, dt, loc);

        view.showProgressDialog();
        DatabaseReference keyReference= databaseReference.push();
        keyReference.setValue(fault);

        StorageReference childRef = storageRef.child(keyReference.getKey());

        //uploading the image
        UploadTask uploadTask = childRef.putFile(filePath);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                view.dismissProgressDialog();
                view.showToast("Upload successful");
                view.resetViews();
                /*pd.dismiss();
                Toast.makeText(getA, "Upload successful", Toast.LENGTH_SHORT).show();*/
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.dismissProgressDialog();
                view.showToast("Upload Failed -> "+ e);
                /*pd.dismiss();
                Toast.makeText(A, "Upload Failed -> " + e, Toast.LENGTH_SHORT).show();*/
            }
        });

    }
}
