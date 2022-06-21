package com.bongmai.tiha.ui.baocao.cuocgoi;

import com.bongmai.tiha.data.entities.DanhSachCuocGoiInfo;
import com.bongmai.tiha.data.entities.DieuKienLocInfo;

import java.util.List;


public interface BaoCaoCuocGoiContract {

    interface View{
        void onGetListThongKeCuocGoiSuccess(List<DanhSachCuocGoiInfo> listResult);

        void onGetListThongKeCuocGoiError(String error);
    }

    interface Presenter {
        void GetListThongKeCuocGoi(DieuKienLocInfo dieuKienLocInfo);
    }
}
