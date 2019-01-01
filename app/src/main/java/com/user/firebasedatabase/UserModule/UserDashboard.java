package com.user.firebasedatabase.UserModule;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.user.firebasedatabase.Adapter.Producer.ProducerViewDetails;
import com.user.firebasedatabase.Pojo.User;
import com.user.firebasedatabase.Profile;
import com.user.firebasedatabase.R;
import com.user.firebasedatabase.admin.AddAdvisor;
import com.user.firebasedatabase.admin.AdminDashboard;
import com.user.firebasedatabase.admin.ViewAdvisor;
import com.user.firebasedatabase.admin.ViewCropDetails;


public class UserDashboard extends AppCompatActivity {

    private Menu menu;

    CardView argodetails,consumer,producerview,weather,producer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        argodetails=findViewById(R.id.agrodetails);

        consumer=findViewById(R.id.consumer);
        weather=findViewById(R.id.weather);

        producerview=findViewById(R.id.producerview);


        producerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(UserDashboard.this,com.user.firebasedatabase.UserModule.ProducerViewDetails.class);
                i.putExtra("type","User");
                startActivity(i);

                   }
        });
        producer=findViewById(R.id.producer);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(UserDashboard.this, com.user.firebasedatabase.UserModule.Weather.MainActivity.class));
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboard.this,Profile.class));

            }
        });

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });

        argodetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(UserDashboard.this,ViewAdvisor.class);
                i.putExtra("type","Admin");
                startActivity(i);
            }
        });

        consumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserDashboard.this,com.user.firebasedatabase.UserModule.ProducerViewDetails.class);
                i.putExtra("type","consumer");
                startActivity(i);
            }
        });
        producer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(UserDashboard.this,ViewCropDetails.class);
                i.putExtra("type","NoAction");
                startActivity(i);
                  //startActivity(new Intent(UserDashboard.this, ProducerAdd.class));
            }
        });

    }  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }
}
