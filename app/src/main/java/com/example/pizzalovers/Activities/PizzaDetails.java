package com.example.pizzalovers.Activities;


import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pizzalovers.R;


public class PizzaDetails extends AppCompatActivity {
    Button customize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);
        customize=(Button) findViewById(R.id.customize);



        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cardIntent = new Intent(PizzaDetails.this, Payment.class);
                startActivity(cardIntent);

            }
        });

       // getSupportActionBar().hide();


        String pizzaname  = getIntent().getExtras().getString("name");
        String pizzadescription = getIntent().getExtras().getString("description");
        int pizzaId = getIntent().getExtras().getInt("pizzaId");
        int pizzaPrice = getIntent().getExtras().getInt("price") ;
        String pizzaimage_Url = getIntent().getExtras().getString("imageUrl") ;



        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView pizza_name = findViewById(R.id.aa_pizza_name);

        TextView pizza_id= findViewById(R.id.aa_Pizza_Id) ;
        TextView pizza_description = findViewById(R.id.aa_description);
        TextView pizza_price  = findViewById(R.id.aa_price) ;
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        pizza_name.setText(pizzaname);
        pizza_id.setText(String.valueOf(pizzaId));
        pizza_description.setText(pizzadescription);
        pizza_price.setText(String.valueOf(pizzaPrice));



        collapsingToolbarLayout.setTitle(pizzaname);


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(pizzaimage_Url).apply(requestOptions).into(img);




    }


}
