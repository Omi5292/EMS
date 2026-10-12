package com.example.ems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EMSdatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EMSdata.db";
    public static final String TABLE_NAME = "records";
    public static final String TABLE_NAME2 = "tests";
    public static final String TABLE_NAME3 = "reports";
    public static final String TABLE_NAME4 = "medicines";

    //========create records table
    public static final String COL1 = "USER_NAME";
    public static final String COL2 = "MOBILE_NO";
    public static final String COL3 = "AADHAR_NO";
    public static final String COL4 = "PLACE";
    public static final String COL5 = "AGE";
    public static final String COL6 = "PASSWORD";

    //========create tests table
    public static final String COL7 = "PRIOR_TESTS";
    public static final String COL8 = "TEST_TYPE";
    public static final String COL9 = "DESCRIPTION";

    //========create reports table
    public static final String COL10 = "REPORT_TYPE";
    public static final String COL11 = "DESCRIPTION";

    //========create medicines table
    public static final String COL12 = "MEDICINE_NAME";
    public static final String COL13 = "MEDICINE_TYPE";
    public static final String COL14 = "DESCRIPTION";

    public EMSdatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //-----records table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COL1+" TEXT, "+COL2+" VARCHAR, "+COL3+" VARCHAR, "+COL4+" TEXT, "+COL5+" VARCHAR, "+COL6+" VARCHAR)");
        //-----tests table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME2+" ("+COL7+" VARCHAR, "+COL8+" VARCHAR, "+COL9+" VARCHAR)");
        //-----reports table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME3+" ("+COL10+" VARCHAR, "+COL11+" VARCHAR)");
        //-----medicines table
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME4+" ("+COL12+" VARCHAR, "+COL13+" VARCHAR, "+COL14+" VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    //===========insert values in records table
    public boolean addData(String item1,String item2,String item3,String item4,String item5,String item6) {
        if (item1 == null || item2 == null || item3 == null || item4 == null || item5 == null || item6 == null) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1,item1);
        cv.put(COL2,item2);
        cv.put(COL3,item3);
        cv.put(COL4,item4);
        cv.put(COL5,item5);
        cv.put(COL6,item6);
        long result = db.insert(TABLE_NAME,null,cv);

        return result != -1;
    }

    //===========Login Verification
    public boolean valid(String item1,String item6){
        if (item1 == null || item6 == null) {
            return false;
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USER_NAME=? AND PASSWORD=?",
                new String[]{item1,item6});
        if (mCursor != null) {
            boolean exists = mCursor.getCount() > 0;
            mCursor.close();
            return exists;
        }
        return false;
    }

    //===========insert values in tests table
    public boolean addData2(String item7,String item8,String item9) {
        if (item7 == null || item8 == null || item9 == null) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL7,item7);
        cv.put(COL8,item8);
        cv.put(COL9,item9);
        long result = db.insert(TABLE_NAME2,null,cv);

        return result != -1;
    }

    //===========view values of tests table
    public ArrayList<TestsModel> gettestdata(){
        ArrayList<TestsModel> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if (c != null) {
            if (c.moveToFirst()){
                do {
                    TestsModel viewModel = new TestsModel();
                    viewModel.setPriortests(c.getString(0) != null ? c.getString(0) : "");
                    viewModel.setTesttype(c.getString(1) != null ? c.getString(1) : "");
                    viewModel.setDescription(c.getString(2) != null ? c.getString(2) : "");
                    list.add(viewModel);
                }while (c.moveToNext());
            }
            c.close();
        }
        return  list;
    }

    //===========insert values in reports table
    public boolean addData3(String item10,String item11) {
        if (item10 == null || item11 == null) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL10,item10);
        cv.put(COL11,item11);
        long result = db.insert(TABLE_NAME3,null,cv);

        return result != -1;
    }

    //===========view values of reports table
    public ArrayList<ReportsModel> getreportdata(){
        ArrayList<ReportsModel> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME3;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if (c != null) {
            if (c.moveToFirst()){
                do {
                    ReportsModel viewModel = new ReportsModel();
                    viewModel.setReporttype(c.getString(0) != null ? c.getString(0) : "");
                    viewModel.setDescription(c.getString(1) != null ? c.getString(1) : "");
                    list.add(viewModel);
                }while (c.moveToNext());
            }
            c.close();
        }
        return  list;
    }

    //===========insert values in medicines table
    public boolean addData4(String item12,String item13,String item14) {
        if (item12 == null || item13 == null || item14 == null) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL12,item12);
        cv.put(COL13,item13);
        cv.put(COL14,item14);
        long result = db.insert(TABLE_NAME4,null,cv);

        return result != -1;
    }

    //===========view values of tests table
    public ArrayList<MedModel> getmeddata(){
        ArrayList<MedModel> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME4;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        if (c != null) {
            if (c.moveToFirst()){
                do {
                    MedModel viewModel = new MedModel();
                    viewModel.setMedname(c.getString(0) != null ? c.getString(0) : "");
                    viewModel.setMedtype(c.getString(1) != null ? c.getString(1) : "");
                    viewModel.setDescription(c.getString(2) != null ? c.getString(2) : "");
                    list.add(viewModel);
                }while (c.moveToNext());
            }
            c.close();
        }
        return  list;
    }
}
