package com.awesomeapp.android.nextinventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BookContract {

    public static final String CONTENT_AUTHORITY = "com.awesomeapp.android.nextinventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_BOOKS = "books";

    private BookContract() {
    }

    public static final class BookEntry implements BaseColumns {

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        //Name for book table
        public final static String TABLE_NAME = "books";

        //row ID
        public final static String _ID = BaseColumns._ID;

        // title - string
        public final static String COLUMN_TITLE = "title";

        // price - number
        public final static String COLUMN_PRICE = "price";

        // quantity - int
        public final static String COLUMN_QUANTITY = "quantity";

        //supplier name - string
        public final static String COLUMN_SUPPLIER_NAME = "supplierName";

        //supplier phone - string
        public final static String COLUMN_SUPPLIER_PHONE = "supplierPhone";
    }
}
