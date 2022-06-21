package com.bongmai.tiha.utils;

public class AppConstants {
    /**
     * API
     */

//    public static String SERVER_NAME = "demogas.tiha.vn";
    //public static String SERVER_NAME = "tihaduoclocmobile.tiha.vn";
    public static String SERVER_NAME = "tihaduoclocmobile.tiha.vn";
    public static String SERVER_NAME_1 = "duocq10mobile.tiha.vn";

    public static String FTP_USERNAME = "demogas";
    public static String FTP_PASSWORD = "tiha@demo.123$";
    public static String URL_SERVER = "http://" + SERVER_NAME;
    public static String URL_SERVER_1 = "http://" + SERVER_NAME_1;

    public static String URL_IMAGE = "http://" + SERVER_NAME + "/resources/filereports/";
    public static String URL_UPDATE_CHANGELOG = "http://autoupdate.tiha.vn/DocumentMobiles/AppUpdate/DuocTongNew/duoctongnew-update-changelog.json";
    public static final String URL_UPDATE_FILE_APK = "http://autoupdate.tiha.vn/DocumentMobiles/AppUpdate/DuocTongNew/DuocTong.apk";
    public static final String Error_KetNoiServer = "Kết nối đến Server thất bại, hãy thử lại!";
    public static final String Error_KhongCoInternet = "Không có kết nối Internet, hãy thử lại!";
    //Login
    public static final String URL_CheckLogin = "/api/NguoiDung/PostLogin";
    public static final String URL_GetListMenu = "/api/Menu/GetListMenu?UserName={0}";
    public static final String URL_GetListNhomBaoCao = "/api/Report/GetListNhomBaoCao?userName={0}";
    public static final String URL_GetListNhomBaoCaoChiTiet = "/api/Report/GetListNhomBaoCaoChiTiet?maNhomBaoCao={0}&userName={1}";

    public static final String URL_GetDashboardDoanhThu = "/api/Report/GetDashboardDoanhThu";
    public static final String URL_GetDashboardCuocGoi = "/api/Report/GetDashboardCuocGoi";
    public static final String URL_GetListDoanhSoBanHangTheoChiNhanh = "/api/Report/GetListDoanhSoBanHangTheoChiNhanh";
    public static final String URL_GetListDoanhSoBanHangTheoKhachHang = "/api/Report/GetListDoanhSoBanHangTheoKhachHang";
    public static final String URL_GetListDoanhSoBanHangTheoNhanVien = "/api/Report/GetListDoanhSoBanHangTheoNhanVien";
    public static final String URL_GetListNhatKyBanHang = "/api/Report/GetListNhatKyBanHang";
    public static final String URL_GetListNhatKyBanHangChiTiet = "/api/Report/GetListNhatKyBanHangChiTiet";
    public static final String URL_GetListThongKeCuocGoi = "/api/Report/GetListThongKeCuocGoi";
    public static final String URL_GetListCongNoTongHop = "/api/Report/GetListCongNoTongHop";
    public static final String URL_GetListCongNoTongHopPhaiThu = "/api/Report/GetListCongNoTongHopPhaiThu";
    public static final String URL_GetListCongNoTongHopPhaiTra = "/api/Report/GetListCongNoTongHopPhaiTra";
    public static final String URL_GetListThongKeTonVoRong = "/api/Report/GetListThongKeTonVoRong";
    public static final String URL_GetListThongKeTonNuocVoRong = "/api/Report/GetListThongKeTonNuocVoRong";
    public static final String URL_GetListNhatKyNhapHangChiTiet = "/api/Report/GetListNhatKyNhapHangChiTiet";
    public static final String URL_GetListNhatKyNhapHang = "/api/Report/GetListNhatKyNhapHang";
    public static final String URL_GetListThongKeTonNuoc = "/api/Report/GetListThongKeTonNuoc";
    public static final String URL_GetListDiaChi = "/api/Address/GetListDiaChi?loaiDiaChi={0}&parentID={1}&parentName={2}";


    //Map
    public static final String URL_InsertUserLocation = "/api/Location/InsertUserLocation";

//    public static final String URL_GetListQuangDuongNhanVienGiaoHang = "/api/GiaoNhanHang/GetListQuangDuongNhanVienGiaoHang";

    //Supplier
    public static final String URL_GetListSupplier = "/api/Supplier/GetListSupplier";
    public static final String URL_InsertSupplier = "/api/Supplier/InsertSupplier";
    public static final String URL_GetListNhomKhachHang = "/api/NhomKhachHang/GetListNhomKhachHang?userName={0}";
    public static final String URL_GetSupplier = "/api/Supplier/GetSupplier?supplierID={0}";
    public static final String URL_DeleteSupplier = "/api/Supplier/DeleteSupplier?supplierID={0}&userName={1}";
    public static final String URL_CapNhatToaDoKhachHang = "/api/Supplier/CapNhatToaDo?supplierID={0}&viDo={1}&kinhDo={2}";
    //test
    public static final String URL_UpdateSupplier = "/api/Supplier/UpdateSupplier";
    //Product
    public static final String URL_GetListProduct = "/api/Product/GetListProduct";
    public static final String URL_GetListAllProduct = "/api/Product/GetListAllProduct";
    public static final String URL_GetProduct = "/api/Product/GetProduct?productID={0}";
    public static final String URL_GetProductTonKho = "/api/Product/GetProductTonKho?maKho={0}&productID={1}&ngay={2}";
    public static final String URL_GetProductDonGia = "/api/Product/GetProductDonGia";
    public static final String URL_KiemTraDuocXuatHang = "/api/Common/KiemTraDuocXuatHang?maKho={0}&productID={1}&ngay={2}&soPhieu={3}&soLuong={4}&userName={5}";
    public static final String URL_GetHinhAnhByProductID = "/api/Product/GetHinhAnhByProductID?productID={0}";
    public static final String URL_InsertProduct = "/api/Product/InsertProduct";
    public static final String URL_UpdateProduct = "/api/Product/UpdateProduct";
    public static final String URL_DeleteProduct = "/api/Product/DeleteProduct?productID={0}&userName={1}";
    //Employee
    public static final String URL_GetListEmployee = "/api/Employee/GetListEmployee?userName={0}&listChiNhanh={1}";
    public static final String URL_GetListBangGiaGroup = "/api/Common/GetListBangGiaGroup";
    public static final String URL_GetHinhAnhByEmployeeID = "/api/Employee/GetHinhAnhByEmployeeID?employeeID={0}";
    public static final String URL_GetEmployee = "/api/Employee/GetEmployee?employeeID={0}";
    public static final String URL_InsertEmployee = "/api/Employee/InsertEmployee";
    public static final String URL_UpdateEmployee = "/api/Employee/UpdateEmployee";
    public static final String URL_DeleteEmployee = "/api/Employee/DeleteEmployee?EmployeeID={0}&userName={1}";
    public static final String URL_GetListBoPhan = "/api/Employee/GetListBoPhan";
    public static final String URL_GetListChucVu = "/api/Employee/GetListChucVu";
    //CuocGoi
    public static final String URL_GetListCuocGoi = "/api/CuocGoi/GetListCuocGoi";
    public static final String URL_GetModifiedDateCuocGoi = "/api/CuocGoi/GetModifiedDateCuocGoi?listChiNhanh={0}";
    public static final String URL_KiemTraTonTaiPhieuXuatByCuocGoi = "/api/CuocGoi/KiemTraTonTaiPhieuXuatByCuocGoi?loaiPhieu={0}&ID={1}";
    public static final String URL_InsertCuocGoiTuKhachHang = "/api/CuocGoi/InsertCuocGoiTuKhachHang";
    public static final String URL_UpdateCuocGoiTuKhachHang = "/api/CuocGoi/UpdateCuocGoiTuKhachHang";
    //Phieu xuat
    public static final String URL_InsertPhieuBanSiWithSerialX = "/api/PhieuXuat/InsertPhieuBanSiWithSerialX";
    public static final String URL_UpdatePhieuBanSiWithSerialX = "/api/PhieuXuat/UpdatePhieuBanSiWithSerialX";
    public static final String URL_GetListPhieuXuat = "/api/PhieuXuat/GetListPhieuXuat";
    public static final String URL_GetPhieuXuat = "/api/PhieuXuat/GetPhieuXuat?soCT={0}";
    public static final String URL_GetChiTietPhieuXuat = "/api/PhieuXuat/GetChiTietPhieuXuat?soCT={0}";
    public static final String URL_DeletePhieuXuat= "/api/PhieuXuat/DeletePhieuXuat?soPhieu={0}&userName={1}";
    public static final String URL_InQuaInternet= "/api/PhieuXuat/InQuaInternet?soPhieu={0}&computerName={1}";

    //Phieu giao hang
    public static final String URL_UpdateTrangThaiPhieuXuat = "/api/GiaoNhanHang/UpdateTrangThaiPhieuXuat";
    public static final String URL_UpdateTrangThaiPhieuDatHang = "/api/GiaoNhanHang/UpdateTrangThaiPhieuDatHang";
    public static final String URL_GetListThongKeGiaoNhanHang = "/api/GiaoNhanHang/GetListThongKeGiaoNhanHang";
    public static final String URL_GetListTrangThai = "/api/TrangThai/GetListTrangThai";
    public static final String URL_GetListTrangThaiByLoaiPhieuByUser = "/api/TrangThai/GetListTrangThaiByLoaiPhieuByUser?loaiPhieu={0}&userName={1}";
    public static final String URL_CapNhatNhanVienGiaoHang = "/api/PhieuXuat/CapNhatNhanVienGiaoHang?soPhieu={0}&employeeID={1}";
    public static final String URL_CapNhatGhiChu = "/api/PhieuXuat/CapNhatGhiChu?soPhieu={0}&ghiChu={1}";
    public static final String URL_HuyDonHang = "/api/GiaoNhanHang/HuyDonHang?soChungTu={0}&ghiChu={1}&userName={2}";
    public static final String URL_HoanThanhDonHang = "/api/GiaoNhanHang/HoanThanhDonHang";
    public static final String URL_GetListQuangDuongNhanVienGiaoHang = "/api/GiaoNhanHang/GetListQuangDuongNhanVienGiaoHang";
    public static final String URL_GetListQuangDuongNhanVienGiaoHangChiTiet = "/api/GiaoNhanHang/GetListQuangDuongNhanVienGiaoHangChiTiet";
    public static final String URL_GetListThongKeKMNVGH = "/api/GiaoNhanHang/GetListThongKeKMNVGH?ngayBD={0}&ngayKT={1}";
    public static final String URL_GetListLuongNVGHTheoKM = "/api/GiaoNhanHang/GetListLuongNVGHTheoKM?ngayBD={0}&ngayKT={1}&MaNV={2}";
    public static final String URL_CapNhatThongTinGiaoHangSai = "/api/GiaoNhanHang/CapNhatThongTinGiaoHangSai?soChungTu={0}";

    //Kho
    public static final String URL_GetListKhoByUser = "/api/Kho/GetListKhoByUser?userName={0}";
    //Loai hang
    public static final String URL_GetListLoaiHangByUser = "/api/LoaiHang/GetListLoaiHangByUser?userName={0}";
    public static final String URL_GetLoaiHang = "/api/LoaiHang/GetLoaiHang?categoryID={0}";
    public static final String URL_InsertLoaiHang = "/api/LoaiHang/InsertLoaiHang";
    //Kho
    public static final String URL_GetKho = "/api/Kho/GetKho?maKho={0}";

    //TblConfig
    public static final String URL_GetTblConfig = "/api/TblConfig/Get?userName={0}";

    //DiaChi
    public static final String URL_InsertAddress = "/api/Address/InsertAddress";

    //SoDuVatTuDau

    public static final String URL_GetListSoDuVatTuDau = "/api/SoDuVatTuDau/GetListSoDuVatTuDau";
    public static final String URL_GetSoDuVatTuDau = "/api/SoDuVatTuDau/GetSoDuVatTuDau?soDuVatTuDauID={0}";
    public static final String URL_InsertSoDuVatTuDau = "/api/SoDuVatTuDau/InsertSoDuVatTuDau";
    public static final String URL_UpdateSoDuVatTuDau = "/api/SoDuVatTuDau/UpdateSoDuVatTuDau";
    public static final String URL_DeleteSoDuVatTuDau = "/api/SoDuVatTuDau/DeleteSoDuVatTuDau?soDuVatTuDauID={0}&userName={1}";


    public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmss";
    /**
     * Max size a image (kb)
     */
    public static int MAX_SIZE_IMAGE = 500;
    public static long DELAY_FIND_DATA = 1000;
    //px
    public static int MAX_WIDTH_IMAGE = 1000;
    public static int MAX_HEIGHT_IMAGE = 1000;
    public static int config_shortAnimTime = 200;



    private AppConstants() {
        // This utility class is not publicly instantiable
    }


}
