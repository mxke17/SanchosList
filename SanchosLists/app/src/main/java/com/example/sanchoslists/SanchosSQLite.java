package com.example.sanchoslists;
import android.content.Context;
import android.database.sqlite.*;

import com.example.sanchoslists.tablecontracts.ListsContract;
import com.example.sanchoslists.tablecontracts.TasksContract;

import java.util.List;

public class SanchosSQLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sanchos_db";
    public static final int DATABASE_VERSION = 2;

    public SanchosSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Desde db");
        // Crear tabla de listas
        String createListsTable = "CREATE TABLE " + ListsContract.DictEntry.TABLE_NAME +
                "(" + ListsContract.DictEntry._ID + " INTEGER PRIMARY KEY, " +
                ListsContract.DictEntry.COLUMN_NAME_NAME + " TEXT UNIQUE)";
        sqLiteDatabase.execSQL(createListsTable);

        // Crear tabla de tareas
        String createTasksTable = "CREATE TABLE " + TasksContract.DictEntry.TABLE_NAME +
                "(" + TasksContract.DictEntry._ID + " INTEGER PRIMARY KEY, " +
                TasksContract.DictEntry.COLUMN_NAME_NAME + " TEXT, " +
                TasksContract.DictEntry.COLUMN_LIST_ID_NAME + " INTEGER, " +
                "FOREIGN KEY(" + TasksContract.DictEntry.COLUMN_LIST_ID_NAME + ") REFERENCES " + ListsContract.DictEntry.TABLE_NAME + "(" + ListsContract.DictEntry._ID + "))";
        sqLiteDatabase.execSQL(createTasksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ListsContract.DictEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TasksContract.DictEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
