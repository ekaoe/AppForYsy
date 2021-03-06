package com.rootlibs.ok_http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;



import java.io.IOException;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//https://github.com/square/okhttp
//implementation("com.squareup.okhttp3:okhttp:4.8.1")
public class ToolOkHttp {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public void reqStart(PackSend send) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = null;

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Map<String, Object> map = send.getUpMap();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Integer) {
                builder.addFormDataPart(key, (Integer) value + "");
            } else if (value instanceof String) {
                builder.addFormDataPart(key, (String) value);
            } else if (value instanceof Byte[]) {
                builder.addFormDataPart(key, value.toString());
            } else {
                if (value == null) {
                    builder.addFormDataPart(key, "");
                } else {
                    builder.addFormDataPart(key, value + "");
                }
            }
        }
//        LogUtil.i("UP file (" + send.url+ " )" + map.toString());
//        String sign="";
        body = builder.build();
        Request request = new Request.Builder()
                .url(send.url)
                .addHeader("token", send.token)
//                .addHeader("s", sign)
//                .addHeader("d", System.currentTimeMillis()/1000+"")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "application/json")
                .addHeader("content-type", "application/json;charset:utf-8")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
//                pDown.code = -1;
//                pDown.errStr = ApplicationWork.netNotUser;
//                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
//                    pDown.fitParent(string);
//                    handler.sendEmptyMessage(0);
//                    LogUtil.i("znh", "http result(" + packHttpUp.getUrl() + ")  " + string);
                } else {
//                    pDown.code = -1;
//                    pDown.errStr = ApplicationWork.netdataerr;
//                    LogUtil.i("znh", " 链接异常： " + response.message());
//                    handler.sendEmptyMessage(0);
                }
            }
        });
    }


}
