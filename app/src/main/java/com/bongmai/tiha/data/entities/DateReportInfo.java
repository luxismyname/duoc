package com.bongmai.tiha.data.entities;

import com.bongmai.tiha.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateReportInfo {

    public static final int HomNay = 0;
    public static final int HomQua = 1;
    public static final int BayNgayQua = 2;
    public static final int TuanNay = 3;
    public static final int TuanTruoc = 4;
    public static final int ThangNay = 5;
    public static final int ThangTruoc = 6;
    public static final int QuyNay = 7;
    public static final int NamNay = 8;
    public static final int LuaChonKhac = 9;
    public String StartDate;
    public String EndDate;
    public String Name;

    public static DateReportInfo GetDateReport(int position) {
        DateReportInfo item = new DateReportInfo();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat df;
        int day;
        int month;
        switch (position) {
            //Hom nay
            case 0:
                item.StartDate = item.EndDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "Hôm nay";
                break;
            //Hom qua
            case 1:
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                item.StartDate = item.EndDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "Hôm qua";
                break;
            //7 ngay qua
            case 2:
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, -6);
                item.StartDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "7 ngày qua";
                break;
            //Tuan nay
            case 3:
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                item.StartDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "Tuần này";
                break;
            //Tuan truoc
            case 4:
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                item.StartDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "Tuần trước";
                break;
            //Thang nay
            case 5:
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                //df = new SimpleDateFormat("MM/yyyy");
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                //tuNgay = "01/" + df.format(calendar.getTime());
                item.StartDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "Tháng này";
                break;
            //Thang truoc
            case 6:
//                        calendar.add(Calendar.MONTH, -1);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                item.StartDate = simpleDateFormat.format(calendar.getTime());
                item.Name = "Tháng trước";
                break;
            //Quy nay
            case 7:
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                month = calendar.get(Calendar.MONTH) + 1;
                df = new SimpleDateFormat("yyyy");
                if (month > 0 && month < 4) {
                    //Qui 1
                    item.StartDate = "01/01/" + df.format(calendar.getTime());
                } else if (month > 3 && month < 7) {
                    //Qui 2
                    item.StartDate = "01/04/" + df.format(calendar.getTime());
                } else if (month > 6 && month < 10) {
                    //Qui 3
                    item.StartDate = "01/07/" + df.format(calendar.getTime());
                } else if (month > 9 && month < 13) {
                    //Qui 4
                    item.StartDate = "01/10/" + df.format(calendar.getTime());
                }
                item.Name = "Quý này";
                break;
            //Nam nay
            case 8:
                item.EndDate = simpleDateFormat.format(calendar.getTime());
                df = new SimpleDateFormat("yyyy");
                item.StartDate = "01/01/" + df.format(calendar.getTime());
                item.Name = "Năm nay";
                break;
            default:
                break;
        }
        return item;
    }

    public static int getPositionDateReport(int menuItemID) {
        switch (menuItemID) {
            case R.id.action_homnay:
                return 0;
            case R.id.action_homqua:
                return 1;
            case R.id.action_7ngayqua:
                return 2;
            case R.id.action_tuannay:
                return 3;
            case R.id.action_tuantruoc:
                return 4;
            case R.id.action_thangnay:
                return 5;
            case R.id.action_thangtruoc:
                return 6;
            case R.id.action_quynay:
                return 7;
            case R.id.action_namnay:
                return 8;
            case R.id.action_luachonkhac:
                return 9;
            default:
                return -1;
        }
    }

    public static List<String> GetListDateReport() {
        List<String> listData = new ArrayList<>();
        listData.add("Hôm nay");
        listData.add("Hôm qua");
        listData.add("7 ngày qua");
        listData.add("Tuần này");
        listData.add("Tuần trước");
        listData.add("Tháng này");
        listData.add("Tháng trước");
        listData.add("Quý này");
        listData.add("Năm nay");
        listData.add("Tùy chọn");
        return listData;
    }
}
