package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddBookFragment extends DialogFragment {
    interface AddBookDialogListener {
        void addBook(Book book);
    }
    private AddBookDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddBookDialogListener) {
            listener = (AddBookDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddBookDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_book, null);
        EditText editBookName = view.findViewById(R.id.edit_text_book_title);
        EditText editAuthorName = view.findViewById(R.id.edit_text_author_name);
        EditText editGenre = view.findViewById(R.id.edit_text_genre);
        EditText editPublicationYear = view.findViewById(R.id.edit_text_publication_year);
        CheckBox readStatusCheckbox = view.findViewById(R.id.edit_text_read_status);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add a book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String bookName = editBookName.getText().toString();
                    String authorName = editAuthorName.getText().toString();
                    String genre = editGenre.getText().toString();  // Assuming editGenre is an EditText for genre
                    int publicationYear = Integer.parseInt(editPublicationYear.getText().toString());  // Assuming you get the year from an EditText
                    boolean isRead = readStatusCheckbox.isChecked();  // Assuming you have a checkbox for read status

                    listener.addBook(new Book(bookName, authorName, genre, publicationYear, isRead));
//                    Log.d("bookname", bookName);
//                    Log.d("authorName", authorName);
//                    Log.d("genre", genre);
//                    Log.d("publication year", String.valueOf(publicationYear));
//                    Log.d("isRead", String.valueOf(isRead));


                })
                .create();
    }


}