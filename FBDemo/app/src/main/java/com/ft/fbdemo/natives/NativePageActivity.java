package com.ft.fbdemo.natives;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ft.fbdemo.PageRouter;
import com.ft.fbdemo.R;

import java.util.HashMap;
import java.util.Map;

public class NativePageActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "NativePageActivity";

    private TextView open_url;
    private TextView mOpenNative;
    private TextView mOpenFlutter;
    private TextView mOpenFlutterFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.native_page);
        open_url = findViewById(R.id.open_url);

        if (getIntent() != null) {
            String url = getIntent().getStringExtra("url");
            if (url == null) {
                url = "";
            }
            open_url.setText("进入native page url：\n" + url);
            Log.d(TAG, "进入native page url：" + url);
        }

        mOpenNative = findViewById(R.id.open_native);
        mOpenFlutter = findViewById(R.id.open_flutter);
        mOpenFlutterFragment = findViewById(R.id.open_flutter_fragment);

        mOpenNative.setOnClickListener(this);
        mOpenFlutter.setOnClickListener(this);
        mOpenFlutterFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Map<String, String> params = new HashMap<>();
        params.put("native_params1", "native_params_a001");
        params.put("native_params2", "native_params_a002");

        if (v == mOpenNative) {
            PageRouter.openPageByUrl(this, PageRouter.NATIVE_PAGE_ONE, params);
        } else if (v == mOpenFlutter) {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_PAGE_ONE, params);
        } else if (v == mOpenFlutterFragment) {
//            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_FRAGMENT_PAGE_URL,params);
        }
    }
}