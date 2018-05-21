package com.awesomeapp.android.nextinventoryapp.data;

import android.provider.BaseColumns;

public final class BookContract {

    private BookContract(){}

    public final static class BookEntery implements BaseColumns{
        //Name for book table
        public final static String TABLE_NAME = "books";

        //row ID
        public final static String _ID = BaseColumns._ID;

        // title - string
        public final static String COLUMN_TITLE ="title";

        // price - float
        public final static String COLUMN_PRICE= "price";

        // quantity - int
        public final static String COLUMN_QUANTITY= "quantity";

        //supplier name - string
        public final static String COLUMN_SUPPLIER_NAME= "supplierName";

        //supplier phone - string
        public final static String COLUMN_SUPPLIER_PHONE= "supplierPhone";




    }
}
