package com.example.pizzalovers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzalovers.Models.Pizza;
import com.example.pizzalovers.R;
import com.example.pizzalovers.adapters.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    private final String JSON_URL = "http://192.168.2.4:8080/demo/all" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Pizza> lstPizza ;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lstPizza= new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();

    }
    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Pizza pizza= new Pizza() ;
                       pizza. setPizzaId(jsonObject.getInt("pizzaId"));
                       pizza.setName(jsonObject.getString("name"));
                       pizza.setDescription(jsonObject.getString("description"));
                       pizza.setPrice(jsonObject.getInt("price"));
                       pizza.setImageUrl(jsonObject.getString("imageUrl"));
                        lstPizza.add(pizza);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstPizza);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(Menu.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Pizza> lstPizza) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstPizza) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }


}



