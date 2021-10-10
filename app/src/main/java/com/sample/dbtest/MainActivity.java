package com.sample.dbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<Item> items= new ArrayList<>();

    ItemAdapter adapter;

    EditText et_search;
//              stringrequest로 값을 db에 저장한다음 그 값으로 검색하기.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        adapter= new ItemAdapter(this, items);
        recyclerView.setAdapter(adapter);
        et_search = findViewById(R.id.search_bar);

        //리사이클러뷰의 레이아웃 매니저 설정
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }


    public  void clickSearch (View view){
        String SB = et_search.getText().toString();
        String ID = "yejin03";
      //  String Postdata;
        //서버주소
        String serverUrl = "http://heremong.dothome.co.kr/Search.php";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String SB = et_search.getText().toString();
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        SearchRequest searchRequest = new SearchRequest(SB,ID,responseListener);
        RequestQueue queue2 = Volley.newRequestQueue(this);
        queue2.add(searchRequest);
    }

    public void clickLoad(View view) {

        // 서버로 Volley를 이용해서 요청을 함.
        //서버의 loadDBtoJson.php파일에 접속하여 (DB데이터들)결과 받기
        //Volley+ 라이브러리 사용



        String SB = et_search.getText().toString();
        String Postdata;
        //서버주소
        String serverUrl = "http://heremong.dothome.co.kr/Search.php";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String SB = et_search.getText().toString();
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        };


        //결과를 JsonArray 받을 것이므로..
        //StringRequest가 아니라..
        //JsonArrayRequest를 이용할 것임
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            //volley 라이브러리의 GET방식은 버튼 누를때마다 새로운 갱신 데이터를 불러들이지 않음. 그래서 POST 방식 사용
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //불러들이는지 확인하기 위해 해당내용을 토스트메세지로 출력하도록 함(개발편의를 위한 것)

                //파라미터로 응답받은 결과 JsonArray를 분석

                items.clear(); //일단 깨끗하게 처리함
                adapter.notifyDataSetChanged();
                try {
                    //배열로 처리했기 때문에 배열의 길이가 우리가 만들어야 할 리사이클러뷰 갯수와 같음
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject= response.getJSONObject(i);

                        int Place_id= Integer.parseInt(jsonObject.getString("Place_id")); //no가 문자열이라서 바꿔야함.
                        String P_name=jsonObject.getString("P_name");
                        String P_address=jsonObject.getString("P_address");
                        String P_image=jsonObject.getString("P_image");

                        //이미지 경로의 경우 서버 IP가 제외된 주소이므로(uploads/xxxx.jpg) 바로 사용 불가.
                        //P_image = "http://heremong.dothome.co.kr/"+P_image;
                        //String P_image = "http://localhost/univ/displayFile?fileName=6d4408e3-9f4a-4ce8-a224-8b101921e92c.jpg";

                        items.add(0,new Item(Place_id, P_name, P_address, P_image)); // 첫 번째 매개변수는 몇번째에 추가 될지, 제일 위에 오도록
                        adapter.notifyItemInserted(0);
                    }
                } catch (JSONException e) {e.printStackTrace();} //문제 생겨도 예외처리

            }
        }, new Response.ErrorListener() { //오류나면 error라고 토스트 메세지 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        })

       {
//           SearchRequest searchRequest = new SearchRequest(SB, responseListener);
//           RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//           queue.add(searchRequest);
//            SearchRequest searchRequest = new SearchRequest(SB,responseListener);
        };

//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//        queue.add(searchRequest);

            //실제 요청 작업을 수행해주는 요청큐 객체 생성
            RequestQueue requestQueue= Volley.newRequestQueue(this);

            //요청큐에 요청 객체 생성
        requestQueue.add(jsonArrayRequest);

//        SearchRequest searchRequest = new SearchRequest(SB,responseListener);
//        RequestQueue queue2 = Volley.newRequestQueue(this);
//        queue2.add(searchRequest);



    }
}