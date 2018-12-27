package com.example.rebeccachan.tourguideapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass that displays an ArrayList of Fun Activities.
 */

public class FunFragment extends Fragment {

    // Creates keys for ListView item data
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_LOCATION = "KEY_LOCATION";
    public static final String KEY_DETAILS = "KEY_DETAILS";
    public static final String KEY_DRAWABLE = "KEY_DRAWABLE";
    public static final String KEY_DESCRIPTION = "KEY_DESCRIPTION";

    public FunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        // Creates a list of fun things to do
        final ArrayList<Details> details = new ArrayList<Details>();
        details.add(new Details(getContext().getString(R.string.gutter_name),
                getContext().getString(R.string.gutter_detail), R.drawable.gutter,
                getContext().getString(R.string.gutter_description), getContext().getString(R.string.gutter_location)));
        details.add(new Details(getContext().getString(R.string.brooklynboulders_name),
                getContext().getString(R.string.brooklynboulders_detail), R.drawable.brooklynbouldersqueensbridge,
                getContext().getString(R.string.brooklynboulders_description), getContext().getString(R.string.brooklynboulders_location)));
        details.add(new Details(getContext().getString(R.string.cliffs_name),
                getContext().getString(R.string.cliffs_detail), R.drawable.cliffs,
                getContext().getString(R.string.cliffs_description), getContext().getString(R.string.cliffs_location)));

        // Create an {@link InfoAdapter} and populates with data sourced from {@link Info}.
        DetailsAdapter adapter = new DetailsAdapter(getActivity(), details);

        // Finds the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There is a view ID called list in the list_view.xml file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Sets the {@link ListView} to use the {@link InfoAdapter} to display list items for each {@link Info} object.
        listView.setAdapter(adapter);

        // Sets an onItemClickListener(), gets the position of clicked item, and calls an explicit intent.
        // Extras sent to the {@link DetailsActivity} include all {@link Info} object data.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Details item = details.get(position);
                Intent details = new Intent(getContext(), DetailsActivity.class);
                details.putExtra(KEY_NAME, item.getName());
                details.putExtra(KEY_LOCATION, item.getLocation());
                details.putExtra(KEY_DETAILS, item.getDetails());
                details.putExtra(KEY_DESCRIPTION, item.getDescription());
                details.putExtra(KEY_DRAWABLE, item.getImageResourceId());
                startActivity(details);
            }
        });

        return rootView;
    }

}