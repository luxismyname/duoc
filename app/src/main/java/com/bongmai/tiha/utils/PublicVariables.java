package com.bongmai.tiha.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.bongmai.tiha.data.entities.BMConfigInfo;
import com.bongmai.tiha.data.entities.DanhSachXuatInfo;
import com.bongmai.tiha.data.entities.DiaChiInfo;
import com.bongmai.tiha.data.entities.DonViTinhInfo;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.LoaiHangInfo;
import com.bongmai.tiha.data.entities.MenuInfo;
import com.bongmai.tiha.data.entities.NguoiDungCuuLongInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.entities.QuanInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.data.entities.TrangThaiInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;

import java.util.ArrayList;
import java.util.List;


public class PublicVariables {
    public static NguoiDungInfo nguoiDungInfo = new NguoiDungInfo();

    public static NguoiDungCuuLongInfo nguoiDungCuuLongInfo = new NguoiDungCuuLongInfo();

    public static List<MenuInfo> listAllMenu = new ArrayList<>();

    public static SupplierInfo supplierInfo = new SupplierInfo();


    public static EmployeeInfo employeeInfo = new EmployeeInfo();

    public static ProductInfo productInfo = new ProductInfo();
    public static KhoInfo khoInfo = new KhoInfo();

    public static PhieuXuatInfo phieuXuatInfo = new PhieuXuatInfo();
    public static VattuxuatInfo vattuxuatInfo = new VattuxuatInfo();
    public static DanhSachXuatInfo danhSachXuatInfo = new DanhSachXuatInfo();
    public static DiaChiInfo diaChiInfo = new DiaChiInfo();
    public static QuanInfo quanInfo = new QuanInfo();

    public static Bitmap BMPicture;
    public static String NgayLamViec;
    public static List<NguoiDungInfo> listNguoiDung;
    public static String listChiNhanhByUserStr = "";
    public static BMConfigInfo bmConfigInfo = new BMConfigInfo();
    public static List<SupplierInfo> listSupplier;
    public static List<ProductInfo> listProduct;
    public static List<EmployeeInfo> listEmployee;
    public static List<LoaiHangInfo> listLoaiHang;
    public static List<TrangThaiInfo> listTrangThai;



    public static BMConfigInfo getBmSystemInfo(Context context) {
//        if (bmConfigInfo.getServerName().isEmpty() || bmConfigInfo.getDatabaseName().isEmpty() || bmConfigInfo.getServerPort().isEmpty() || bmConfigInfo.getDBUsername().isEmpty()) {
////            BaseService service = new BaseService(context);
////            bmConfigInfo = service.GetBMSystem();
//        }
        return bmConfigInfo;

    }

    public static void ClearData() {
        nguoiDungInfo = null;
        listAllMenu = null;
        listNguoiDung = null;
        BMPicture = null;
        bmConfigInfo = null;
        listSupplier = null;
        listProduct = null;
        listEmployee = null;
        listLoaiHang = null;
        listTrangThai = null;
    }



}
