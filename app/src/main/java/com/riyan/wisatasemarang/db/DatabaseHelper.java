package com.riyan.wisatasemarang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.riyan.wisatasemarang.model.Wisata;

import java.util.ArrayList;

/**
 * Created by riyan on 21/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "dbwisata";
    private final static String DATABASE_TABLE = "table_wisata";
    private final static String WISATA_ID = "_id";
    private final static String NAMA_WISATA = "nama_wisata";
    private final static String GAMBAR_WISATA = "gambar_wisata";
    private final static String ALAMAT_WISATA = "alamat_wisata";
    private final static String DESKRIPSI_WISATA = "deskripsi_wisata";
    private final static String LATITUDE_WISATA = "latitude_wisata";
    private final static String LONGITUDE_WISATA = "longitude_wisata";

    private final static int DATABASE_VERSION = 4;
    private final static String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE
            + " (" + WISATA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAMA_WISATA + " VARCHAR(200), "
            + GAMBAR_WISATA + " VARCHAR(200), "
            + ALAMAT_WISATA + " TEXT, "
            + DESKRIPSI_WISATA + " TEXT, "
            + LATITUDE_WISATA + " VARCHAR(20), "
            + LONGITUDE_WISATA + " VARCHAR(20));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertData(String namaWisata,
                           String gambarWisata,
                           String alamatWisata,
                           String deskripsiWisata,
                           String latWisata,
                           String longWisata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA_WISATA, namaWisata);
        contentValues.put(GAMBAR_WISATA, gambarWisata);
        contentValues.put(ALAMAT_WISATA, alamatWisata);
        contentValues.put(DESKRIPSI_WISATA, deskripsiWisata);
        contentValues.put(LATITUDE_WISATA, latWisata);
        contentValues.put(LONGITUDE_WISATA, longWisata);
        long id = db.insert(DATABASE_TABLE, null, contentValues);
        db.close();
        return id;
    }

    public ArrayList<Wisata> getDataFavorite() {
        ArrayList<Wisata> listWisataFavorite = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columName = {WISATA_ID, NAMA_WISATA,
                GAMBAR_WISATA,
                ALAMAT_WISATA,
                DESKRIPSI_WISATA,
                LATITUDE_WISATA,
                LONGITUDE_WISATA};

        Cursor cursor = db.query(DATABASE_TABLE, columName, null, null,
                null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idWisata = cursor.getInt(cursor.getColumnIndex(WISATA_ID));
                String namaWisata = cursor.getString(cursor.getColumnIndex(NAMA_WISATA));
                String gambarWisata = cursor.getString(cursor.getColumnIndex(GAMBAR_WISATA));
                String alamatWisata = cursor.getString(cursor.getColumnIndex(ALAMAT_WISATA));
                String deskripsiWisata = cursor.getString(cursor.getColumnIndex(DESKRIPSI_WISATA));
                String latWisata = cursor.getString(cursor.getColumnIndex(LATITUDE_WISATA));
                String longWisata = cursor.getString(cursor.getColumnIndex(LONGITUDE_WISATA));

                Wisata wisataFavorite = new Wisata(String.valueOf(idWisata),
                        namaWisata,
                        gambarWisata,
                        deskripsiWisata,
                        alamatWisata,
                        latWisata,
                        longWisata);
                listWisataFavorite.add(wisataFavorite);
            }
        }
        db.close();
        return listWisataFavorite;
    }

    public int delete(String namaWisata) {
        SQLiteDatabase db = this.getWritableDatabase();
        String namaKolomnya = NAMA_WISATA + " = ?";
        String[] nilaiFieldnya = {namaWisata};
        int count = db.delete(DATABASE_TABLE, namaKolomnya, nilaiFieldnya);
        return count;
    }

}
