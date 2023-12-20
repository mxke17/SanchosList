package com.example.sanchoslists;
import android.content.Context;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

public class SanchosSQLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sanchos_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_LISTS = "lists";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LIST_NAME = "listName";

    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_TASK_ID = "taskId";
    public static final String COLUMN_TASK_NAME = "taskName";
    public static final String COLUMN_LIST_ID = "listId";
    String SQL_DELETE_ENTRIES =  "DROP TABLE IF EXISTS " + DATABASE_NAME;

    public SanchosSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear tabla de listas
        String createListsTable = "CREATE TABLE " + TABLE_LISTS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_LIST_NAME + " TEXT UNIQUE)";
        sqLiteDatabase.execSQL(createListsTable);

        // Crear tabla de tareas
        String createTasksTable = "CREATE TABLE " + TABLE_TASKS +
                "(" + COLUMN_TASK_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TASK_NAME + " TEXT, " +
                COLUMN_LIST_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_LIST_ID + ") REFERENCES " + TABLE_LISTS + "(" + COLUMN_ID + "))";
        sqLiteDatabase.execSQL(createTasksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
