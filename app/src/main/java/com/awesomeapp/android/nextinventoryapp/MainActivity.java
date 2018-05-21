package com.awesomeapp.android.nextinventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.awesomeapp.android.nextinventoryapp.data.BookContract.BookEntery;
import com.awesomeapp.android.nextinventoryapp.data.BookDBHelper;

public class MainActivity extends AppCompatActivity {

    private BookDBHelper dbHelper;
    private TextView displayData;
    private SQLiteDatabase dbWritable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayData = findViewById(R.id.displayData);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new BookDBHelper(this);
        // Gets the database in write mode
        dbWritable = dbHelper.getWritableDatabase();

    }

    private void displayDatabaseInfo() {
        SQLiteDatabase dbReadable = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        String[] projection = {
                BookEntery._ID,
                BookEntery.COLUMN_TITLE,
                BookEntery.COLUMN_PRICE,
                BookEntery.COLUMN_QUANTITY,
                BookEntery.COLUMN_SUPPLIER_NAME,
                BookEntery.COLUMN_SUPPLIER_PHONE};

        // Perform a query on the book table
        Cursor cursor = dbReadable.query(
                BookEntery.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {

            displayData.setText(getString(R.string.database_message, cursor.getCount()+""));
            displayData.append(BookEntery._ID + " - " +
                    BookEntery.COLUMN_TITLE + " - " +
                    BookEntery.COLUMN_PRICE + " - " +
                    BookEntery.COLUMN_QUANTITY + " - " +
                    BookEntery.COLUMN_SUPPLIER_NAME + " - " +
                    BookEntery.COLUMN_SUPPLIER_PHONE + "\n");

            // index of each column
            int idColumnIndex = cursor.getColumnIndex(BookEntery._ID);
            int titleColumnIndex = cursor.getColumnIndex(BookEntery.COLUMN_TITLE);
            int priceColumnIndex = cursor.getColumnIndex(BookEntery.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(BookEntery.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookEntery.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(BookEntery.COLUMN_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentTitle = cursor.getString(titleColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayData.append(("\n" + currentID + " - " +
                        currentTitle + " - " +
                        currentPrice + " - " +
                        currentQuantity + " - " +
                        currentSupplierName + " - " +
                        currentSupplierPhone));
            }
        } finally {

            cursor.close();
        }
    }

    private void insertBook() {

        //dummy data
        ContentValues values = new ContentValues();
        values.put(BookEntery.COLUMN_TITLE, "Some title");
        values.put(BookEntery.COLUMN_QUANTITY, 10);
        values.put(BookEntery.COLUMN_PRICE, 12);
        values.put(BookEntery.COLUMN_SUPPLIER_NAME, "Supplier name");
        values.put(BookEntery.COLUMN_SUPPLIER_PHONE, "555 555 555");

        dbWritable.insert(BookEntery.TABLE_NAME, null, values);
    }

    private void deleteAllBooks(){
        dbWritable.delete(BookEntery.TABLE_NAME, null, null);
    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_data:
                insertBook();
                displayDatabaseInfo();
                return true;
            case R.id.delete_all_items:
                deleteAllBooks();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
