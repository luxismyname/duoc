package com.bongmai.tiha.ui.danhmuc.employee.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bongmai.tiha.R;
import com.bongmai.tiha.data.entities.EmployeeInfo;
import com.bongmai.tiha.data.entities.SupplierInfo;
import com.bongmai.tiha.ui.base.BaseFragment;
import com.bongmai.tiha.utils.AppUtils;

public class ThongTinNhanVienFragment extends Fragment implements BaseFragment {
    TextView
            tvMaNV,
            tvTenNV,
            tvSDT1,
            tvNgaySinh,
            tvDiaChi,
            tvEmail,
            tvCMND,
            tvHesoluong,
            tvBHXH,
            tvBHYT,
            tvBoPhan,
            tvVitri;

    RadioButton rdoNam, rdoNu;
    EmployeeInfo employeeInfo;

    public static ThongTinNhanVienFragment newInstance(EmployeeInfo employeeInfo) {
        ThongTinNhanVienFragment fragment = new ThongTinNhanVienFragment();
        Bundle args = new Bundle();
        args.putSerializable("EmployeeInfo", employeeInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        employeeInfo = (getArguments() != null) ? (EmployeeInfo) getArguments().getSerializable("EmployeeInfo") : new EmployeeInfo();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin_nhan_vien, container, false);
        onInit(view);
        onLoadData();
        return view;

    }



    @Override
    public void onInit(View view) {
        tvMaNV = view.findViewById(R.id.tvMaNV);
        tvTenNV = view.findViewById(R.id.tvTenNV);
        tvSDT1 = view.findViewById(R.id.tvSDT1);

        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvHesoluong = view.findViewById(R.id.tvHeSoLuong);
        tvCMND = view.findViewById(R.id.tvCMND);
        tvBHXH = view.findViewById(R.id.tvBHXH);
        tvBHYT = view.findViewById(R.id.tvBHYT);
        rdoNam = view.findViewById(R.id.rdoNam);
        rdoNu = view.findViewById(R.id.rdoNu);
        tvBoPhan = view.findViewById(R.id.tvBoPhan);
        tvVitri = view.findViewById(R.id.tvViTri);

    }

    @Override
    public void onLoadData() {

        if(employeeInfo!=null){
            tvMaNV.setText(employeeInfo.getEmployeeID());
            tvTenNV.setText(employeeInfo.getEmployeeName());
            tvSDT1.setText(employeeInfo.getTel());
            tvNgaySinh.setText(AppUtils.formatDateToString(employeeInfo.getBirthDate(), "dd/MM/yyyy"));
            tvDiaChi.setText(employeeInfo.getAddress());
            tvEmail.setText(employeeInfo.getEmail());
            tvHesoluong.setText(employeeInfo.getBasicSalary());
            tvCMND.setText(employeeInfo.getPassportNo());
            tvBHXH.setText(employeeInfo.getSocialInsuranceNo());
            tvBHYT.setText(employeeInfo.getSecurityNo());

            if(employeeInfo.getSex() == EmployeeInfo.GIOITINH_NAM){
                rdoNam.setChecked(true);
            } else if(employeeInfo.getSex() == EmployeeInfo.GIOITINH_NU){
                rdoNu.setChecked(true);
            }
            tvBoPhan.setText(employeeInfo.getDepartmentID());
            tvVitri.setText(employeeInfo.getPositionName());
        }

    }
}