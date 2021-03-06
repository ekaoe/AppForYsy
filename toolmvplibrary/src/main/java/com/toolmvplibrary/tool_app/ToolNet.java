package com.toolmvplibrary.tool_app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ToolNet {
    public boolean checkNet(Context context){
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }
}
