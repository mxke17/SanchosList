package com.example.sanchoslists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanchoslists.tablecontracts.ListsContract;

public class createNewList extends AppCompatActivity {

    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);

        textInput = (EditText) findViewById(R.id.listNameEditText);
    }

    public void backOfCreateNewList(View view){
        finish();
    }

    public void createList(View view){
        SanchosSQLite dbHelper = new SanchosSQLite(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int duration = Toast.LENGTH_SHORT;
        int text ;

        // Obten el nombre de la lista desde el EditText
        String listName = textInput.getText().toString();

        // Crea un objeto ContentValues para insertar los valores en la base de datos
        ContentValues values = new ContentValues();
        values.put(ListsContract.DictEntry.COLUMN_NAME_NAME, listName);

        // Inserta la nueva lista en la tabla "lists"
        long newRowId = db.insert(ListsContract.DictEntry.TABLE_NAME, null, values);

        hideSoftKeyboard(textInput);

        if (newRowId == -1){
            text = R.string.errorToast;
        } else {
            text = R.string.correctToast;
        }

        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        toast.show();
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}