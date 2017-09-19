package com.anbsoft.myapplication.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anbsoft.myapplication.Constants.Constants;
import com.anbsoft.myapplication.Models.Product;
import com.anbsoft.myapplication.Models.User;

import java.util.ArrayList;

/**
 * Created by Akash on 19-Sep-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "anbSoft";

    private static final String TABLE_USERS = "users";

    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_PH_NO = "phone_number";

    private static final String TABLE_PRODUCTS = "products";

    private static final String KEY_PROD_ID = "id";
    private static final String KEY_NAME = "prod_name";
    private static final String KEY_DESC = "desc";
    private static final String KEY_PRICE = "price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PH_NO + " TEXT,"
                + KEY_PASS + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_PROD_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_PRICE + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);

        addProducts(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PH_NO, user.getPhoneNumber());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASS, user.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, null, KEY_USERNAME + "=? AND " + KEY_PASS + "=?",
                new String[]{username, password}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.isAfterLast()) {
            return null;
        } else {
            User user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

            return user;
        }
    }

    public boolean checkUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, null, KEY_USERNAME + "=?",
                new String[]{username}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToNext();
            if (cursor.isAfterLast()) {
                return false;
            } else {
                return true;
            }
        } else
            return false;
    }

    public void addProducts(SQLiteDatabase sqLiteDatabase) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Product 1");
        values.put(KEY_DESC, Constants.descStr);
        values.put(KEY_PRICE, "10");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values);


        ContentValues values2 = new ContentValues();
        values2.put(KEY_NAME, "Product 2");
        values2.put(KEY_DESC, Constants.descStr);
        values2.put(KEY_PRICE, "20");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(KEY_NAME, "Product 3");
        values3.put(KEY_DESC, Constants.descStr);
        values3.put(KEY_PRICE, "25");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values3);

        ContentValues values4 = new ContentValues();
        values4.put(KEY_NAME, "Product 4");
        values4.put(KEY_DESC, Constants.descStr);
        values4.put(KEY_PRICE, "30");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values4);

        ContentValues values5 = new ContentValues();
        values5.put(KEY_NAME, "Product 5");
        values5.put(KEY_DESC, Constants.descStr);
        values5.put(KEY_PRICE, "40");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values5);

        ContentValues values6 = new ContentValues();
        values6.put(KEY_NAME, "Product 6");
        values6.put(KEY_DESC, Constants.descStr);
        values6.put(KEY_PRICE, "55");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values6);

        ContentValues values7 = new ContentValues();
        values7.put(KEY_NAME, "Product 7");
        values7.put(KEY_DESC, Constants.descStr);
        values7.put(KEY_PRICE, "70");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values7);

        ContentValues values8 = new ContentValues();
        values8.put(KEY_NAME, "Product 8");
        values8.put(KEY_DESC, Constants.descStr);
        values8.put(KEY_PRICE, "80");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values8);

        ContentValues values9 = new ContentValues();
        values9.put(KEY_NAME, "Product 9");
        values9.put(KEY_DESC, Constants.descStr);
        values9.put(KEY_PRICE, "100");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values9);

        ContentValues values10 = new ContentValues();
        values10.put(KEY_NAME, "Product 10");
        values10.put(KEY_DESC, Constants.descStr);
        values10.put(KEY_PRICE, "150");

        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values10);
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productArrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_PRODUCTS, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                productArrayList.add(new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), Double.valueOf(cursor.getString(3))));
                cursor.moveToNext();
            }
        }

        return productArrayList;
    }
}
