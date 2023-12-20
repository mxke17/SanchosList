package com.example.sanchoslists.tablecontracts;

import android.provider.BaseColumns;

public final class ListsContract {
    private ListsContract() {}

    public static abstract class DictEntry implements BaseColumns {
        public static final String TABLE_NAME = "TABLE_LISTS";
        public static final String COLUMN_NAME_NAME = "NAME";
    }
}
