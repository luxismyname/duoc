package com.bongmai.tiha.ui.map;

import android.os.AsyncTask;

import com.bongmai.tiha.data.entities.DistanceInfo;
import com.bongmai.tiha.data.entities.DurationInfo;
import com.bongmai.tiha.data.entities.EmployeeGiaoHangInfo;
import com.bongmai.tiha.data.entities.KhoInfo;
import com.bongmai.tiha.data.entities.NguoiDungCuuLongInfo;
import com.bongmai.tiha.data.entities.NguoiDungInfo;
import com.bongmai.tiha.data.entities.PhieuXuatInfo;
import com.bongmai.tiha.data.entities.RouteInfo;
import com.bongmai.tiha.data.entities.TblConfigInfo;
import com.bongmai.tiha.data.entities.ThongKeGiaoNhanHangInfo;
import com.bongmai.tiha.data.network.kho.IKhoModel;
import com.bongmai.tiha.data.network.kho.KhoModel;
import com.bongmai.tiha.data.network.nguoidung.INguoiDungModel;
import com.bongmai.tiha.data.network.nguoidung.NguoiDungModel;
import com.bongmai.tiha.data.network.phieugiaohang.IPhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieugiaohang.PhieuGiaoHangModel;
import com.bongmai.tiha.data.network.phieuxuat.IPhieuXuatModel;
import com.bongmai.tiha.data.network.phieuxuat.PhieuXuatModel;
import com.bongmai.tiha.data.network.tblconfig.ITblConfigModel;
import com.bongmai.tiha.data.network.tblconfig.TblConfigModel;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MapPresenter implements MapContract.Presenter {
    private static final String DIRECTION_URL_API = "https://maps.googleapis.com/maps/api/directions/json?";
    private static final String GOOGLE_API_KEY = "AIzaSyBu86Xy64zcwMiKCLbsDbjPHQ9iG_5TBh0";

    MapContract.View view;
    KhoModel khoModel;
    PhieuGiaoHangModel phieuGiaoHangModel;
    PhieuXuatModel phieuXuatModel;
    TblConfigModel tblConfigModel;

    public MapPresenter(MapContract.View view) {
        this.view = view;
        this.khoModel = new KhoModel();
        this.phieuGiaoHangModel = new PhieuGiaoHangModel();
        this.phieuXuatModel = new PhieuXuatModel();
        this.tblConfigModel = new TblConfigModel();
    }

    @Override
    public void GetPhieuXuat(String soCT) {
        phieuXuatModel.GetPhieuXuat(soCT, new IPhieuXuatModel.IOnGetPhieuXuatFinishedListener() {
            @Override
            public void onSuccess(PhieuXuatInfo phieuXuatResult) {
                view.onGetPhieuXuatSuccess(phieuXuatResult);
            }

            @Override
            public void onError(String error) {
                view.onGetPhieuXuatError(error);
            }
        });
    }

    @Override
    public void GetPhieuXuatCuuLong(String soCT) {
        phieuXuatModel.GetPhieuXuatCuuLong(soCT, new IPhieuXuatModel.IOnGetPhieuXuatCuuLongFinishedListener() {
            @Override
            public void onGetPhieuXuatCuuLongSuccess(PhieuXuatInfo phieuXuatResult) {
                view.onGetPhieuXuatCuuLongSuccess(phieuXuatResult);
            }

            @Override
            public void onGetPhieuXuatCuuLongError(String error) {
                view.onGetPhieuXuatCuuLongError(error);
            }
        });
    }

    @Override
    public void GetKho(String maKho) {
        khoModel.GetKho(maKho, new IKhoModel.IOnGetKhoFinishedListener() {
            @Override
            public void onSuccess(KhoInfo itemResult) {
                view.onGetKhoSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetKhoError(error);
            }
        });
    }

    @Override
    public void GetKhoCuuLong(String maKho) {
        khoModel.GetKhoCuuLong(maKho, new IKhoModel.IOnGetKhoCuuLongFinishedListener() {
            @Override
            public void onGetKhoCuuLongSuccess(KhoInfo itemResult) {
                view.onGetKhoCuuLongSuccess(itemResult);
            }

            @Override
            public void onGetKhoCuuLongError(String error) {
                view.onGetKhoCuuLongError(error);
            }
        });
    }

    @Override
    public void UpdateTrangThaiPhieuXuat(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuXuat(thongKeGiaoNhanHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuXuatFinishedListener() {
            @Override
            public void onSuccess() {
                view.onUpdateTrangThaiPhieuXuatSuccess(thongKeGiaoNhanHangInfo);
            }

            @Override
            public void onError(String error) {
                view.onUpdateTrangThaiPhieuXuatError(error);
            }
        });
    }

    @Override
    public void UpdateTrangThaiPhieuXuatCuuLong(ThongKeGiaoNhanHangInfo thongKeGiaoNhanHangInfo) {
        phieuGiaoHangModel.UpdateTrangThaiPhieuXuatCuuLong(thongKeGiaoNhanHangInfo, new IPhieuGiaoHangModel.IOnUpdateTrangThaiPhieuXuatCuuLongFinishedListener() {
            @Override
            public void onUpdateTrangThaiPhieuXuatCuuLongSuccess() {
                view.onUpdateTrangThaiPhieuXuatCuuLongSuccess(thongKeGiaoNhanHangInfo);
            }

            @Override
            public void onUpdateTrangThaiPhieuXuatCuuLongError(String error) {
                view.onUpdateTrangThaiPhieuXuatCuuLongError(error);
            }
        });
    }

    @Override
    public void CapNhatNhanVienGiaoHang(String soPhieu, String employeeID) {
        phieuGiaoHangModel.CapNhatNhanVienGiaoHang(soPhieu, employeeID, new IPhieuGiaoHangModel.IOnCapNhatNhanVienGiaoHangFinishedListener() {
            @Override
            public void onCapNhatNhanVienGiaoHangSuccess() {
                view.onCapNhatNhanVienGiaoHangSuccess();
            }

            @Override
            public void onCapNhatNhanVienGiaoHangError(String error) {
                view.onCapNhatNhanVienGiaoHangError(error);
            }
        });
    }

    @Override
    public void CapNhatNhanVienGiaoHangCuuLong(String soPhieu, String employeeID) {
        phieuGiaoHangModel.CapNhatNhanVienGiaoHangCuuLong(soPhieu, employeeID, new IPhieuGiaoHangModel.IOnCapNhatNhanVienGiaoHangCuuLongFinishedListener() {
            @Override
            public void onCapNhatNhanVienGiaoHangCuuLongSuccess() {
                view.onCapNhatNhanVienGiaoHangCuuLongSuccess();
            }

            @Override
            public void onCapNhatNhanVienGiaoHangCuuLongError(String error) {
                view.onCapNhatNhanVienGiaoHangCuuLongError(error);
            }
        });
    }

    @Override
    public void GetTblConfig(String userName) {
        tblConfigModel.GetTblConfig(userName, new ITblConfigModel.IOnGetTblConfigFinishedListener() {
            @Override
            public void onSuccess(TblConfigInfo itemResult) {
                view.onGetTblConfigSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetTblConfigError(error);
            }
        });
    }

    @Override
    public void GetTblConfigCuuLong(String userName) {
        tblConfigModel.GetTblConfigCuuLong(userName, new ITblConfigModel.IOnGetTblConfigCuuLongFinishedListener() {
            @Override
            public void onSuccess(TblConfigInfo itemResult) {
                view.onGetTblConfigCuuLongSuccess(itemResult);
            }

            @Override
            public void onError(String error) {
                view.onGetTblConfigError(error);
            }
        });
    }


    @Override
    public void DowloadDataMap(String origin, String destination) {
        try {
            String URL = createUrl(origin, destination);
            new DownloadRawData().execute(URL);
        } catch (UnsupportedEncodingException e) {
            view.onDowloadDataMapError(e.getMessage());
        }
    }

//    @Override
//    public void DowloadDataMapTurnBack(String destination,String origin ) {
//        try {
//            String URL = createUrlTurnBack(destination, origin);
//            new DownloadRawDataTurnBack().execute(URL);
//        } catch (UnsupportedEncodingException e) {
//            view.onDowloadDataMapTurnbackError(e.getMessage());
//        }
//    }

    @Override
    public void HoanThanhDonHang(EmployeeGiaoHangInfo employeeGiaoHangInfo) {
        phieuGiaoHangModel.HoanThanhDonHang(employeeGiaoHangInfo, new IPhieuGiaoHangModel.IOnHoanThanhDonHangFinishedListener() {
            @Override
            public void onHoanThanhDonHangSuccess(EmployeeGiaoHangInfo itemResult) {
                view.onHoanThanhDonHangSuccess(itemResult);
            }

            @Override
            public void onHoanThanhDonHangError(String error) {
                view.onHoanThanhDonHangError(error);
            }
        });
    }

    @Override
    public void HoanThanhDonHangCuuLong(EmployeeGiaoHangInfo employeeGiaoHangInfo) {
        phieuGiaoHangModel.HoanThanhDonHangCuuLong(employeeGiaoHangInfo, new IPhieuGiaoHangModel.IOnHoanThanhDonHangCuuLongFinishedListener() {
            @Override
            public void onHoanThanhDonHangCuuLongSuccess(EmployeeGiaoHangInfo itemResult) {
                view.onHoanThanhDonHangCuuLongSuccess(employeeGiaoHangInfo);
            }

            @Override
            public void onHoanThanhDonHangCuuLongError(String error) {
                view.onHoanThanhDonHangCuuLongError(error);
            }
        });
    }


    private class DownloadRawData extends AsyncTask<String, Void, String> {
        String link;
        @Override
        protected String doInBackground(String... params) {
            String link = params[0];
            try {
                this.link = link;
                URL url = new URL(link);
                InputStream is = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
//                e.printStackTrace();
            } catch (IOException e) {
//                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String res) {
            try {
                view.onDowloadDataMapSuccess(parseJSon(res), link);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

//    private class DownloadRawDataTurnBack extends AsyncTask<String, Void, String> {
//        String link;
//        @Override
//        protected String doInBackground(String... params) {
//            String link = params[0];
//            try {
//                this.link = link;
//                URL url = new URL(link);
//                InputStream is = url.openConnection().getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//
//                return buffer.toString();
//
//            } catch (MalformedURLException e) {
////                e.printStackTrace();
//            } catch (IOException e) {
////                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String res) {
//            try {
//                view.onDowloadDataMapTurnbackSuccess(parseJSon(res), link);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private String createUrl(String origin, String destination) throws UnsupportedEncodingException {
        //Chuyen sang utf-8
        String urlOrigin = URLEncoder.encode(origin, "utf-8");
        String urlDestination = URLEncoder.encode(destination, "utf-8");
        return DIRECTION_URL_API + "origin=" + urlOrigin + "&destination=" + urlDestination + "&key=" + GOOGLE_API_KEY;
    }

    private String createUrlTurnBack(String destination, String origin ) throws UnsupportedEncodingException {
        //Chuyen sang utf-8
        String urlOrigin = URLEncoder.encode(origin, "utf-8");
        String urlDestination = URLEncoder.encode(destination, "utf-8");
//        String urlStop = URLEncoder.encode(stop, "utf-8");
        return DIRECTION_URL_API + "origin=" + urlDestination  + "&destination=" + urlOrigin + "&key=" + GOOGLE_API_KEY;
    }



    private List<RouteInfo> parseJSon(String data) throws JSONException {
        if (data == null)
            return null;

        List<RouteInfo> routes = new ArrayList<RouteInfo>();
        JSONObject jsonData = new JSONObject(data);
        JSONArray jsonRoutes = jsonData.getJSONArray("routes");
        for (int i = 0; i < jsonRoutes.length(); i++) {
            JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
            RouteInfo route = new RouteInfo();

            JSONObject overview_polylineJson = jsonRoute.getJSONObject("overview_polyline");
            JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
            JSONObject jsonLeg = jsonLegs.getJSONObject(0);
            JSONObject jsonDistance = jsonLeg.getJSONObject("distance");
            JSONObject jsonDuration = jsonLeg.getJSONObject("duration");
            JSONObject jsonEndLocation = jsonLeg.getJSONObject("end_location");
            JSONObject jsonStartLocation = jsonLeg.getJSONObject("start_location");

            route.setDistance(new DistanceInfo(jsonDistance.getString("text"), jsonDistance.getInt("value")));
            route.setDuration(new DurationInfo(jsonDuration.getString("text"), jsonDuration.getInt("value")));
            route.setEndAddress(jsonLeg.getString("end_address"));
            route.setStartAddress(jsonLeg.getString("start_address"));
            route.setStartLocation(new LatLng(jsonStartLocation.getDouble("lat"), jsonStartLocation.getDouble("lng")));
            route.setEndLocation(new LatLng(jsonEndLocation.getDouble("lat"), jsonEndLocation.getDouble("lng")));
            route.setPoints(decodePolyLine(overview_polylineJson.getString("points")));

            routes.add(route);
        }

        return routes;
    }

    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }

        return decoded;
    }
}
