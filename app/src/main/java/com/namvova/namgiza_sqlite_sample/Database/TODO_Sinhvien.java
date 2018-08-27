package com.namvova.namgiza_sqlite_sample.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.namvova.namgiza_sqlite_sample.Model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class TODO_Sinhvien extends SQLiteOpenHelper {
    private static final String LOG = "LOGNAMEGIZA";
    private static final String DATABASE_NAME = "NAMGIZA_DATABASE1";
    private static final String TABLE_NAME = "STUDENT";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String CLASS = "CLASS";
    private static final int VERSION = 1;

    private Context context;


    public TODO_Sinhvien(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCreateDatabase = "create table "
                + TABLE_NAME +
                "  ( " + ID + " integer primary key autoincrement ," +
                NAME + " text not null, " +
                CLASS + " text not null )";
        sqLiteDatabase.execSQL(queryCreateDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    /*
        // ADD SINHVIEN VAO TABLE;
       */
    public void addStudent(SinhVien sinhVien) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, sinhVien.getmName());
        contentValues.put(CLASS, sinhVien.getmClass());
        //
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        Log.d(LOG, "addSinhVien sussies");
    }

    /*
         SELECT THEO ID
         */
    public SinhVien selectByID(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{ID,
                        NAME, CLASS}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        SinhVien sinhVien = new SinhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        sqLiteDatabase.close();
        return sinhVien;
    }

    /*
    // SELETC * FROM
    */
    public List<SinhVien> selectAll() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<SinhVien> sinhVienList = new ArrayList<>();

        String query = " SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            SinhVien sinhVien = new SinhVien();
            sinhVien.setmID(cursor.getInt(0));
            sinhVien.setmName(cursor.getString(1));
            sinhVien.setmClass(cursor.getString(2));
            sinhVienList.add(sinhVien);
            cursor.moveToNext();
        } while (cursor.isAfterLast());
        cursor.close();
        Log.d(LOG,"Cont data : " + cursor.getCount());
        sqLiteDatabase.close();
        return sinhVienList;
    }

    /*
     Update theo dieu kien
      */
    public int upDate(SinhVien sinhVien) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, sinhVien.getmName());
        contentValues.put(CLASS, sinhVien.getmClass());
        return sqLiteDatabase.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(sinhVien.getmID())});
    }

    /*
    DELETE ALL
    */
    public int delete() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    /*
    DELETE BY ID
    */
    public int deleteByID(SinhVien sinhVien) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(sinhVien.getmID())});
    }

    /*
    COUNT SO LUONG RECORD THOA MAN.
    */
    public int countRecord() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);
        return cursor.getCount();
    }

}
