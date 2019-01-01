package com.user.firebasedatabase.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.user.firebasedatabase.Pojo.AddCropPojo;
import com.user.firebasedatabase.Pojo.AdvisorPojo;
import com.user.firebasedatabase.R;

public class AddAdvisor extends AppCompatActivity {

    Button submit;
    MaterialEditText advisorname, mobileno, address;
    String txt_key;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ArgoAdvisor");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_advisor);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        advisorname = findViewById(R.id.advisorname);
        mobileno = findViewById(R.id.mobileno);
        address = findViewById(R.id.address);
        submit = findViewById(R.id.submit);


        if (getIntent().getStringExtra("name") != null) {
            String txt_cropname = getIntent().getStringExtra("name");
            String txt_marketprice = getIntent().getStringExtra("address");
            String txt_scheduleprice = getIntent().getStringExtra("mobileno");

            txt_key = getIntent().getStringExtra("key");
            advisorname.setText(txt_cropname);
            mobileno.setText(txt_scheduleprice);
            address.setText(txt_marketprice);
            submit.setText("Update");

        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(advisorname.getText().toString())) {

                    Toast.makeText(getApplicationContext(), "Enter Advisor Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobileno.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter MobileNo", Toast.LENGTH_SHORT).show();


                    return;
                }
                if (TextUtils.isEmpty(address.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter Address Name", Toast.LENGTH_SHORT).show();


                    return;
                }


                if (submit.getText().toString().equalsIgnoreCase("Update")) {

                    AdvisorPojo advisorPojo = new AdvisorPojo(advisorname.getText().toString(), mobileno.getText().toString(), address.getText().toString());

                    myRef.child(txt_key).setValue(advisorPojo);
                    startActivity(new Intent(AddAdvisor.this, ViewAdvisor.class));
                    finish();


                } else {

                    AdvisorPojo advisorPojo = new AdvisorPojo(advisorname.getText().toString(), mobileno.getText().toString(), address.getText().toString());
                    myRef.push().setValue(advisorPojo);

                    startActivity(new Intent(AddAdvisor.this, ViewAdvisor.class));
                    finish();

                }


            }
        });


    }
}
