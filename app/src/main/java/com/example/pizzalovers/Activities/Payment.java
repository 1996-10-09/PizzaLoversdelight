package com.example.pizzalovers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzalovers.R;

import java.util.HashMap;
import java.util.Map;


public class Payment extends AppCompatActivity  {

    EditText nameText = null;
    EditText priceText = null;
    TextView textView;
    Button payment;
    Button cash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        nameText = findViewById(R.id.paymentName);
        priceText = findViewById(R.id.paymentprice);
        payment=findViewById(R.id.cardpayment);
        cash=findViewById(R.id.cash);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payintent= new Intent(Payment.this,GooogleMapsActivity.class);
                startActivity(payintent);
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payintent= new Intent(Payment.this,CardPayment.class);
                startActivity(payintent);
            }
        });
    }
    protected void payment(View view) {
        String  pizzaName = nameText.getText().toString().trim();
        String pizzaPrice = priceText.getText().toString().trim();
        String url = "http://192.168.2.4:8080/demoOne/add?name="+ pizzaName+"&price="+pizzaPrice;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(stringRequest);
    }

    class HTTPResponseListner implements Response.Listener<String>{
        @Override
        public void onResponse(String response) {
            textView.setText(response);
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            textView.setText(error.getMessage());
        }
    }


}