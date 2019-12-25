package com.ft.fbdemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ft.fbdemo.google.FlutterDemoActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static WeakReference<MainActivity> sRef;

    private Button btnOne;
    private Button btnTwo;
    private Button btnBoostOne;
    private Button btnBoostgoFlutterTwo;
    private Button btnBoostTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //google提供接入方案
        btnOne = findViewById(R.id.tv_go_flutter_one);
        btnTwo = findViewById(R.id.tv_go_flutter_two);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);

        //flutterboost方案
        btnBoostOne = findViewById(R.id.boost_go_flutter_one);
        btnBoostgoFlutterTwo = findViewById(R.id.boost_go_flutter_two);
        btnBoostTwo = findViewById(R.id.boost_go_native_two);
        btnBoostOne.setOnClickListener(this);
        btnBoostgoFlutterTwo.setOnClickListener(this);
        btnBoostTwo.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sRef.clear();
        sRef = null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.boost_go_flutter_one:
                Map<String, String> params = new HashMap<>();
                params.put("name", "chenx");
                params.put("id", "3451");
                //跳转flutter页面
                PageRouter.openPageByUrl(MainActivity.this, PageRouter.FLUTTER_PAGE_ONE, params);
                break;
            case R.id.boost_go_flutter_two:
                Map<String, String> params2 = new HashMap<>();
                params2.put("name", "chenx2");
                params2.put("id", "3452");
                //跳转flutter页面
                PageRouter.openPageByUrl(MainActivity.this, PageRouter.FLUTTER_PAGE_TWO, params2);
                break;
            case R.id.boost_go_native_two:
                Map<String, String> params3 = new HashMap<>();
                params3.put("name", "chenx3");
                //跳转native页面
                PageRouter.openPageByUrl(MainActivity.this, PageRouter.NATIVE_PAGE_ONE, params3);
                break;
            case R.id.tv_go_flutter_one:
                Intent intent = new Intent(MainActivity.this, FlutterDemoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("routeName", "first");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_go_flutter_two:
                Intent customFlutter = FlutterActivity.withNewEngine()
                        .initialRoute("someOtherRoute")
                        .build(MainActivity.this);
                startActivity(customFlutter);
                break;
        }

    }
}
