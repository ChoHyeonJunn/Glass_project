package com.example.u.registeration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest{

    final static private String URL ="http://wjstkdduq.cafe24.com/CourseDelete.php";
    private Map<String, String> parameters;

    public DeleteRequest(String userID, String DY, String STRT_TM, String END_TM, String CMPS_NM, String BULD_NM, String CLSRM_NM, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("DY", DY);
        parameters.put("STRT_TM", STRT_TM);
        parameters.put("END_TM", END_TM);
        parameters.put("CMPS_NM", CMPS_NM);
        parameters.put("BULD_NM", BULD_NM);
        parameters.put("CLSRM_NM", CLSRM_NM);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
