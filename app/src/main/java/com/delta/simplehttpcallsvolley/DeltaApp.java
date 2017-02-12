package com.delta.simplehttpcallsvolley;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Samantha on 2/11/17 11:39 AM .
 */

public class DeltaApp extends Application {
    private static DeltaApp mInstance;
    private static RequestQueue mRequestQue;
    private static final String TAG = "DEFAULT ";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized DeltaApp getmInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQue() {
        if (mRequestQue == null) {
            mRequestQue = Volley.newRequestQueue(this.getApplicationContext());
        }
        return mRequestQue;
    }

    public <T> void addToRequestQue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQue().add(request);
    }

    public <T> void addToRequestQue(Request<T> request) {
        request.setTag(TAG);
        getRequestQue().add(request);
    }

    public void canclePendingRequest(Object tag) {
        if (mRequestQue != null) {
            mRequestQue.cancelAll(tag);
        }
    }

}