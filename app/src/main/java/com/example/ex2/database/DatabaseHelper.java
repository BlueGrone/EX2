package com.example.ex2.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.ex2.models.Book;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BDLibreria";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TEMAS = "TEMAS";
    private static final String TABLE_LIBROS = "LIBROS";

    private static final String CREATE_TABLE_TEMAS =
            "CREATE TABLE " + TABLE_TEMAS + " (IdTema INTEGER PRIMARY KEY, Tema TEXT NOT NULL, Nro_Libros INTEGER NOT NULL DEFAULT 0);";

    private static final String CREATE_TABLE_LIBROS =
            "CREATE TABLE " + TABLE_LIBROS + " (LibroId TEXT PRIMARY KEY, Titulo TEXT NOT NULL, Autor TEXT NOT NULL, " +
                    "Costo REAL NOT NULL, Stock INTEGER NOT NULL, Edicion TEXT NOT NULL, IdTema INTEGER, " +
                    "FOREIGN KEY(IdTema) REFERENCES " + TABLE_TEMAS + "(IdTema));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEMAS);
        db.execSQL(CREATE_TABLE_LIBROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBROS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMAS);
        onCreate(db);
    }
    public List<Book> getAllBooksOrderedByTema() {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LIBROS + " ORDER BY IdTema ASC, Titulo ASC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Book book = new Book(
                        cursor.getString(cursor.getColumnIndex("LibroId")),
                        cursor.getString(cursor.getColumnIndex("Titulo")),
                        cursor.getString(cursor.getColumnIndex("Autor")),
                        cursor.getDouble(cursor.getColumnIndex("Costo")),
                        cursor.getInt(cursor.getColumnIndex("Stock")),
                        cursor.getString(cursor.getColumnIndex("Edicion")),
                        cursor.getInt(cursor.getColumnIndex("IdTema"))
                );
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return books;
    }

    @SuppressLint("Range")
    public List<String> getTemas() {
        List<String> temas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TEMAS", new String[]{"Tema"}, null, null, null, null, "Tema ASC");
        if (cursor.moveToFirst()) {
            do {
                temas.add(cursor.getString(cursor.getColumnIndex("Tema")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return temas;
    }

    public void addLibro(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Titulo", book.getTitulo());
        values.put("Autor", book.getAutor());
        values.put("Costo", book.getCosto());
        values.put("Stock", book.getStock());
        values.put("Edicion", book.getEdicion());
        values.put("IdTema", book.getTemaId());

        db.insert("LIBROS", null, values);
        db.close();
    }


    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LIBROS, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("LibroId");
            int titleIndex = cursor.getColumnIndex("Titulo");
            int authorIndex = cursor.getColumnIndex("Autor");
            int costIndex = cursor.getColumnIndex("Costo");
            int stockIndex = cursor.getColumnIndex("Stock");
            int editionIndex = cursor.getColumnIndex("Edicion");
            int temaIdIndex = cursor.getColumnIndex("IdTema");

            while (!cursor.isAfterLast()) {
                if (idIndex != -1 && titleIndex != -1 && authorIndex != -1 && costIndex != -1 &&
                        stockIndex != -1 && editionIndex != -1 && temaIdIndex != -1) {
                    Book book = new Book(
                            cursor.getString(idIndex),
                            cursor.getString(titleIndex),
                            cursor.getString(authorIndex),
                            cursor.getDouble(costIndex),
                            cursor.getInt(stockIndex),
                            cursor.getString(editionIndex),
                            cursor.getInt(temaIdIndex)
                    );
                    books.add(book);
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        return books;
    }

    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LibroId", book.getLibroId());
        values.put("Titulo", book.getTitulo());
        values.put("Autor", book.getAutor());
        values.put("Costo", book.getCosto());
        values.put("Stock", book.getStock());
        values.put("Edicion", book.getEdicion());
        values.put("IdTema", book.getTemaId());
        db.insert(TABLE_LIBROS, null, values);
        db.close();
    }

    public void updateLibro(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Titulo", book.getTitulo());
        values.put("Autor", book.getAutor());
        values.put("Costo", book.getCosto());
        values.put("Stock", book.getStock());
        values.put("Edicion", book.getEdicion());
        values.put("IdTema", book.getTemaId());
        db.update(TABLE_LIBROS, values, "LibroId = ?", new String[] { book.getLibroId() });
        db.close();
    }

    public void deleteLibro(String libroId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIBROS, "LibroId = ?", new String[]{libroId});
        db.close();
    }

    private void updateTemaLibroCount(int temaId, int delta) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_TEMAS + " SET Nro_Libros = Nro_Libros + (" + delta + ") WHERE IdTema = " + temaId);
        db.close();
    }
}

