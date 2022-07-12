package com.smarteist.imageslider.Data.Api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.smarteist.imageslider.Data.Baseurl.Baseurl;
import com.smarteist.imageslider.Data.Endpoints.Endpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Api {
    final AppCompatActivity contex  ;

    public Api(AppCompatActivity value){
        contex=value;
    };
    public void getValue(final VolleyCallBack myValue) {
        RequestQueue queue = Volley.newRequestQueue(contex);

        String url = Baseurl.baseurl+ Endpoint.rewards;

       // myValue.onSuccess("gfhfg");
        final String[] currentResponse = new String[1];
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        //textView.setText("Response is: " + response.substring(0,500));
//
//                        Log.d("tag",response.toString());
//
//                        myValue.onSuccess(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                currentResponse[0] =error.getMessage();
//            }
//        });
//        queue.add(stringRequest);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                myValue.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
    }
}
