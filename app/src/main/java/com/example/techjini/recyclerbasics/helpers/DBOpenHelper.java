package com.example.techjini.recyclerbasics.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.techjini.recyclerbasics.databinding.RecyclerItemsBinding;
import com.example.techjini.recyclerbasics.model.Bookmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techjini on 13/9/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BOOKMARKDB";
    private static final int DATABASE_VERSION = 1;
    private static final String BOOKMARK_TABLE_NAME = "bookmark";
    private static final String BOOKMARK_TABLE_CREATE =
            "CREATE TABLE " + BOOKMARK_TABLE_NAME + "("
                    + "title" + " TEXT," + "link" + " TEXT,"
                    + "status" + " BOOLEAN" + ")";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BOOKMARK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOKMARK_TABLE_NAME);
        onCreate(db);
    }

    public void addBookmark() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", "Google");
        values.put("link", "www.google.com");
        values.put("status", 0);
        db.insert(BOOKMARK_TABLE_NAME, null, values);

        ContentValues values1 = new ContentValues();
        values1.put("title", "Yahoo");
        values1.put("link", "www.yahoo.com");
        values.put("status", 0);
        db.insert(BOOKMARK_TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put("title", "TechJini");
        values2.put("link", "www.techjini.com");
        values.put("status", 0);
        db.insert(BOOKMARK_TABLE_NAME, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put("title", "SAP");
        values3.put("link", "www.sap.com");
        values.put("status", 0);
        db.insert(BOOKMARK_TABLE_NAME, null, values3);

        ContentValues values4 = new ContentValues();
        values4.put("title", "Honeywell");
        values4.put("link", "www.honeywell.com");
        values.put("status", 0);
        db.insert(BOOKMARK_TABLE_NAME, null, values4);
        db.close();
    }

    public List<Bookmark> getAllBookmark() {
        List<Bookmark> resultList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + BOOKMARK_TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Bookmark bookmark = new Bookmark();
                bookmark.setTitle(cursor.getString(0));
                bookmark.setLink(cursor.getString(1));
                bookmark.setStatus(cursor.getInt(cursor.getColumnIndex("status")) == 1);
                if (bookmark.getStatus() == true) {
                    Log.d("STATUS", "checking box of " + bookmark.getTitle());
                }
                resultList.add(bookmark);
            } while (cursor.moveToNext());
        }
        return resultList;
    }

    public void checkBookmark(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("status", 1);
        int i = db.update(BOOKMARK_TABLE_NAME, data, "title = ?", new String[]{title});
        Log.d("STATUS", "setting " + title + " check to 1,return value " + i);
        db.close();
    }

    public void uncheckBookmark(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("status", 0);
        db.update(BOOKMARK_TABLE_NAME, data, "title = ?", new String[]{title});
        Log.d("STATUS", "setting " + title + " check to 0");
        db.close();
    }
}
