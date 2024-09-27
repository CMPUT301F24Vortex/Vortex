package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // declare the variables
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addButton;
    Button deleteButton;
    int selectedPosition = -1;  // Variable to store the selected position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // link the xml to java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // link each item in xml to variable
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);


        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        // create dataList first, and change cities array-> list and put this cities list to dataList
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        // now dataList has all cities' name.

        // Adapter = List -> ListView
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // when I choose it, I can only click one item
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Add a new city to the list
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newCity = cityInput.getText().toString().trim();
                if (!newCity.isEmpty()) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    cityInput.setText(""); // Clear the input field
                    Toast.makeText(MainActivity.this, "City Added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle ListView item selection
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;  // Store the selected city's position
                cityList.setItemChecked(position, true);  // Highlight the selected item
            }
        });

        // Delete the selected city
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (selectedPosition >= 0) {
                    String cityToDelete = dataList.get(selectedPosition);
                    dataList.remove(selectedPosition);  // Remove the selected city
                    cityAdapter.notifyDataSetChanged();  // Refresh the list
                    selectedPosition = -1;  // Reset the selection
                    Toast.makeText(MainActivity.this, cityToDelete + " Deleted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
