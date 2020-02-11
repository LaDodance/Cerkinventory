package com.example.android.cerkinventory.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.android.cerkinventory.Application;
import com.example.android.cerkinventory.entity.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl {

    public static void insert(ArrayList<Product> entities) {
        SQLiteDatabase db = Application.getInstance().getGeneralDbHelper().getWritableDatabase();
        List<ContentValues> contentValuesList = new ArrayList<>();
        for (Product user : entities) {
            contentValuesList.add(modelToCV(user));
        }

        db.beginTransaction();
        for (ContentValues values : contentValuesList) {
            if (values.getAsInteger(Columns.COLUMN_ID) != null && values.getAsInteger(Columns.COLUMN_ID) != 0) {
                Log.e("TAG", "update");
                db.update(Columns.TABLE_NAME, values, Columns.COLUMN_ID + "=?", new String[]{String.valueOf(values.getAsInteger(Columns.COLUMN_ID))});
            } else {
                Log.e("TAG", "INSERT");
                db.insert(Columns.TABLE_NAME, null, values);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public static ArrayList<Product> getAllProduct() {
        ArrayList<Product> results = new ArrayList<>();

        SQLiteDatabase db = Application.getInstance().getGeneralDbHelper().getWritableDatabase();
        String sortOrder = Columns.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(Columns.TABLE_NAME, null, null, null, null, null, sortOrder);
        while (cursor.moveToNext()) {
            results.add(cursorToModel(cursor));
        }

        cursor.close();
        return results;
    }



    public static int getTotalProduct() {
        SQLiteDatabase db = Application.getInstance().getGeneralDbHelper().getWritableDatabase();
        String sortOrder = Columns.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(Columns.TABLE_NAME, new String[]{Columns.COLUMN_ID}, null, null, null, null, sortOrder);
        int count = cursor.getCount();

        cursor.close();
        return count;
    }



    public static Product getProductByName(String name) {
        Product result = null;

        SQLiteDatabase db = Application.getInstance().getGeneralDbHelper().getWritableDatabase();
        String sortOrder = Columns.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(Columns.TABLE_NAME, null, Columns.COLUMN_NAME + "=?", new String[]{name}, null, null, sortOrder);
        if (cursor.moveToFirst()) {
            result = cursorToModel(cursor);
        }

        cursor.close();
        return result;
    }

    public static void update(Product product) {
        ContentValues cv = modelToCV(product);

        SQLiteDatabase db = Application.getInstance().getGeneralDbHelper().getWritableDatabase();
        db.beginTransaction();
        db.update(Columns.TABLE_NAME, cv, Columns.COLUMN_ID + "=?", new String[]{String.valueOf(product.getId())});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private static ContentValues modelToCV(Product entity) {
        ContentValues contentValues = new ContentValues();

        // ne pas mettre l'id si insert nouvelle ligne car auto incrémenter -> si tu le met et qu'il est égal à zéro il va essayer d'updater une ligne qui n'existe pas
        if (entity.getId() != 0) {
            contentValues.put(Columns.COLUMN_ID, entity.getId());
        }

        contentValues.put(Columns.COLUMN_NAME, entity.getNameProduct());
        contentValues.put(Columns.COLUMN_PRICE, entity.getPrice());
        contentValues.put(Columns.COLUMN_QUANTITY, entity.getQuantity());

        return contentValues;
    }

    public static Product cursorToModel(Cursor cursor) {
        Product entity = new Product();
        entity.setId(cursor.getInt(cursor.getColumnIndex(Columns.COLUMN_ID)));
        entity.setNameProduct(cursor.getString(cursor.getColumnIndex(Columns.COLUMN_NAME)));
        entity.setPrice(cursor.getFloat(cursor.getColumnIndex(Columns.COLUMN_PRICE)));
        entity.setQuantity(cursor.getInt(cursor.getColumnIndex(Columns.COLUMN_QUANTITY)));

        return entity;
    }

    public static class Columns implements BaseColumns {
        public static final String TABLE_NAME = "product";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "nameProduct";
        private static final String COLUMN_PRICE = "price";
        private static final String COLUMN_QUANTITY = "quantity";

    }
}
