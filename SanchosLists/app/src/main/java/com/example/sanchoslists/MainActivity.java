package com.example.sanchoslists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.sanchoslists.tablecontracts.ListsContract;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Atributos para manejar la BD
    private SanchosSQLite dbHelper;

    private StringListAdapter adapter;
    private SQLiteDatabase db;
    private RecyclerView listLists;
    private List<String> listNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Desde main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new SanchosSQLite(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        //initLists();

        List<String> listNames = new ArrayList<>();

        adapter = new StringListAdapter(listNames);

        listLists = (RecyclerView) findViewById(R.id.listNamesList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        listLists.setLayoutManager(layoutManager);
        listLists.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        
        Cursor cursor = db.query(ListsContract.DictEntry.TABLE_NAME, null, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(ListsContract.DictEntry.COLUMN_NAME_NAME);
            if (columnIndex > 0) {
                do {
                    listNames.add(cursor.getString(columnIndex));
                } while (cursor.moveToNext());
            }
        }

        //listNames tiene los nombres de todas las lista
        adapter.notifyDataSetChanged();
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