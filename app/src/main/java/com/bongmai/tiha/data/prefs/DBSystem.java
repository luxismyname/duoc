package com.bongmai.tiha.data.prefs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bongmai.tiha.data.entities.BMConfigInfo;

public class DBSystem {
    public static final String KEY_ROWID = "_ID";
    public static final String ChiNhanh = "BM_ChiNhanh";
    public static final String MyPhone = "BM_MyPhone";
    public static final String KhachHangMacDinh = "BM_KhachHangMacDinh";
    public static final String Company = "BM_Company";
    public static final String Address = "BM_Address";
    public static final String Tel = "BM_Tel";
    public static final String LuuY = "BM_LuuY";

    public static final String DATABASE_NAME = "BM_TIHA";
    public static final String DATABASE_TABLE_CONFIG = "BM_CONFIG";
    public static final int DATABASE_VERSION = 1;

    private DbHelper kHelper;
    private static Context kContext;
    private SQLiteDatabase kDatabase;

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            kContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "create table " + DATABASE_TABLE_CONFIG + " ("
                    + KEY_ROWID + " integer primary key," //0
                    + ChiNhanh + " text not null," //1
                    + MyPhone + " text not null,"//2
                    + KhachHangMacDinh + " text not null,"//3
                    + Company + " text not null,"//4
                    + Address + " text not null,"//5
                    + Tel + " text not null,"//6
                    + LuuY + " text not null"//7
                    + "); ";
            db.execSQL(query);

            query = "INSERT INTO " + DATABASE_TABLE_CONFIG + "("
                    + KEY_ROWID + "," //0
                    + ChiNhanh + ", "//1
                    + MyPhone + ", "//2
                    + KhachHangMacDinh + ", "//3
                    + Company + ", " //4
                    + Address + ", " //5
                    + Tel + ", " //6
                    + LuuY //7
                    + ")"

                    + "VALUES(" +
                    "1," +//0
                    " ''," +//1
                    " ''," +//2
                    " ''," +//3
                    " ''," +//4
                    " ''," +//5
                    " ''," +//6
                    " ''" +//7
                    ")";
            db.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + DATABASE_TABLE_CONFIG);
            onCreate(db);
        }
    }

    public DBSystem(Context c) {
        kContext = c;
    }

    public DBSystem open() {
        kHelper = new DbHelper(kContext);
        kDatabase = kHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        kHelper.close();
    }

    public void Insert(String ID, BMConfigInfo bmConfigInfo) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROWID, ID);
        cv.put(ChiNhanh, bmConfigInfo.getChiNhanh());
        cv.put(MyPhone, bmConfigInfo.getMyPhone());
        cv.put(KhachHangMacDinh, bmConfigInfo.getKhachHangMacDinh());
        cv.put(Company, bmConfigInfo.getCompany());
        cv.put(Address, bmConfigInfo.getAddress());
        cv.put(Tel, bmConfigInfo.getTel());
        cv.put(LuuY, bmConfigInfo.getLuuY());
        kDatabase.insert(DATABASE_TABLE_CONFIG, null, cv);
    }

    public void Update(BMConfigInfo bmConfigInfo) {
        ContentValues cv = new ContentValues();
        cv.put(ChiNhanh, bmConfigInfo.getChiNhanh());
        cv.put(MyPhone, bmConfigInfo.getMyPhone());
        cv.put(KhachHangMacDinh, bmConfigInfo.getKhachHangMacDinh());
        cv.put(Company, bmConfigInfo.getCompany());
        cv.put(Address, bmConfigInfo.getAddress());
        cv.put(Tel, bmConfigInfo.getTel());
        cv.put(LuuY, bmConfigInfo.getLuuY());
        kDatabase.update(DATABASE_TABLE_CONFIG, cv, null, null);
    }

    public void UpdateMyPhone(String myPhone) {
        ContentValues cv = new ContentValues();
        cv.put(MyPhone, myPhone);
        kDatabase.update(DATABASE_TABLE_CONFIG, cv, null, null);
    }

    public Cursor SelectSystemAll() {
        return kDatabase.query(DATABASE_TABLE_CONFIG, new String[]{
                KEY_ROWID,//0
                ChiNhanh,//1
                MyPhone,//2
                KhachHangMacDinh,//3
                Company, //4
                Address, //5
                Tel, //6
                LuuY, //7
        }, null, null, null, null, null);
    }
}
