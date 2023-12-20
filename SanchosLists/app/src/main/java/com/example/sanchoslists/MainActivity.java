package com.example.sanchoslists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sanchoslists.tablecontracts.ListsContract;

public class MainActivity extends AppCompatActivity {

    // Atributos para manejar la BD
    private SanchosSQLite dbHelper;
    private SQLiteDatabase db;
    private RecyclerView listLists;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Desde main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new SanchosSQLite(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        //listLists = (RecyclerView) findViewById(R.id.taskListRv);
        textView = (TextView) findViewById(R.id.textView2);
        initLists();
    }

    private void initLists(){
        // Adicion de valores a la bd
        ContentValues values = new ContentValues();
        values.put(ListsContract.DictEntry.COLUMN_NAME_NAME, "Compras");
        db.insert(ListsContract.DictEntry.TABLE_NAME, null, values);

        values.put(ListsContract.DictEntry.COLUMN_NAME_NAME, "Compras2");
        db.insert(ListsContract.DictEntry.TABLE_NAME, null, values);
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