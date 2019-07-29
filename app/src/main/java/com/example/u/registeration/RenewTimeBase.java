package com.example.u.registeration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RenewTimeBase extends StringRequest{

    final static private String URL ="http://wjstkdduq.cafe24.com/RenewTimeBase.php";
    private Map<String, String> parameters;

    public RenewTimeBase(String userID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
