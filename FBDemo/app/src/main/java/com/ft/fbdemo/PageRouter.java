package com.ft.fbdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ft.fbdemo.natives.NativePageActivity;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Map;

public class PageRouter {
    public final static Map<String, String> pageName = new HashMap<>();

    //进入flutter
    public static String FLUTTER_PAGE_ONE = "flutter://flutter_page_one";
    public static String FLUTTER_PAGE_TWO = "flutter://flutter_page_two";
    public static String FLUTTER_NEW_PAGE_FROM_FLUTTER = "flutter://flutter_new_page_from_flutter";

    //进入native
    public static String NATIVE_PAGE_ONE = "flutter://native_page_one";

    static {
        pageName.put(FLUTTER_PAGE_ONE, "flutter_one_page_boost");
        pageName.put(FLUTTER_PAGE_TWO, "flutter_two_page_boost");
        pageName.put(FLUTTER_NEW_PAGE_FROM_FLUTTER, "flutter_new_page");
    }

    public static boolean openPageByUrl(Context context, String url, Map params) {
        return openPageByUrl(context, url, params, 0);
    }

    public static boolean openPageByUrl(Context context, String url, Map params, int requestCode) {
        String path = url.split("\\?")[0];
        Log.d("openPageByUrl", "url:" + url + " path:" + path);
        try {
            if (pageName.containsKey(path)) {
                //进入flutter，BoostFlutterActivity组件
                Intent intent = BoostFlutterActivity.withNewEngine().url(pageName.get(path)).params(params)
                        .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque).build(context);
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, requestCode);
                } else {
                    context.startActivity(intent);
                }
                return true;
            } else if (url.startsWith(NATIVE_PAGE_ONE)) {//跳转native1，flutter层代码来到这里
                Intent intent = new Intent(context, NativePageActivity.class);
                intent.putExtra("url", url);
                context.startActivity(intent);
                return true;
            }
            return false;

        } catch (Throwable t) {
            return false;
        }
    }
}
