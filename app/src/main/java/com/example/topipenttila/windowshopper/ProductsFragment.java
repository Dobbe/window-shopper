package com.example.topipenttila.windowshopper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by topipenttila on 04/03/17.
 */

public class ProductsFragment extends Fragment {

    private String TAG = "ProductsFragment";
    // List view
    private ListView lv;
    EditText inputSearch;
    ArrayAdapter<ListObject> arrayAdapter;
    ArrayList<ListObject> objects = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.products_fragment, container, false);
        v.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        lv = (ListView) v.findViewById(R.id.list_view);


        final ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                v.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                objects.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    objects.add(new ListObject(snapshot.child("name").getValue().toString(), snapshot.child("description").getValue().toString(), snapshot.child("price").getValue().toString(), snapshot.child("store").getValue().toString()));
                    // Adding items to listview
                    arrayAdapter = new CustomAdapter(v.getContext(), objects);
                    lv.setAdapter(arrayAdapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        };

        inputSearch = (EditText) v.findViewById(R.id.inputSearch);

        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    v.findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    Query query = myRef.child("products").orderByChild("description")
                            .startAt(inputSearch.getText().toString())
                            .endAt(inputSearch.getText().toString()+"\uf8ff");
                    query.addValueEventListener(valueEventListener);

                    Log.e(TAG, "onEditorAction: " + inputSearch.getText());
                    return true;
                }
                return false;
            }
        });

        return v;
    }
}
