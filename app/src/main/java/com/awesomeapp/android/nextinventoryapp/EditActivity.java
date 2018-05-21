package com.awesomeapp.android.nextinventoryapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.awesomeapp.android.nextinventoryapp.data.BookContract.BookEntery;
import com.awesomeapp.android.nextinventoryapp.data.BookDBHelper;


public class EditActivity extends AppCompatActivity {

    private EditText titleText;
    private EditText priceText;
    private EditText quantityText;
    private EditText supplierNameText;
    private EditText supplierPhoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titleText = findViewById(R.id.titleText);
        priceText = findViewById(R.id.priceText);
        quantityText = findViewById(R.id.quantityText);
        supplierNameText = findViewById(R.id.supplierNameText);
        supplierPhoneText = findViewById(R.id.supplierPhoneText);
    }


    private void insertBook() {
        // Read from input fields
        String titleBook = titleText.getText().toString().trim();
        int priceBook = Integer.parseInt(priceText.getText().toString());
        int quantityBook = Integer.parseInt(quantityText.getText().toString());
        String supplierNameBook = supplierNameText.getText().toString().trim();
        String supplierPhoneBook = supplierPhoneText.getText().toString().trim();

        // Create database helper
        BookDBHelper mDbHelper = new BookDBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys, and take value from input fields
        ContentValues values = new ContentValues();
        values.put(BookEntery.COLUMN_TITLE, titleBook);
        values.put(BookEntery.COLUMN_QUANTITY, quantityBook);
        values.put(BookEntery.COLUMN_PRICE, priceBook);
        values.put(BookEntery.COLUMN_SUPPLIER_NAME, supplierNameBook);
        values.put(BookEntery.COLUMN_SUPPLIER_PHONE, supplierPhoneBook);


        // Insert a new row for db in the database, returning the ID of that new row.
        long newRowId = db.insert(BookEntery.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving book", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Book saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_item_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                insertBook();
                finish();
                return true;

            case R.id.delete_item:
                // Do nothing
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
