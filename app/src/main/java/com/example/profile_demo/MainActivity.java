package com.example.profile_demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    DemoAdapter demoAdapter;
    RequestQueue requestQueue;
    String stringurl;
    ArrayList<javabeen> myarrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myarrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJson();

        HttpPOSTRequestWithArbitaryStringBody();


    }

    private void parseJson() {
        String stringurl = "https://reqres.in/api/users?page=2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringurl, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            //Log.e(TAG, "onResponse: @@@"+jsonArray );
                            for (int i = 1; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                String cid = data.getString("id");
                                String email = data.getString("email");
                                String first_name = data.getString("first_name");
                                String last_name = data.getString("last_name");
                                String avatar = data.getString("avatar");

                                myarrayList.add(new javabeen(cid, email, first_name, last_name, avatar));
                            }
                            demoAdapter = new DemoAdapter(myarrayList, MainActivity.this);
                            recyclerView.setAdapter(demoAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        request.getBody();

        requestQueue.add(request);


    }


    public void HttpPOSTRequestWithArbitaryStringBody() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "YOUR_URL";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("@@@Response success :", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            // this is the relevant method
            @Override
            public byte[] getBody() throws AuthFailureError {

                JSONArray addressDataArray = new JSONArray();
                JSONObject addressData = new JSONObject();
                try {
                    addressData.put("firstName", "Mubarak");
                    addressData.put("lastName", "Ansari");
                    addressData.put("mobileNumber", "1");


                    //addressDataArray.put(addressData); if need array : addressData=[{something data}]
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                String httpPostBody = "websiteId=1&storeId=1&customerToken=&addressId=&addressData=" + addressData;
                // usually you'd have a field with some values you'd want to escape, you need to do it yourself if overriding getBody. here's how you do it
                try {
                    httpPostBody = httpPostBody + "&randomFieldFilledWithAwkwardCharacters=" + URLEncoder.encode("{{%stuffToBe Escaped/", "UTF-8");
                    Log.e("@@@ERROR", "exception" + httpPostBody);

                } catch (UnsupportedEncodingException exception) {
                    Log.e("ERROR", "exception", exception);
                    // return null and don't pass any POST string if you encounter encoding error
                    return null;
                }
                return httpPostBody.getBytes();
            }
        };
        queue.add(postRequest);
    }

}











