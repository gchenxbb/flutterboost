package com.ft.fbdemo;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.ft.fbdemo.boost.TextPlatformViewFactory;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.Utils;
import com.idlefish.flutterboost.interfaces.INativeRouter;

import io.flutter.plugin.common.MethodChannel;

import java.util.Map;

import io.flutter.embedding.android.FlutterView;
import io.flutter.plugin.common.StandardMessageCodec;

/**
 * Application
 */
public class FbApplication extends Application {
    public static final String TAG = "FbApplication";

    public void onCreate() {
        super.onCreate();

        INativeRouter router = new INativeRouter() {
            @Override
            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
                String assembleUrl = Utils.assembleUrl(url, urlParams);
                PageRouter.openPageByUrl(context, assembleUrl, urlParams);
            }

        };
        FlutterBoost.BoostLifecycleListener boostLifecycleListener = new FlutterBoost.BoostLifecycleListener() {
            @Override
            public void beforeCreateEngine() {

            }

            @Override
            public void onEngineCreated() {
                // 注册MethodChannel，监听flutter侧的getPlatformVersion调用
                MethodChannel methodChannel = new MethodChannel(FlutterBoost.instance().engineProvider().getDartExecutor(), "flutter_native_channel");
                methodChannel.setMethodCallHandler((call, result) -> {

                    if (call.method.equals("getPlatformVersion")) {
                        result.success(Build.VERSION.RELEASE);
                    } else {
                        result.notImplemented();
                    }

                });

                // 注册PlatformView viewTypeId要和flutter中的viewType对应
                FlutterBoost
                        .instance()
                        .engineProvider()
                        .getPlatformViewsController()
                        .getRegistry()
                        .registerViewFactory("plugins.test/view", new TextPlatformViewFactory(StandardMessageCodec.INSTANCE));

            }

            @Override
            public void onPluginsRegistered() {

            }

            @Override
            public void onEngineDestroy() {

            }
        };

        Platform platform = new FlutterBoost.ConfigBuilder(this, router)
                .isDebug(true)
                .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build();

        FlutterBoost.instance().init(platform);
    }

}
