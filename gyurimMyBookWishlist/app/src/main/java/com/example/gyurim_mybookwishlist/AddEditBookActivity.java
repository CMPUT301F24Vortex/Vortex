package com.example.gyurim_mybookwishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AddEditBookActivity extends Activity {

    private EditText titleInput;
    private EditText authorInput;
    private Spinner genreSpinner;
    private EditText yearInput;
    private RadioButton readStatus;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);

        // Initialize the input fields
        titleInput = findViewById(R.id.titleInput);
        authorInput = findViewById(R.id.authorInput);
        genreSpinner = findViewById(R.id.genreSpinner);
        yearInput = findViewById(R.id.yearInput);
        readStatus = findViewById(R.id.readRadioButton);
        saveButton = findViewById(R.id.saveButton);

        // Save button listener to pass the book data back to MainActivity
        saveButton.setOnClickListener(v -> saveBook());
    }

    private void saveBook() {
        // Collect input data, validate, and return to MainActivity
        String title = titleInput.getText().toString();
        String author = authorInput.getText().toString();
        String genre = genreSpinner.getSelectedItem().toString();
        int year = Integer.parseInt(yearInput.getText().toString());
        boolean isRead = readStatus.isChecked();

        // Create a new Book object
        Book book = new Book(title, author, genre, year, isRead);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("book", book);  // Pass the book back to MainActivity
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}



