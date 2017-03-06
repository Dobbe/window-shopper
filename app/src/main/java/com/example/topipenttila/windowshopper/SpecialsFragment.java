package com.example.topipenttila.windowshopper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

/**
 * Created by topipenttila on 04/03/17.
 */

public class SpecialsFragment extends Fragment {
    private final String TAG = "SpecialsFragment";
    // List view
    private ListView lv;
    // Listview Adapter
    ArrayAdapter<ListObject> arrayAdapter;
    ArrayList<ListObject> objects = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.specials_fragment, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        ValueEventListener eventListener;
        // Listview Data
        lv = (ListView) v.findViewById(R.id.list_view);
        arrayAdapter = new CustomAdapter(v.getContext(), objects);
        lv.setAdapter(arrayAdapter);

        // Read from the database
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                objects.clear();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    objects.add(new ListObject(snapshot.child("name").getValue().toString(), snapshot.child("description").getValue().toString(), snapshot.child("price").getValue().toString(), snapshot.child("store").getValue().toString()));
                }
                arrayAdapter.notifyDataSetChanged();
                //arrayAdapter = new CustomAdapter(v.getContext(), objects);
                //lv.setAdapter(arrayAdapter);
                v.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read value.", error.toException());
            }
        };

        myRef.child("specials").addValueEventListener(eventListener);

        return v;
    }
}
