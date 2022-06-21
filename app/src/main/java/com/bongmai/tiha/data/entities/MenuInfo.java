package com.bongmai.tiha.data.entities;

import com.bongmai.tiha.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MenuInfo {
    public static final String DEFAULT_FRAGMENT = "PhieuBanHangGasBMForm";
    private String MenuID;

    private String Name;

    private String Description;

    private String FormName;

    private int ThuTu;

    private int TabMenuID;

    private String TabMenuName;

    private int Level;

    private Boolean Full;

    private Boolean Read;

    private Boolean Write;

    private Boolean Modify;

    private Boolean Delete;

    private Boolean ShowMain;

    private List<MenuInfo> ListChildMenu;

    public String getMenuID() {
        return MenuID;
    }

    public void setMenuID(String menuID) {
        MenuID = menuID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getFormName() {
        return FormName;
    }

    public void setFormName(String formName) {
        FormName = formName;
    }

    public int getThuTu() {
        return ThuTu;
    }

    public void setThuTu(int thuTu) {
        ThuTu = thuTu;
    }

    public int getTabMenuID() {
        return TabMenuID;
    }

    public void setTabMenuID(int tabMenuID) {
        TabMenuID = tabMenuID;
    }

    public String getTabMenuName() {
        return TabMenuName;
    }

    public void setTabMenuName(String tabMenuName) {
        TabMenuName = tabMenuName;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public Boolean getFull() {
        return Full;
    }

    public void setFull(Boolean full) {
        Full = full;
    }

    public Boolean getRead() {
        return Read;
    }

    public void setRead(Boolean read) {
        Read = read;
    }

    public Boolean getWrite() {
        return Write;
    }

    public void setWrite(Boolean write) {
        Write = write;
    }

    public Boolean getModify() {
        return Modify;
    }

    public void setModify(Boolean modify) {
        Modify = modify;
    }

    public Boolean getDelete() {
        return Delete;
    }

    public void setDelete(Boolean delete) {
        Delete = delete;
    }

    public Boolean getShowMain() {
        return ShowMain;
    }

    public void setShowMain(Boolean showMain) {
        ShowMain = showMain;
    }

    public List<MenuInfo> getListChildMenu() {
        return ListChildMenu;
    }

    public void setListChildMenu(List<MenuInfo> listChildMenu) {
        ListChildMenu = listChildMenu;
    }

    public MenuInfo() {
        MenuID = "";
        Name = "";
        Description = "";
        FormName = "";
        ThuTu = 0;
        TabMenuID = -1;
        TabMenuName = "";
        Level = 0;
        Full = false;
        Read = false;
        Write = false;
        Modify = false;
        Delete = false;
        ShowMain = false;
        ListChildMenu = new ArrayList<>();
    }

    public MenuInfo getMenu(String jsonString) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<MenuInfo>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public ArrayList<MenuInfo> getListMenu(String jsonString) {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Type type = new TypeToken<ArrayList<MenuInfo>>() {
        }.getType();
        return gson.fromJson(jsonString, type);
    }

    public int getIDIconMenu() {
        int result = 0;
//        switch (FormName) {
//            case "TrangChu":
//                result = R.drawable.ic_menu_trangchu;
//                break;
//            case "HeThong":
//                result = R.drawable.ic_menu_hethong;
//                break;
//            case "DanhMuc":
//                result = R.drawable.ic_menu_danhmuc;
//                break;
//            case "ChungTu":
//                result = R.drawable.ic_menu_nghiepvu;
//                break;
//            case "TongHop":
//            case "ThongKe":
//                result = R.drawable.ic_menu_thongke;
//                break;
//            case "BaoCao":
//                result = R.drawable.ic_menu_baocao;
//                break;
//            case "CaiDat":
//                result = R.drawable.ic_menu_caidat;
//                break;
//            case "DangXuat":
//                result = R.drawable.ic_menu_dangxuat;
//                break;
//            case "TroGiup":
//                result = R.drawable.ic_menu_trogiup;
//                break;
//            default:
//                result = R.drawable.ic_menu_default;
//                break;
//        }
        return result;
    }

}
