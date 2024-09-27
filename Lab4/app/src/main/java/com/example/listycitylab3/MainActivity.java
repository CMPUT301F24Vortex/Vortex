package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements AddBookFragment.AddBookDialogListener, EditBookFragment.EditBookListener{
    private ArrayList<Book> dataList;
    private ListView bookList;
    private BookArrayAdapter bookAdapter;
    private TextView totalReadTextView;
    private TextView totalBooksTextView;

    @Override
    public void addBook(Book book) {
        bookAdapter.add(book);
        bookAdapter.notifyDataSetChanged();
        updateTotalReadCount();
        updateBookStats();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] bookNames = {};
        String[] authors = {};
        String[] genres = {};
        int[] publicationYears = {};
        boolean[] readStatus = {};

        dataList = new ArrayList<>();
        for (int i = 0; i < bookNames.length; i++) {
            dataList.add(new Book(
                    bookNames[i],        // Book name
                    authors[i],          // Author
                    genres[i],           // Genre
                    publicationYears[i], // Publication year
                    readStatus[i]        // Read status
            ));
        }

        bookList = findViewById(R.id.book_list);
        bookAdapter = new BookArrayAdapter(this, dataList);
        bookList.setAdapter(bookAdapter);

        totalBooksTextView = findViewById(R.id.text_total_books);
        totalReadTextView = findViewById(R.id.text_total_read);


        FloatingActionButton fab = findViewById(R.id.button_add_book);
        fab.setOnClickListener(v -> {
            new AddBookFragment().show(getSupportFragmentManager(), "Add Book");
        });

        bookList.setOnItemClickListener((parent, view, position, id) -> {
            Book clickedBook = dataList.get(position);
            EditBookFragment editBookFragment = EditBookFragment.newInstance(clickedBook);
            editBookFragment.show(getSupportFragmentManager(), "Edit Book");
        });
        updateBookStats();
        updateTotalReadCount();


    }
    private void updateBookStats() {
        int totalBooks = dataList.size();  // Total number of books
        int totalRead = 0;

        // Count the number of books marked as "Read"
        for (Book book : dataList) {
            if (book.getRead_status()) {
                totalRead++;
            }
        }

        // Update the TextViews
        totalBooksTextView.setText("Total Books: " + totalBooks);
        totalReadTextView.setText("Total Read: " + totalRead);
    }

    private void updateTotalReadCount() {
        int totalRead = 0;
        for (Book book : dataList) {
            if (book.getRead_status()) {
                totalRead++;
            }
        }
        totalReadTextView.setText("Total Read: " + totalRead);
    }


    @Override
    public void onBookEdited(Book book) {
        bookAdapter.notifyDataSetChanged();
        updateTotalReadCount();
        updateBookStats();
    }

    // Implement the onBookDeleted method to handle book deletion
    @Override
    public void onBookDeleted(Book book) {
        dataList.remove(book);  // Remove the book from the list
        bookAdapter.notifyDataSetChanged();  // Refresh the ListView
        updateTotalReadCount();
        updateBookStats(); // Update the total read count after deletion
    }
}