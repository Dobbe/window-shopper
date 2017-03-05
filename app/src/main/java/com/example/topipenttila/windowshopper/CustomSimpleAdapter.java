package com.example.topipenttila.windowshopper;

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
 * Created by topipenttila on 05/03/17.
 */

public class CustomSimpleAdapter extends ArrayAdapter<ListObject> {

    final String TAG = "CustomSimpleAdapter";

    public CustomSimpleAdapter (Context context, ArrayList<ListObject> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListObject object = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.company_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.company_name);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.company_address);
        ImageView companyImage = (ImageView) convertView.findViewById(R.id.company_picture);
        // Populate the data into the template view using the data object
        tvName.setText(object.name);
        tvAddress.setText(object.description);

        if (object.name.equals("Home Corp")) companyImage.setImageResource(R.drawable.homecorp);
        if (object.name.equals("Pick n Pay")) companyImage.setImageResource(R.drawable.picknpay);
        if (object.name.equals("Hi fi Corp")) companyImage.setImageResource(R.drawable.hifi);
        if (object.name.equals("Checkers")) companyImage.setImageResource(R.drawable.checkers);
        if (object.name.equals("Hi fi Corp")) companyImage.setImageResource(R.drawable.hifi);
        if (object.name.equals("Spar")) companyImage.setImageResource(R.drawable.spar);
        if (object.name.equals("Game")) companyImage.setImageResource(R.drawable.game);
        if (object.name.equals("Incredible Connection")) companyImage.setImageResource(R.drawable.incredible);
        if (object.name.equals("Ellerines")) companyImage.setImageResource(R.drawable.ellerines);
        if (object.name.equals("Shoprite")) companyImage.setImageResource(R.drawable.shoprite);
        // Return the completed view to render on screen
        return convertView;
    }
}
