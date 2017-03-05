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

/**
 * Created by topipenttila on 04/03/17.
 */

public class ShopsFragment extends Fragment {
    private final String TAG = "ShopsFragment";
    private ListView lv;
    // Listview Adapter
    ArrayAdapter<ListObject> arrayAdapter;
    // Search EditText
    EditText inputSearch;
    ArrayList<ListObject> objects = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.shops_fragment, container, false);
        // Listview Data
        lv = (ListView) v.findViewById(R.id.list_view_shops);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        ValueEventListener eventListener;

        // Read from the database
        myRef.child("shops").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    objects.add(new ListObject(snapshot.child("name").getValue().toString(), snapshot.child("address").getValue().toString()));
                    // Adding items to listview
                    arrayAdapter = new CustomSimpleAdapter(v.getContext(), objects);
                    lv.setAdapter(arrayAdapter);
                    v.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                }
                //String value = dataSnapshot.getValue();
                //Log.e(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read value.", error.toException());
            }
        });

        inputSearch = (EditText) v.findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                arrayAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        return v;
    }
}
