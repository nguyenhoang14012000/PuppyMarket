package com.nhuhoa.puppystore;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nhuhoa.puppystore.model.UserInformation;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelperInfor  extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "userInformation.db";
    // Table name: Note.
    private static final String TABLE_INFORMATION = "Information";

    private static final String COLUMN_INFORMATION_ID_PURCHASE ="idPurchase";
    private static final String COLUMN_INFORMATION_ID_USER ="idUser";
    private static final String COLUMN_INFORMATION_USERNAME ="username";
    private static final String COLUMN_INFORMATION_EMAIL ="email";
    private static final String COLUMN_INFORMATION_PHONE ="phone";
    private static final String COLUMN_INFORMATION_ADDRESS ="address";
    private static final String COLUMN_INFORMATION_QUANTITY ="quantity";
    private static final String COLUMN_INFORMATION_ID_PRODUCT ="idProduct";
    private static final String COLUMN_INFORMATION_TOTAL_PRICE ="totalPrice";
    private static final String COLUMN_INFORMATION_PAYMENT_METHOD ="paymentMethod";
    private static final String COLUMN_INFORMATION_PURCHASE_TIME="purchaseTime";

    public MyDatabaseHelperInfor(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "MyDatabaseHelperInfor.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_INFORMATION + "("
                + COLUMN_INFORMATION_ID_PURCHASE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_INFORMATION_ID_USER + " INTEGER," + COLUMN_INFORMATION_USERNAME + " TEXT,"
                + COLUMN_INFORMATION_EMAIL + " TEXT," + COLUMN_INFORMATION_PHONE + " TEXT," +
                COLUMN_INFORMATION_ADDRESS + " TEXT," + COLUMN_INFORMATION_ID_PRODUCT + " INTEGER ," +
                COLUMN_INFORMATION_QUANTITY + " INTEGER," + COLUMN_INFORMATION_TOTAL_PRICE + " REAL," +
                COLUMN_INFORMATION_PAYMENT_METHOD + " TEXT," + COLUMN_INFORMATION_PURCHASE_TIME + " TEXT" + ")";
        // Execute Script.
        sqLiteDatabase.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG, "MyDatabaseHelperInfor.onUpgrade ... ");
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INFORMATION);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
    public void addInformation(UserInformation infor) {
        Log.i(TAG, "MyDatabaseHelperInfor.addInformation ... ");

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INFORMATION_ID_USER, infor.getIdUser());
        values.put(COLUMN_INFORMATION_USERNAME, infor.getUsername());
        values.put(COLUMN_INFORMATION_EMAIL, infor.getEmail());
        values.put(COLUMN_INFORMATION_PHONE, infor.getPhone());
        values.put(COLUMN_INFORMATION_ADDRESS, infor.getAddress());
        values.put(COLUMN_INFORMATION_ID_PRODUCT, infor.getIdProduct());
        values.put(COLUMN_INFORMATION_QUANTITY, infor.getQuantity());
        values.put(COLUMN_INFORMATION_TOTAL_PRICE, infor.getTotalPrice());
        values.put(COLUMN_INFORMATION_PAYMENT_METHOD, infor.getPaymentMethod());
        values.put(COLUMN_INFORMATION_PURCHASE_TIME, infor.getPurchaseTime());


        // Inserting Row
        db.insert(TABLE_INFORMATION, null, values);

        // Closing database connection
        db.close();
    }


    public UserInformation getInfor(int id) {
        Log.i(TAG, "MyDatabaseHelperInfor.getInformation ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INFORMATION, new String[] { COLUMN_INFORMATION_ID_PURCHASE, COLUMN_INFORMATION_ID_USER , COLUMN_INFORMATION_USERNAME ,
                         COLUMN_INFORMATION_EMAIL , COLUMN_INFORMATION_PHONE ,
                        COLUMN_INFORMATION_ADDRESS ,COLUMN_INFORMATION_ID_PRODUCT ,
                        COLUMN_INFORMATION_QUANTITY ,COLUMN_INFORMATION_TOTAL_PRICE ,
                        COLUMN_INFORMATION_PAYMENT_METHOD ,COLUMN_INFORMATION_PURCHASE_TIME }, COLUMN_INFORMATION_ID_USER + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserInformation infor = new UserInformation(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),
                cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),
                Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),
                Double.parseDouble(cursor.getString(8)),cursor.getString(9),cursor.getString(10));
        // return note
        return infor;
    }


    public List<UserInformation> getAllInfor() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<UserInformation> inforList = new ArrayList<UserInformation>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_INFORMATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserInformation infor = new UserInformation();
                infor.setIdPurchase(Integer.parseInt(cursor.getString(0)));
                infor.setIdUser(Integer.parseInt(cursor.getString(1)));
                infor.setUsername(cursor.getString(2));
                infor.setEmail(cursor.getString(3));
                infor.setPhone(cursor.getString(4));
                infor.setAddress(cursor.getString(5));
                infor.setIdProduct(Integer.parseInt(cursor.getString(6)));
                infor.setQuantity(Integer.parseInt(cursor.getString(7)));
                infor.setTotalPrice(Double.parseDouble(cursor.getString(8)));
                infor.setPaymentMethod(cursor.getString(9));
                infor.setPurchaseTime(cursor.getString(10));
                // Adding note to list
                inforList.add(infor);
            } while (cursor.moveToNext());
        }

        // return note list
        return inforList;
    }

    public int getInforsCount() {
        Log.i(TAG, "MyDatabaseHelperInfor.getInforsCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_INFORMATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateInfor(UserInformation infor) {
        Log.i(TAG, "MyDatabaseHelperInfor.updateInfor ... " );

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_INFORMATION_USERNAME, infor.getUsername());
        values.put(COLUMN_INFORMATION_EMAIL, infor.getEmail());
        values.put(COLUMN_INFORMATION_PHONE, infor.getPhone());
        values.put(COLUMN_INFORMATION_ADDRESS, infor.getAddress());
        values.put(COLUMN_INFORMATION_ID_PRODUCT, infor.getIdProduct());
        values.put(COLUMN_INFORMATION_QUANTITY, infor.getQuantity());
        values.put(COLUMN_INFORMATION_TOTAL_PRICE, infor.getTotalPrice());
        values.put(COLUMN_INFORMATION_PAYMENT_METHOD, infor.getPaymentMethod());
        values.put(COLUMN_INFORMATION_PURCHASE_TIME, infor.getPurchaseTime());

        // updating row
        return db.update(TABLE_INFORMATION, values, COLUMN_INFORMATION_ID_PURCHASE + " = ?",
                new String[]{String.valueOf(infor.getIdPurchase())});
    }

    public void deleteInfor(UserInformation infor) {
        Log.i(TAG, "MyDatabaseHelperInfor.deleteInfor ... " );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INFORMATION, COLUMN_INFORMATION_ID_PURCHASE + " = ?",
                new String[] { String.valueOf(infor.getIdPurchase()) });
        db.close();
    }

}

