package com.delta.simplehttpcallsvolley.utills;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.delta.simplehttpcallsvolley.AppFacade;
import com.delta.simplehttpcallsvolley.DeltaApp;
import com.delta.simplehttpcallsvolley.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samantha on 2/11/17 2:06 PM .
 */

public class ServiceHandler extends AbstractServiceHandler {
    private Object jsonResponse;
    private Object serializedObject;
    private String url;
    private Activity activity;
    private int tag;

    private static final String LOG_TAG = "DELTA";

    private ProgressDialog dialog;

    public ServiceHandler(Activity activity, String url, int tag) {
        setActivity(activity);
        setTag(tag);
        setUrl(url);

        dialog = new ProgressDialog(activity);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
    }

    private Object getSerializedObject() {
        return serializedObject;
    }

    private void setSerializedObject(Object serializedObject) {
        this.serializedObject = serializedObject;
    }

    private Object getJsonResponse() {
        return jsonResponse;
    }

    private String getUrl() {
        return url;
    }

    private Activity getActivity() {
        return activity;
    }

    private int getTag() {
        return tag;
    }

    private void setJsonResponse(Object jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void setTag(int tag) {
        this.tag = tag;
    }

    public void makeGatRequest() {
        showpDialog();

        if (getNetworkState()) {
            StringRequest deltaRequest = new StringRequest(Request.Method.GET, getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(LOG_TAG, response);
                    if (response.trim().charAt(0) == '{') {
                        try {
                            setJsonResponse(new JSONObject(response.trim()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            setJsonResponse(new JSONArray(response.trim()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    retrieveData(true);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(LOG_TAG, error.toString());
                    try {
                        setJsonResponse(getActivity().getString(R.string.ERROR));
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        retrieveData(false);
                    }
                }
            });
            DeltaApp.getmInstance().addToRequestQue(deltaRequest);

        } else {
            try {
                setJsonResponse(getActivity().getString(R.string.CONNECTION_ERROR));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                retrieveData(false);
            }
        }

    }

    private void retrieveData(boolean success) {
        hidepDialog();
        if (success) {
            setSerializedObject(getiSerializer().serializeResponse(getJsonResponse()));
            (AppFacade.getInstance()).returnResponse(getActivity(), getSerializedObject(), getTag(), success);
        } else {
            (AppFacade.getInstance()).returnResponse(getActivity(), getJsonResponse(), getTag(), success);
        }
    }

    private void showpDialog() {
        if (!dialog.isShowing())
            dialog.show();
    }

    private void hidepDialog() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    /**
     * determine network status
     */
    private boolean getNetworkState() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE);
    }
}