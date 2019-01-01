package com.user.firebasedatabase.UserModule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.user.firebasedatabase.Common.Common;
import com.user.firebasedatabase.Pojo.AddCropPojo;
import com.user.firebasedatabase.Pojo.ProductAddPojo;
import com.user.firebasedatabase.R;
import com.user.firebasedatabase.admin.AddCrop;
import com.user.firebasedatabase.admin.ViewCropDetails;

public class ProducerAdd extends AppCompatActivity {
    MaterialEditText cropname, marketprice, scheduleprice, quantity, totalamount;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ProductDetails");
    Button submit;
    String delivery, image, txt_key;
    Switch deliveryoption;
    String toggle = "true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer_add);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        cropname = findViewById(R.id.cropname);
        deliveryoption = findViewById(R.id.deliveryoption);
        marketprice = findViewById(R.id.marketprice);
        scheduleprice = findViewById(R.id.scheduleprice);
        quantity = findViewById(R.id.quantity);
        totalamount = findViewById(R.id.totalamount);
        submit = findViewById(R.id.submit);


        deliveryoption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                deliveryoption.setChecked(isChecked);

                if (isChecked) {
                    toggle = "true";
                } else toggle = "false";

            }
        });

        if (toggle.equalsIgnoreCase("true")) {
            deliveryoption.setChecked(true);
            toggle = "true";
        } else {
            deliveryoption.setChecked(false);
            toggle = "false";
        }


        Intent intent = getIntent();
        if (intent != null) {
            String txt_cropname = intent.getStringExtra("cropname");
            String txt_marketprice = intent.getStringExtra("marketprice");
            String txt_scheduleprice = intent.getStringExtra("scheduleprice");
            image = intent.getStringExtra("image");
            delivery = intent.getStringExtra("delivery");


            txt_key = intent.getStringExtra("key");
            cropname.setText(txt_cropname);
            scheduleprice.setText(txt_scheduleprice);
            marketprice.setText(txt_marketprice);

        }


        if (getIntent().getStringExtra("total") != null) {
            String txt_cropname = intent.getStringExtra("cropname");
            String txt_marketprice = intent.getStringExtra("marketprice");
            String txt_scheduleprice = intent.getStringExtra("scheduleprice");

            String total_txt = intent.getStringExtra("total");
            String txt_quantity = intent.getStringExtra("quantity");


            image = intent.getStringExtra("image");
            delivery = intent.getStringExtra("delivery");

            toggle = intent.getStringExtra("toggle");

            txt_key = intent.getStringExtra("key");
            cropname.setText(txt_cropname);
            scheduleprice.setText(txt_scheduleprice);
            marketprice.setText(txt_marketprice);
            quantity.setText(txt_quantity);
            totalamount.setText(total_txt);
            submit.setText("Update");
        }


//        i.putExtra("key", cropLists.get(position).getCropKey().get(0));

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {


                    double total = Double.parseDouble(String.valueOf(s.toString())) * Double.parseDouble(String.valueOf(scheduleprice.getText().toString()));
                    totalamount.setText("" + total);
                } else totalamount.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(cropname.getText().toString())) {

                    Toast.makeText(getApplicationContext(), "Enter cropname", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(scheduleprice.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter scheduleprice", Toast.LENGTH_SHORT).show();


                    return;
                }
                if (TextUtils.isEmpty(marketprice.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter marketprice", Toast.LENGTH_SHORT).show();


                    return;
                }
                if (TextUtils.isEmpty(quantity.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter quantity", Toast.LENGTH_SHORT).show();


                    return;
                }
                if (submit.getText().toString().equalsIgnoreCase("Update")) {

                    ProductAddPojo cropPojo = new ProductAddPojo(cropname.getText().toString(), marketprice.getText().toString(), scheduleprice.getText().toString(), quantity.getText().toString(), totalamount.getText().toString(), Common.mobileno, Common.Username, image, txt_key,toggle);
                    myRef.child(txt_key).setValue(cropPojo);
                   // startActivity(new Intent(ProducerAdd.this, ProducerViewDetails.class));
                    Intent i=new Intent(ProducerAdd.this,ProducerViewDetails.class);
                    i.putExtra("type","User");
                    startActivity(i);


                    finish();
                } else {
                    ProductAddPojo cropPojo = new ProductAddPojo(cropname.getText().toString(), marketprice.getText().toString(), scheduleprice.getText().toString(), quantity.getText().toString(), totalamount.getText().toString(), Common.mobileno, Common.Username, image, txt_key,toggle);
                    myRef.push().setValue(cropPojo);

                    Intent i=new Intent(ProducerAdd.this,ProducerViewDetails.class);
                    i.putExtra("type","User");
                    startActivity(i);

                   finish();
                }


            }
        });

    }
}
