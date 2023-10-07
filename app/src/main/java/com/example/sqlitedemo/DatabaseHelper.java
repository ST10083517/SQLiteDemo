package com.example.sqlitedemo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_AGE = "AGE";
    public static final String COLUMN_ACTIVESTATUS = "ACTIVESTATUS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_AGE + " INTEGER , " + COLUMN_ACTIVESTATUS + " BOOL)";
                db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Customer customerModel){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getsName());
        cv.put(COLUMN_AGE, customerModel.getiAge());
        cv.put(COLUMN_ACTIVESTATUS, customerModel.isbIsActive());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);


        if (insert == -1){

            return false;
        } else {

            return true;
        }

    }

    public List<Customer> getAllDatabaseRecords(){


        List<Customer> customerList = new ArrayList<>();
        String getAllRecordsStatement = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        int iCustomerID , iAge;
        String sCustomerName;
        Boolean bIsActive ;
        Customer customerModel;

        Cursor cursor = db.rawQuery(getAllRecordsStatement  , null);

        if(cursor.moveToFirst()) {
/* Loop through the cursor and obtain rows of data which will be stored in a new Customer object
for each row of data and store them in the customerList defined earlier.*/
            do {
                iCustomerID = cursor.getInt(0);
                sCustomerName = cursor.getString(1);
                iAge = cursor.getInt(2);
                bIsActive = cursor.getInt(3) == 1 ? true : false;

                customerModel = new Customer(iCustomerID, sCustomerName, iAge, bIsActive);
                customerList.add(customerModel);
            } while (cursor.moveToNext());
        }
            else {

                // Not records gets added to the List. Failure
                cursor.close();
                db.close();

            }

        return customerList;
    }

    public boolean deleteCustomerRecord(Customer deleteRecord){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteCustomerRecord = "DELETE FROM " + CUSTOMER_TABLE +" WHERE " + COLUMN_ID
                + " = " + deleteRecord.getiID();
        Cursor cursor = db.rawQuery(deleteCustomerRecord  , null);

        if(cursor.moveToFirst()) {
            return true;
        }
            else{

            return false;
        }
    }
}
