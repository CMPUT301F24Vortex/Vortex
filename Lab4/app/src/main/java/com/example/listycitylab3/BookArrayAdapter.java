package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookArrayAdapter extends ArrayAdapter<Book> {

    public BookArrayAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);  // Use "books" instead of "cities"
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Reuse the convertView if possible, else inflate a new view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content, parent, false);
        }

        // Get the book for this position
        Book book = getItem(position);

        // Check if the book is null (just a safety check)
        if (book != null) {
            // Find the TextViews in the layout and populate them with book data
            TextView bookName = convertView.findViewById(R.id.book_text);
            TextView authorName = convertView.findViewById(R.id.author_text);
            TextView genreText = convertView.findViewById(R.id.genre_text);
            TextView publicationYearText = convertView.findViewById(R.id.publication_year_text);
            TextView statusText = convertView.findViewById(R.id.status_read_text);

            // Set book data to the views
            bookName.setText(book.getName());       // Set book name
            authorName.setText(book.getAuthor());   // Set author name
            genreText.setText(book.getGenre());     // Set genre
            publicationYearText.setText(String.valueOf(book.getPublication_year()));  // Set publication year
            statusText.setText(book.getRead_status() ? "Read" : "Unread");  // Set read/unread status
        }

        return convertView; // Return the updated view
    }
}


