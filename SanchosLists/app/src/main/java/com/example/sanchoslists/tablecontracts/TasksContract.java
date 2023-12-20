package com.example.sanchoslists.tablecontracts;

import android.provider.BaseColumns;

public final class TasksContract {
    private TasksContract() {}

    public static abstract class DictEntry implements BaseColumns {
        public static final String TABLE_NAME = "TABLE_TASKS";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_LIST_ID_NAME = "LIST_ID";
    }
}
