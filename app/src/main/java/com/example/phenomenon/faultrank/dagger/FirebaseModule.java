package com.example.phenomenon.faultrank.dagger;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PHENOMENON on 9/20/2017.
 */

@Module
public class FirebaseModule {
    @Singleton
    @Provides
    public FirebaseDatabase provideDatabase(){
        return FirebaseDatabase.getInstance();
    }

    @Singleton
    @Provides
    public DatabaseReference provideDbReference(FirebaseDatabase firebaseDatabase){
        return firebaseDatabase.getReference().child("faults");
    }

}
