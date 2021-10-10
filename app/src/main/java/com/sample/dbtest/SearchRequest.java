package com.sample.dbtest;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://heremong.dothome.co.kr/Search.php";
    private Map<String, String> map;


    public SearchRequest(String SB, String ID,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("SB",SB);
        map.put("ID",ID);


    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

//
//    public void sendRequest(String url) {
//
//        StringRequest request = new StringRequest(
//                Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        textView.setText(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        textView.setText(error.getMessage());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                //params.put("key", "value");
//                return params;
//            }
//        };
//
//        // 이전 결과가 있더라도 새로 요청
//        request.setShouldCache(false);
//
//        AppHelper.requestQueue.add(request);
//
//     //   Toast.makeText(getApplicationContext(), "Request sent", Toast.LENGTH_SHORT).show();
//    }
}
