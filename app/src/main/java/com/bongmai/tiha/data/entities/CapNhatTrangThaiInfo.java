package com.bongmai.tiha.data.entities;

import com.bongmai.tiha.utils.PublicVariables;

public class CapNhatTrangThaiInfo {

    private String SoChungTu;
    private String MaTrangThaiHienTai;
    private String TenTrangThaiHienTai;
    private String MaTrangThaiCu;
    private String UserName;

    public String getSoChungTu() {
        return SoChungTu;
    }

    public void setSoChungTu(String soChungTu) {
        SoChungTu = soChungTu;
    }

    public String getMaTrangThaiHienTai() {
        return MaTrangThaiHienTai;
    }

    public void setMaTrangThaiHienTai(String maTrangThaiHienTai) {
        MaTrangThaiHienTai = maTrangThaiHienTai;
    }

    public String getTenTrangThaiHienTai() {
        return TenTrangThaiHienTai;
    }

    public void setTenTrangThaiHienTai(String tenTrangThaiHienTai) {
        TenTrangThaiHienTai = tenTrangThaiHienTai;
    }

    public String getMaTrangThaiCu() {
        return MaTrangThaiCu;
    }

    public void setMaTrangThaiCu(String maTrangThaiCu) {
        MaTrangThaiCu = maTrangThaiCu;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public CapNhatTrangThaiInfo() {
        SoChungTu = "";
        MaTrangThaiHienTai = "";
        TenTrangThaiHienTai = "";
        MaTrangThaiCu = "";
        UserName = "";
    }

    public CapNhatTrangThaiInfo(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo, TrangThaiInfo trangThaiInfo){

        SoChungTu = thongKeGiaoNhanHangInfo.getSoCt();
        MaTrangThaiHienTai = trangThaiInfo.getMaTrangThai();
        TenTrangThaiHienTai = trangThaiInfo.getTenTrangThai();
        MaTrangThaiCu = thongKeGiaoNhanHangInfo.getMaTrangThai();
        UserName = PublicVariables.nguoiDungInfo.getUserName();

    }
}
