package com.delta.simplehttpcallsvolley;

import android.app.Activity;

import com.delta.simplehttpcallsvolley.utills.IResponseResult;
import com.delta.simplehttpcallsvolley.utills.IResponseSerializer;
import com.delta.simplehttpcallsvolley.utills.ServiceHandler;

/**
 * Created by Samantha on 2/12/17 9:47 AM .
 */

public class AppFacade {

    private static AppFacade mInstance = null;
    private ServiceHandler handler;

    public static AppFacade getInstance() {
        if (mInstance == null) {
            synchronized (AppFacade.class) {
                mInstance = new AppFacade();
            }
        }
        return mInstance;
    }

    public void initHandler(Activity activity, String url, int tag, IResponseSerializer iSerializer) {
        handler = new ServiceHandler(activity, url, tag);
        handler.setiSerializer(iSerializer);
        handler.makeGatRequest();
    }

    public void returnResponse(Activity activity, Object result, int tagNo, boolean success) {
        if (tagNo == 0) {
            ((IResponseResult) activity).response(result, success);
        } else {
            ((IResponseResult) activity).response(result, tagNo, success);
        }
    }
}
