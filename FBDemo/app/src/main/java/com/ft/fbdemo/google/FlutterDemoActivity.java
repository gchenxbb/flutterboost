package com.ft.fbdemo.google;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.flutter.facade.Flutter;
import io.flutter.plugin.common.*;
import io.flutter.view.*;


public class FlutterDemoActivity extends AppCompatActivity {

    public static final String CHANNEL_NAME = "com.flutterbus/demo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 根据指定routeName创建FlutterView用来展示对应dart中的Widget
        FlutterView flutterView = Flutter.createView(this, this.getLifecycle(), "");

        // 创建Platform Channel用来和Flutter层进行交互
        new MethodChannel(flutterView, CHANNEL_NAME).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
//                methodCall(methodCall, result);
            }
        });
        setContentView(flutterView);
    }

}


