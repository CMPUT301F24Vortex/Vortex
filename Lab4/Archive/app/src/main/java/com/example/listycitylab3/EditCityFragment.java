package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {
    private City city;

    public static EditCityFragment newInstance(City city) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putSerializable("city", city);  // Put the city object into the bundle
        fragment.setArguments(args);  // Attach the bundle to the fragment
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Get the City object from the arguments (Bundle)
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable("city");
        }

        // Inflate the layout for editing the city (e.g., EditText fields for name and province)
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_name);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_name);

        // Pre-fill the EditTexts with the current city name and province
        editCityName.setText(city.getName());
        editProvinceName.setText(city.getProvince());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    // Update the City object with the new details
                    String newCityName = editCityName.getText().toString();
                    String newProvinceName = editProvinceName.getText().toString();
                    city.setName(newCityName);
                    city.setProvince(newProvinceName);

                    // You can call a listener to pass the updated city back to the activity here
                    if (getActivity() instanceof EditCityListener) {
                        ((EditCityListener) getActivity()).onCityEdited(city);
                    }
                })
                .create();
    }

    public interface EditCityListener {
        void onCityEdited(City city);
    }
}

