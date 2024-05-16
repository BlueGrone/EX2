package com.example.ex2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddBook = findViewById(R.id.btnAddBook);
        Button btnUpdateBook = findViewById(R.id.btnUpdateBook);
        Button btnListBooks = findViewById(R.id.btnListBooks);

        btnAddBook.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddBookActivity.class)));
        btnUpdateBook.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UpdateBookActivity.class)));
        btnListBooks.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListBooksActivity.class)));
    }
}
