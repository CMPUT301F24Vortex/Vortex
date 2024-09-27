package com.example.gyurim_mybookwishlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gyurim_mybookwishlist.BookAdapter;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView bookListView;
    private BookAdapter bookAdapter; // Use BookAdapter here
    private ArrayList<Book> bookList = new ArrayList<>();
    private static final int ADD_BOOK_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ListView
        bookListView = findViewById(R.id.bookListView);

        // Set up the adapter for ListView
        bookAdapter = new BookAdapter(this, bookList);  // Use BookAdapter here
        bookListView.setAdapter(bookAdapter);

        // Add button listener to add a new book
        findViewById(R.id.addBookButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditBookActivity.class);
            startActivityForResult(intent, ADD_BOOK_REQUEST_CODE);
        });

        // Update the book count and read status
        updateBookStats();
    }

    private void updateBookStats() {
        // Find the TextView where you will display the stats
        TextView statsTextView = findViewById(R.id.bookStatsTextView);

        // Calculate the total number of books
        int totalBooks = bookList.size();

        // Count how many books are marked as "Read"
        int readBooks = 0;
        for (Book book : bookList) {
            if (book.isRead()) {
                readBooks++;
            }
        }

        // Update the TextView with the new stats
        String statsText = "Total books: " + totalBooks + ", Read: " + readBooks;
        statsTextView.setText(statsText);
    }


    // Handle result when a new book is added or an existing one is edited
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Retrieve the Book object passed from AddEditBookActivity
            Book newBook = (Book) data.getSerializableExtra("book");
            if (newBook != null) {
                bookList.add(newBook);  // Add the new book to the list
                bookAdapter.notifyDataSetChanged();  // Refresh the ListView
                updateBookStats();  // Update any stats related to the book count
            }
        }
    }





}
