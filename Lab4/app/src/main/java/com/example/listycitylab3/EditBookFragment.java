package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditBookFragment extends DialogFragment {
    private Book book;

    // Factory method to create a new instance of EditBookFragment with the Book data
    public static EditBookFragment newInstance(Book book) {
        EditBookFragment fragment = new EditBookFragment();
        Bundle args = new Bundle();
        args.putSerializable("book", book);
        fragment.setArguments(args);  // Attach the bundle to the fragment
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            book = (Book) getArguments().getSerializable("book");  // Retrieve the book from arguments
        }

        // Inflate the fragment layout
        View view = getLayoutInflater().inflate(R.layout.fragment_edit_book, null);

        // Get references to the EditText and CheckBox views
        EditText editBookName = view.findViewById(R.id.edit_text_book_title);
        EditText editAuthorName = view.findViewById(R.id.edit_text_author_name);
        EditText editGenre = view.findViewById(R.id.edit_text_genre);
        EditText editPublicationYear = view.findViewById(R.id.edit_text_publication_year);
        CheckBox editReadStatus = view.findViewById(R.id.edit_text_read_status);
        Button deleteButton = view.findViewById(R.id.button_delete_book);  // Reference to the delete button

        // Set the current book details in the EditTexts
        editBookName.setText(book.getName());
        editAuthorName.setText(book.getAuthor());
        editGenre.setText(book.getGenre());
        editPublicationYear.setText(String.valueOf(book.getPublication_year()));
        editReadStatus.setChecked(book.getRead_status());

        // Build the dialog with the edit and delete functionality
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle("Edit Book")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    // Save changes to the book
                    String newBookName = editBookName.getText().toString();
                    String newAuthorName = editAuthorName.getText().toString();
                    String newGenre = editGenre.getText().toString();
                    int newPublicationYear = Integer.parseInt(editPublicationYear.getText().toString());
                    boolean newReadStatus = editReadStatus.isChecked();

                    // Update the book object with new values
                    book.setName(newBookName);
                    book.setAuthor(newAuthorName);
                    book.setGenre(newGenre);
                    book.setPublication_year(newPublicationYear);
                    book.setRead_status(newReadStatus);

                    // Notify the activity of the book edit
                    if (getActivity() instanceof EditBookListener) {
                        ((EditBookListener) getActivity()).onBookEdited(book);
                    }

                });

        // Handle the delete button click
        deleteButton.setOnClickListener(v -> {
            // Notify the activity of the book deletion
            if (getActivity() instanceof EditBookListener) {
                ((EditBookListener) getActivity()).onBookDeleted(book);  // Notify activity that the book should be deleted
                dismiss();  // Close the dialog after deletion
            }
        });

        return builder.create();
    }

    // Interface to communicate with the activity for editing or deleting a book
    public interface EditBookListener {
        void onBookEdited(Book book);  // Called when the book is edited
        void onBookDeleted(Book book);  // Called when the book is deleted
    }
}


