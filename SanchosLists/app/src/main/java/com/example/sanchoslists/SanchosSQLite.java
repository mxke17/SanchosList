package com.example.sanchoslists;
import android.content.Context;
import android.database.sqlite.*;

import com.example.sanchoslists.tablecontracts.ListsContract;

public class SanchosSQLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sanchos_db";
    public static final int DATABASE_VERSION = 2;

    public SanchosSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear tabla de listas
        String createListsTable = "CREATE TABLE " + ListsContract.DictEntry.TABLE_NAME +
                "(" + ListsContract.DictEntry._ID + " INTEGER PRIMARY KEY, " +
                ListsContract.DictEntry.COLUMN_NAME_NAME + " TEXT UNIQUE)";
        sqLiteDatabase.execSQL(createListsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ListsContract.DictEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
