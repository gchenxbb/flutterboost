package com.ft.fbdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ft.fbdemo.boostflutters.FlutterFragmentPageActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static WeakReference<MainActivity> sRef;

    private Button btnBoostgoFlutterOne;
    private Button btnBoostgoFlutterTwo;
//    private Button btnBoostTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //flutterboost方案
        btnBoostgoFlutterOne = findViewById(R.id.boost_go_flutter_one);
        btnBoostgoFlutterTwo = findViewById(R.id.boost_go_fragment);
//        btnBoostTwo = findViewById(R.id.boost_go_native_two);
        btnBoostgoFlutterOne.setOnClickListener(this);
        btnBoostgoFlutterTwo.setOnClickListener(this);
//        btnBoostTwo.setOnClickListener(this);
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
            case R.id.boost_go_fragment:
                startActivity(new Intent(MainActivity.this, FlutterFragmentPageActivity.class));
                break;
        }

    }
}
