package com.bongmai.tiha.data.network.product;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bongmai.tiha.data.entities.ProductCondition;
import com.bongmai.tiha.data.entities.ProductInfo;
import com.bongmai.tiha.data.network.api.APIService;
import com.bongmai.tiha.data.network.api.VolleyCallback;
import com.bongmai.tiha.utils.AppConstants;
import com.bongmai.tiha.utils.AppUtils;
import com.bongmai.tiha.utils.PublicVariables;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ProductModel implements IProductModel {

    APIService service;


    @Override
    public void InsertProduct(ProductInfo productInfo, final IOnInsertProductFinishedListener listener) {
        String URL = AppConstants.URL_InsertProduct;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        params.put("HinhAnh", TextUtils.isEmpty(productInfo.getHinhAnh()) ? "" : productInfo.getHinhAnh() );
        productInfo.setHinhAnh(null);
        params.put("itemProduct", new Gson().toJson(productInfo));
        service.Insert(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onInsertProductSuccess(new ProductInfo().getProduct(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onInsertProductError(AppUtils.getMessageVolleyError(error));
            }
        }, params);

    }

    @Override
    public void UpdateProduct(ProductInfo productInfo, IOnUpdateProductFinishedListener listener) {
        String URL = AppConstants.URL_UpdateProduct;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        params.put("UserName", PublicVariables.nguoiDungInfo.getUserName());
        params.put("HinhAnh", TextUtils.isEmpty(productInfo.getHinhAnh()) ? "" : productInfo.getHinhAnh());
        productInfo.setHinhAnh(null);
        params.put("itemProduct", new Gson().toJson(productInfo));
        service.Update(Request.Method.PUT,new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("LogProductUpdate", response);
                listener.onUpdateProductSuccess(new ProductInfo().getProduct(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onUpdateProductError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListProduct(ProductCondition condition, final IOnGetListProductFinishedListener listener) {
        String URL = AppConstants.URL_GetListProduct;
        service = new APIService(URL);
        Map<String, String> params = condition.GetParameter();
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String json = jsonObject.getJSONArray("List").toString();
                    long total = Long.parseLong(jsonObject.get("Total").toString());
                    listener.onGetListProductSuccess(new ProductInfo().getListProduct(json), total);
                } catch (JSONException e) {
                    listener.onGetListProductError(e.getMessage());
                }
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListProductError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetListAllProduct(ProductCondition condition, final IOnGetListAllProductFinishedListener listener) {
        String URL = AppConstants.URL_GetListAllProduct;
        service = new APIService(URL);
//        Map<String, String> params = condition.GetParameter();
        Map<String, String> params = new HashMap<>();
//        params.put("Begin", String.valueOf(condition.getBegin()));
//        params.put("End", String.valueOf(condition.getEnd()));
        params.put("NhomLoaiHang", condition.getNhomLoaiHang());
        params.put("UserName", condition.getUserName());
        params.put("TextSearch", condition.getTextSearch());
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("listod",response);
                listener.onGetListAllProductSuccess(new ProductInfo().getListProduct(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetListAllProductError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }


    @Override
    public void GetProduct(String productID, final IOnGetProductFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetProduct, productID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Log.d("rep", response);
                listener.onGetProductSuccess(new ProductInfo().getProduct(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetProductError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetProductTonKho(String maKho, String productID, Date ngay, IOnGetProductTonKhoFinishedListener listener) {
        String URL = MessageFormat.format(AppConstants.URL_GetProductTonKho, maKho, productID, AppUtils.formatDateToString(ngay, "dd/MM/yyyy"));
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetProductTonKhoSuccess(Double.parseDouble(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetProductTonKhoError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void GetProductDonGia(String loaiPhieu, String supplierID, String productID, Date ngay, double soLuong, String donViTinh, IOnGetProductDonGiaFinishedListener listener) {
        String URL = AppConstants.URL_GetProductDonGia;
        service = new APIService(URL);
        Map<String, String> params = new HashMap<>();
        params.put("LoaiPhieu", loaiPhieu);
        params.put("SupplierID", supplierID);
        params.put("ProductID", productID);
        params.put("Ngay", AppUtils.formatDateToString(ngay, "dd/MM/yyyy"));
        params.put("SoLuong", String.valueOf(soLuong));
        params.put("DonViTinh", donViTinh);
        service.DownloadJsonPOST(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onGetProductDonGiaSuccess(Double.parseDouble(response));
            }

            @Override
            public void onError(VolleyError error) {
                listener.onGetProductDonGiaError(AppUtils.getMessageVolleyError(error));
            }
        }, params);
    }

    @Override
    public void GetHinhAnhByProductID(String productID, final IOnGetHinhAnhByProductIDFinishedListener listener) {

        String URL = MessageFormat.format(AppConstants.URL_GetHinhAnhByProductID, productID);
        service = new APIService(URL);
        service.DownloadJson(new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                listener.onSuccess(response);
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(AppUtils.getMessageVolleyError(error));
            }
        });
    }

    @Override
    public void DeleteProduct( String supplierID, final IOnDeleteProductFinishedListener listener) {

        String URL = MessageFormat.format(AppConstants.URL_DeleteProduct, supplierID, PublicVariables.nguoiDungInfo.getUserName());
        service = new APIService(URL);
        service.Delete(Request.Method.DELETE, new VolleyCallback() {
            @Override

            public void onSuccess(String response) {
                Log.d("Product", response);
                listener.onDeleteProductSuccess();
            }

            @Override
            public void onError(VolleyError error) {
                listener.onDeleteProductError(AppUtils.getMessageVolleyError(error));
            }
        });


    }




}
