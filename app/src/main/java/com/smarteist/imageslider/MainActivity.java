package com.smarteist.imageslider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.imageslider.Adapter.Adapter;
import com.smarteist.imageslider.Data.Api.Api;
import com.smarteist.imageslider.Data.Api.VolleyCallBack;
import com.smarteist.imageslider.Data.ApiModel.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item>itemList=new ArrayList<>();
    TabLayout tabLayout;
    Adapter adapter;
    private Parcelable recyclerViewState;
   SliderView sliderView;
   int[]images={R.drawable.ptwo,
           R.drawable.p1,
           R.drawable.pthree};
   RecyclerView recyclerView;

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tabLayout=findViewById(R.id.tab_layout);
        recyclerView=findViewById(R.id.rectclerviewimg);


        sliderView = findViewById(R.id.imageSlider);

        tabLayout.setSelectedTabIndicatorColor(R.drawable.indicator);


        itemList=new ArrayList<>();
        Slideradapter slideradapter = new Slideradapter(images);
        sliderView.setSliderAdapter(slideradapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycle(true);
        sliderView.setScrollTimeInSec(4);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.rewards);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:

                        return true;
                }
                return false;
            }
        });

        Api api=new Api(MainActivity.this);
        final ArrayList<Item> list = new ArrayList<>();
        api.getValue(new VolleyCallBack() {
          @Override
          public void onSuccess(JSONArray response) {
              for (int i=0;i<response.length();i++){
                  try {
                      JSONObject jsonObject= response.getJSONObject(i);
                    Item item=new Item();
                    item.setId(jsonObject.getString("id").toString());
                    item.setTitle(jsonObject.getString("title").toString());
                    item.setExpire_date(jsonObject.getString("expire_date").toString());
                    item.setSub_title(jsonObject.getString("sub_title").toString());
                    item.setImage(jsonObject.getString("image").toString());
                    item.setIs_completed(jsonObject.getBoolean("is_completed"));
                      itemList.add(item);

                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
              LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
              recyclerView.setLayoutManager(layoutManager);
              adapter=new Adapter(getApplicationContext(),itemList);
              recyclerView.setAdapter(adapter);
              recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
              recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
              recyclerView.smoothScrollToPosition(0);
              recyclerView.post(new Runnable()
              {
                  @Override
                  public void run() {
                      adapter.notifyDataSetChanged();
                  }
              });

          }
      });

    }

    @Override
    protected void onStart() {
        super.onStart();
        sliderView.startAutoCycle();
    }
}
