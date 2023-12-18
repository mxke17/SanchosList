package com.example.sanchoslists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class createNewList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);
    }

    public void backOfCreateNewList(View view){
        finish();
    }

    public void createList(View view){
        SanchosSQLite dbHelper = new SanchosSQLite(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        // Obten el nombre de la lista desde el EditText
        String listName = ((EditText) findViewById(R.id.listNameEditText)).getText().toString();

        // Verifica que el nombre de la lista no esté vacío
        if (listName.trim().isEmpty()) {
            // Puedes mostrar un mensaje indicando que el nombre no puede estar vacío
            return;
        }

        // Crea un objeto ContentValues para insertar los valores en la base de datos
        ContentValues values = new ContentValues();
        values.put(SanchosSQLite.COLUMN_LIST_NAME, listName);

        // Inserta la nueva lista en la tabla "lists"
        long newRowId = db.insert(SanchosSQLite.TABLE_LISTS, null, values);
    }
}