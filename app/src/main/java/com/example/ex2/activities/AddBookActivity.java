package com.example.ex2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ex2.R;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextTitulo, editTextAutor, editTextCosto, editTextStock, editTextEdicion;
    private Spinner spinnerTema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book); // Make sure this is the correct layout file

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextCosto = findViewById(R.id.editTextCosto);
        editTextStock = findViewById(R.id.editTextStock);
        editTextEdicion = findViewById(R.id.editTextEdicion);
        spinnerTema = findViewById(R.id.spinnerTema);
        Button buttonGuardar = findViewById(R.id.buttonGuardarLibro);

        loadTemas();  // Load themes directly into the spinner

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBook();
            }
        });
    }

    private void loadTemas() {
        List<String> temas = new ArrayList<>();
        temas.add("BIOLOGIA");
        temas.add("ESTADISTICA");
        temas.add("FISICA");
        temas.add("HISTORIA");
        temas.add("INFORMATICA");
        temas.add("LITERATURA");
        temas.add("ARTE");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, temas);
        spinnerTema.setAdapter(adapter);
    }

    private void saveBook() {
        String titulo = editTextTitulo.getText().toString();
        String autor = editTextAutor.getText().toString();
        double costo = Double.parseDouble(editTextCosto.getText().toString());
        int stock = Integer.parseInt(editTextStock.getText().toString());
        String edicion = editTextEdicion.getText().toString();
        int temaId = spinnerTema.getSelectedItemPosition() + 1;  // This gets the position of the selected item in the spinner

        // Add your logic to save the book using the DatabaseHelper
        Toast.makeText(this, "Libro guardado con éxito", Toast.LENGTH_SHORT).show();
        finish();
    }
}
