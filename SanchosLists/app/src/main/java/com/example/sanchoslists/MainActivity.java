package com.example.sanchoslists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Atributos para manejar la BD
    private SanchosSQLite dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new SanchosSQLite(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        initLists();
    }

    private void initLists(){
        // Adicion de valores a la bd
        ContentValues values = new ContentValues();
        values.put(SanchosSQLite.COLUMN_LIST_NAME, "Compras");
        db.insert(SanchosSQLite.DATABASE_NAME, null, values);

        values.put(SanchosSQLite.COLUMN_LIST_NAME, "Compras2");
        db.insert(SanchosSQLite.DATABASE_NAME, null, values);
    }

    public void createNewList(View view){
        Intent i = new Intent(this, createNewList.class );
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}