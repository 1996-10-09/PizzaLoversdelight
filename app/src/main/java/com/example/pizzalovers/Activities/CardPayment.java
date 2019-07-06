package com.example.pizzalovers.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;
import com.example.pizzalovers.R;

public class CardPayment extends AppCompatActivity {
    CardForm cardForm;
    Button buy;
    Button location;
    AlertDialog.Builder alertbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);
        location = findViewById(R.id.btn_location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardPayment.this,"Enter your location for delivery",Toast.LENGTH_SHORT).show();
                Intent payInintent= new Intent(CardPayment.this,GooogleMapsActivity.class);
                startActivity(payInintent);
            }
        });

        cardForm= findViewById(R.id.paymentcard);
        buy = findViewById(R.id.btn_buy);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS required on this number")
                .setup(CardPayment.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardForm.isValid()){
                    alertbuilder= new AlertDialog.Builder(CardPayment.this);
                    alertbuilder.setTitle("Confirm before purchase");
                    alertbuilder.setMessage("Card Number:"+ cardForm.getCardNumber()+ "\n" + "Card Expiry Date:" +
                            cardForm.getExpirationDateEditText().getText().toString()+"\n" + "Card CVV:" + cardForm.getCvv()+
                            "\n" + "Postal Code:" + cardForm.getPostalCode() + "\n" + "Phone Number: " + cardForm.getMobileNumber());
                    alertbuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(CardPayment.this,"Thank you for purchace",Toast.LENGTH_LONG).show();

                        }
                    });
                    alertbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertbuilder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(CardPayment.this,"Please Complete the form",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}