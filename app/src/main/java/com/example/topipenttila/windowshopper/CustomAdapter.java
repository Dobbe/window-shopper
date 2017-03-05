package com.example.topipenttila.windowshopper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by topipenttila on 04/03/17.
 */

public class CustomAdapter extends ArrayAdapter<ListObject> {

    final String TAG = "CustomAdapter";

    public CustomAdapter (Context context, ArrayList<ListObject> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListObject object = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.product_name);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.product_description);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.product_price);
        ImageView productImage = (ImageView) convertView.findViewById(R.id.product_picture);
        ImageView companyImage = (ImageView) convertView.findViewById(R.id.company_picture);
        // Populate the data into the template view using the data object
        tvName.setText(object.name);
        tvDescription.setText(object.description);
        tvPrice.setText(object.price);
        Log.e(TAG, object.name);
        if (object.name.equals("Samsung TV")) productImage.setImageResource(R.drawable.flat_tv);
        if (object.name.equals("Coca Cola")) productImage.setImageResource(R.drawable.coke);
        if (object.name == "Samsung Fridge") productImage.setImageResource(R.drawable.fridge);

        if (object.store.equals("Home Corp")) companyImage.setImageResource(R.drawable.homecorp);
        if (object.store.equals("Pick N Pay")) companyImage.setImageResource(R.drawable.picknpay);
        if (object.store.equals("Hi fi Corp ")) companyImage.setImageResource(R.drawable.hifi);
        // Return the completed view to render on screen
        return convertView;
    }
}