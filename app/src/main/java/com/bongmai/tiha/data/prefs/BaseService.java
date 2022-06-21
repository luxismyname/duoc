package com.bongmai.tiha.data.prefs;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.bongmai.tiha.data.entities.BMConfigInfo;


public class BaseService {

    Context mContext;
    private static DBSystem dbSystem;

    public static final int Login_Timeout = 5;

    public BaseService(Context context) {
        this.mContext = context;
        this.dbSystem = new DBSystem(context);
    }

    public BMConfigInfo GetConfig() {
         BMConfigInfo bmConfigInfo = null;
        try {
            bmConfigInfo = new BMConfigInfo();
            dbSystem.open();
            Cursor cur = dbSystem.SelectSystemAll();
            if (cur != null) {
                while (cur.moveToNext()) {
                    //Chi nhanh
                    bmConfigInfo.setChiNhanh(TextUtils.isEmpty(cur.getString(1)) ? "" : cur.getString(1));
                    //My phone
                    bmConfigInfo.setMyPhone(TextUtils.isEmpty(cur.getString(2)) ? "" : cur.getString(2));
                    //KhachHangMacDinh
                    bmConfigInfo.setKhachHangMacDinh(TextUtils.isEmpty(cur.getString(3)) ? "" : cur.getString(3));
                    //Company
                    bmConfigInfo.setCompany(TextUtils.isEmpty(cur.getString(4)) ? "CTY TNHH CNTT BÔNG MAI" : cur.getString(4));
                    //Address
                    bmConfigInfo.setAddress(TextUtils.isEmpty(cur.getString(5)) ? "11 Đường 21D, Bình Trị Đông B, Bình Tân, TP.HCM" : cur.getString(5));
                    //Tel
                    bmConfigInfo.setTel(TextUtils.isEmpty(cur.getString(6)) ? "0913.100.388" : cur.getString(6));
                    //LuuY
                    bmConfigInfo.setLuuY(TextUtils.isEmpty(cur.getString(7)) ? "" : cur.getString(7));
                }
            }
            cur.close();
            dbSystem.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bmConfigInfo;
    }

    public void UpdateConfig(BMConfigInfo bmConfigInfo) {
        try {
            dbSystem.open();

            dbSystem.Update(bmConfigInfo);
            dbSystem.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
