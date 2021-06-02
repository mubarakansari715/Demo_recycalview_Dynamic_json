package com.example.profile_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

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



    }

    private void parseJson() {
        String stringurl = "https://reqres.in/api/users?page=2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for(int i=1; i<jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                String  cid = data.getString("id");
                                String email=data.getString("email");
                                String first_name= data.getString("first_name");
                                String last_name= data.getString("last_name");
                                String avatar =data.getString("avatar");

                                myarrayList.add(new javabeen(cid,email,first_name,last_name,avatar));
                            }
                            demoAdapter =new DemoAdapter(myarrayList, MainActivity.this);
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
        requestQueue.add(request);

    }

}











