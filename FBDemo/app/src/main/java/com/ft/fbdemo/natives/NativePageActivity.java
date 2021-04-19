package com.ft.fbdemo.natives;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ft.fbdemo.PageRouter;
import com.ft.fbdemo.R;

import java.util.HashMap;
import java.util.Map;

public class NativePageActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "NativePageActivity";

    private TextView open_url;
    private TextView mOpenNative;
    private TextView mOpenFlutter;

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

        mOpenNative.setOnClickListener(this);
        mOpenFlutter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Map<String, String> params = new HashMap<>();
        params.put("native_params1", "native_params_a001");
        params.put("native_params2", "native_params_a002");

        if (v == mOpenNative) {
            PageRouter.openPageByUrl(this, PageRouter.NATIVE_PAGE_ONE, params);
        } else if (v == mOpenFlutter) {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_PAGE_TWO, params);
        }
    }
}