package com.bongmai.tiha.data.entities;

import java.util.HashMap;
import java.util.Map;

public class EmployeeGiaoHangCondition {

    private String EmployeeID;
    private String NgayBD;
    private String NgayKT;


    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String ngayBD) {
        NgayBD = ngayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String ngayKT) {
        NgayKT = ngayKT;
    }

    public EmployeeGiaoHangCondition() {
        EmployeeID = "";
        NgayBD = "";
        NgayKT = "";

    }

    public EmployeeGiaoHangCondition(EmployeeGiaoHangCondition item) {
        EmployeeID = item.EmployeeID;
        NgayBD = String.valueOf(item.getNgayBD());
        NgayKT = String.valueOf(item.getNgayKT());
    }

    public Map<String, String> GetParameter() {
        Map<String, String> params = new HashMap<>();
        params.put("EmployeeID", EmployeeID);
        params.put("NgayBD", NgayBD);
        params.put("NgayKT", NgayKT);
        return params;
    }

}
