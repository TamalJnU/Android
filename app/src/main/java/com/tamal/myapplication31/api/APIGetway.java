package com.tamal.myapplication31.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tamal.myapplication31.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIGetway {

    public List<User> getList(Context con){
        List<User> userList = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(con);
        String url ="http://192.168.20.37:3000/users/all";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println("Response: --"+response);
//                                Toast.makeText(ApiMainActivity.this, "Massage: "+ response, Toast.LENGTH_LONG).show();

                        try {
                            JSONArray ja= new JSONArray(response);
                            for (int i = 0; i < ja.length() ; i++) {

                                JSONObject jb = ja.getJSONObject(i);
                                String name = jb.getString("name");
                                String username = jb.getString("username");
                                String password = jb.getString("password");
                                Integer id = jb.getInt("id");

                                User user =  new User(jb.getInt("id"), jb.getString("name"), jb.getString("username"), jb.getString("password"));

                                userList.add(user);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("error--"+ error.getLocalizedMessage());
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();

                return paramV;
            }
        };
        queue.add(stringRequest);


        return userList;
    }

}
