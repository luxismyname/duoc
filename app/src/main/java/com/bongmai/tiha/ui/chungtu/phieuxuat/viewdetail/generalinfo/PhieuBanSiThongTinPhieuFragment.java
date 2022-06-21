package com.bongmai.tiha.ui.chungtu.phieuxuat.viewdetail.generalinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.VattuxuatInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiActivity;
import com.bongmai.tiha.ui.chungtu.phieuxuat.PhieuBanSiAdapter;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;

import java.util.ArrayList;
import java.util.List;


public class PhieuBanSiThongTinPhieuFragment extends Fragment implements BaseFragment, PhieuBanSiThongTInPhieuContract.View{

    PhieuXuatInfo phieuXuatInfo;
    ConstraintLayout ctlTongtien;
    TextView tvNgay, tvSoCT, tvTenKhachHang, tvDienThoai, tvDiaChi, tvTongTien, tvKho, tvNhanVienBanHang, tvNhanVienGiaoHang, tvGhiChu;
    PhieuBanSiThongTinPhieuPresenter pbsPresenter;
    private static final String LOAI_NHANVIENBANHANG = "NVBH";
    private static final String LOAI_NHANVIENGIAOHANG = "NVGH";


    public static PhieuBanSiThongTinPhieuFragment newInstance(PhieuXuatInfo phieuXuatInfo) {
        PhieuBanSiThongTinPhieuFragment fragment = new PhieuBanSiThongTinPhieuFragment();
        Bundle args = new Bundle();
        args.putSerializable("PhieuXuatInfo", phieuXuatInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        phieuXuatInfo = (getArguments() != null) ? (PhieuXuatInfo) getArguments().getSerializable("PhieuXuatInfo") : new PhieuXuatInfo();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieubansi_detail_generalinfo, container, false);
        onInit(view);
        onLoadData();
        return view;
    }

    @Override
    public void onInit(View view) {

        tvNgay = view.findViewById(R.id.tvNgay);
        tvSoCT = view.findViewById(R.id.tvSoCT);
        tvTenKhachHang = view.findViewById(R.id.tvTenKhachHang);
        tvDienThoai = view.findViewById(R.id.tvDienThoai);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        tvTongTien = view.findViewById(R.id.tvTongTien);
        tvKho = view.findViewById(R.id.tvKho);
        tvNhanVienBanHang = view.findViewById(R.id.tvNVBH);
        tvNhanVienGiaoHang = view.findViewById(R.id.tvNVGH);
        tvGhiChu = view.findViewById(R.id.tvGhiChu);
        ctlTongtien = view.findViewById(R.id.ctlTongTien);
    }

    @Override
    public void onLoadData() {

        pbsPresenter = new PhieuBanSiThongTinPhieuPresenter(this);
        if(phieuXuatInfo != null )
        {

            tvSoCT.setText(phieuXuatInfo.getSoCt());
            tvNgay.setText(AppUtils.formatDateToString(phieuXuatInfo.getNgay(), "dd/MM/yyyy"));
            tvTenKhachHang.setText(phieuXuatInfo.getTennguoimua());
            tvDienThoai.setText(phieuXuatInfo.getDTDD());
            tvDiaChi.setText(phieuXuatInfo.getDiachi());
            tvKho.setText(phieuXuatInfo.getMSK());

            for (KhoInfo itemKho : PublicVariables.nguoiDungInfo.getListKho()) {
                if (itemKho.getMSK().equals(phieuXuatInfo.getMSK())) {
                    tvKho.setText(itemKho.getTenkho());
                    break;
                }
            }

            tvTongTien.setText(AppUtils.formatNumber("N0").format(phieuXuatInfo.getTriGia()));

            if(PublicVariables.nguoiDungInfo.getGroupName().equals("Administrartor")){
                ctlTongtien.setVisibility(View.VISIBLE);
            } else {
                ctlTongtien.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(phieuXuatInfo.getDDBH()))
                pbsPresenter.GetEmployee(phieuXuatInfo.getDDBH(), LOAI_NHANVIENBANHANG);
            if (!TextUtils.isEmpty(phieuXuatInfo.getMSNguoigiao()))
                pbsPresenter.GetEmployee(phieuXuatInfo.getMSNguoigiao(), LOAI_NHANVIENGIAOHANG);
//            tvNhanVienBanHang.setText(phieuXuatInfo.getDDBH());
//            tvNhanVienGiaoHang.setText(phieuXuatInfo.getMSNguoigiao());
            tvGhiChu.setText(phieuXuatInfo.getGhichu());

        }

    }

    @Override
    public void onGetEmployeeSuccess(EmployeeInfo itemResult, String loai) {
        if (loai.equals(LOAI_NHANVIENBANHANG)) {
            tvNhanVienBanHang.setText(itemResult.getEmployeeName());
        } else {
            tvNhanVienGiaoHang.setText(itemResult.getEmployeeName());
        }
    }

    @Override
    public void onGetEmployeeError(String error, String loai) {
        Toast.makeText(getContext(), "Lấy thông tin " + (loai.equals(LOAI_NHANVIENBANHANG) ? "nhân viên bán hàng" : "nhân viên giao hàng") + " lỗi. Chi tiết:\n" + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetKhoSuccess(KhoInfo itemResult) {
        tvKho.setText(itemResult.getTenkho());
    }

    @Override
    public void onGetKhoError(String error) {

    }
}
