package com.example.manng.csc_25_07_navigationdrawerlayout2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by vudinhai on 6/23/17.
 */

public class DBHelper {
    String DATABASE_NAME = "ShopDB.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase db = null;
    ArrayList<String> arrayList;

    Context context;

    public DBHelper(Context context) {
        this.context = context;

        processSQLite();
    }

    private void processSQLite() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                copyDatabaseFromAsset();
                Toast.makeText(context, "Copy database successfully !", Toast.LENGTH_SHORT).show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void copyDatabaseFromAsset() {
        try {
            InputStream databaseInputStream = context.getAssets().open(DATABASE_NAME);

            String outputStream = getPathDatabaseSystem();

            File file = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!file.exists()) {
                file.mkdir();
            }

            OutputStream databaseOutputStream = new FileOutputStream(outputStream);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = databaseInputStream.read(buffer)) > 0) {
                databaseOutputStream.write(buffer, 0, length);
            }


            databaseOutputStream.flush();
            databaseOutputStream.close();
            databaseInputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getPathDatabaseSystem() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public ArrayList getAllMenu() {
        ArrayList result = new ArrayList();

        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM DanhMucMenu";

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);

            result.add(name);
        }

        result.add("Trang cá nhân");
        result.add("Về chúng tôi");

        return result;
    }

//    public ArrayList<Movie> getAllMovie() {
//        ArrayList<Movie> result = new ArrayList<Movie>();
//
//        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
//
//        String sql = "SELECT * FROM Movies";
//
//        Cursor cursor = db.rawQuery(sql, null);
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String title = cursor.getString(1);
//            String image = cursor.getString(2);
//            float rating = cursor.getFloat(3);
//            int year = cursor.getInt(4);
//            result.add(new Movie(id, title, image, rating, year));
//        }
//
//        return result;
//    }
//
//    public void insertMovie(Movie movie) {
//        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("title", movie.getTitle());
//        contentValues.put("image", movie.getImage());
//        contentValues.put("rating", movie.getRating());
//        contentValues.put("releaseYear", movie.getReleaseYear());
//
//        if (db.insert("Movies", null, contentValues) > 0) {
//            Toast.makeText(context, "Insert Successfully", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void updateMovie(Movie movie) {
//        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("title", movie.getTitle());
//        contentValues.put("image", movie.getImage());
//        contentValues.put("rating", movie.getRating());
//        contentValues.put("releaseYear", movie.getReleaseYear());
//
//        if (db.update("Movies", contentValues, "id=" + movie.getId(), null) > 0) {
//            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    public void deleteMovie(Movie movie) {
//        db = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
//
//        if (db.delete("Movies", "id=" + movie.getId(), null) > 0) {
//            Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
//        }
//    }


}
