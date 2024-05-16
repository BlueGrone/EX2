package com.example.ex2.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ex2.R;
import com.example.ex2.database.DatabaseHelper;
import com.example.ex2.models.Book;

import java.util.ArrayList;
import java.util.List;

public class UpdateBookActivity extends AppCompatActivity {

    private EditText editTextLibroId, editTextTitulo, editTextAutor, editTextCosto, editTextStock, editTextEdicion;
    private Spinner spinnerTema;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        dbHelper = new DatabaseHelper(this);
        editTextLibroId = findViewById(R.id.editTextLibroId);
        spinnerTema = findViewById(R.id.spinnerTema);
        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        editTextCosto = findViewById(R.id.editTextCosto);
        editTextStock = findViewById(R.id.editTextStock);
        editTextEdicion = findViewById(R.id.editTextEdicion);
        Button buttonActualizar = findViewById(R.id.buttonActualizarLibro);
        Button buttonEliminar = findViewById(R.id.buttonEliminarLibro);

        loadTemas();

        buttonActualizar.setOnClickListener(v -> updateBook());
        buttonEliminar.setOnClickListener(v -> confirmDeletion());
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

    private void updateBook() {
        String libroId = editTextLibroId.getText().toString();
        String titulo = editTextTitulo.getText().toString();
        String autor = editTextAutor.getText().toString();
        double costo = Double.parseDouble(editTextCosto.getText().toString());
        int stock = Integer.parseInt(editTextStock.getText().toString());
        String edicion = editTextEdicion.getText().toString();
        int temaId = spinnerTema.getSelectedItemPosition() + 1;

        Book book = new Book(libroId, titulo, autor, costo, stock, edicion, temaId);
        dbHelper.updateLibro(book);
        Toast.makeText(this, "Libro actualizado con éxito", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void confirmDeletion() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Seguro de Eliminar este libro?")
                .setPositiveButton("Aceptar", (dialog, which) -> deleteBook())
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void deleteBook() {
        String libroId = editTextLibroId.getText().toString();
        dbHelper.deleteLibro(libroId);
        Toast.makeText(this, "Libro eliminado con éxito", Toast.LENGTH_SHORT).show();
        finish();
    }
}
