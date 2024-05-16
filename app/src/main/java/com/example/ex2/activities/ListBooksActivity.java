package com.example.ex2.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex2.R;
import com.example.ex2.database.DatabaseHelper;
import com.example.ex2.models.Book;
import java.util.List;

public class ListBooksActivity extends AppCompatActivity {

    private ListView listViewBooks;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        listViewBooks = findViewById(R.id.listViewBooks);
        dbHelper = new DatabaseHelper(this);

        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = dbHelper.getAllBooksOrderedByTema();
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, books);
        listViewBooks.setAdapter(adapter);
    }
}
