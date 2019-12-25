package com.ft.fbdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ft.fbdemo.boostflutters.FlutterFragmentPageActivity;
import com.ft.fbdemo.natives.NativePageActivity;
import com.ft.fbdemo.natives.NativePageTwoActivity;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Map;

public class PageRouter {
    //进入flutter
    public static String FLUTTER_PAGE_ONE = "flutter://flutter_page_one";
    public static String FLUTTER_PAGE_TWO = "flutter://flutter_page_two";
    public static String FLUTTER_PAGE_THREE_FROM_FLUTTER = "flutter://flutter_page_three_from_flutter";

    //进入native
    public static String NATIVE_PAGE_ONE = "flutter://native_page_one";
    public static String NATIVE_PAGE_TWO = "flutter://native_page_two";


    public final static Map<String, String> pageName = new HashMap<>();

    static {
        pageName.put(FLUTTER_PAGE_ONE, "flutter_one_page_boost");
        pageName.put(FLUTTER_PAGE_TWO, "flutter_two_page_boost");
        pageName.put(FLUTTER_PAGE_THREE_FROM_FLUTTER, "flutter_three_page_boost");

        pageName.put("first", "first_boost");
        pageName.put("second", "second_boost");
        pageName.put("tab", "tab_boost");
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
            } else if (url.startsWith(FLUTTER_PAGE_ONE)) {//跳转flutter
                context.startActivity(new Intent(context, FlutterFragmentPageActivity.class));
                return true;
            } else if (url.startsWith(NATIVE_PAGE_ONE)) {//跳转native1
                Intent intent = new Intent(context, NativePageActivity.class);
                intent.putExtra("url", url);
                //解析参数
                context.startActivity(intent);
                return true;
            } else if (url.startsWith(NATIVE_PAGE_TWO)) {//跳转native2
                Intent intent = new Intent(context, NativePageTwoActivity.class);
                intent.putExtra("url", url);
                context.startActivity(intent);
                return true;
            }

            return false;

        } catch (Throwable t) {
            return false;
        }
    }

//    public static boolean openPageUrl(Context context, String url, int requestCode) {
//        try {
//            if (url.startsWith(NATIVE_FIRST_PAGE)) {
//                Intent intent = new Intent(context, NativeFirstPageActivity.class);
//                intent.putExtra("url", url);
//                context.startActivity(intent);
//                return true;
//            } else if (url.startsWith(NATIVE_SECOND_PAGE)) {
//                Intent intent = new Intent(context, NativeSecondPageActivity.class);
//                intent.putExtra("url", url);
//                ((Activity) context).startActivityForResult(intent, requestCode);
//                return true;
//            } else if (url.startsWith(FLUTTER_FIRST_PAGE)) {
//                Intent intent = new Intent(context, FlutterFirstPageActivity.class);
//                intent.putExtra("url", url);
//                ((Activity) context).startActivityForResult(intent, requestCode);
//                return true;
//
//            } else if (url.startsWith(FLUTTER_SECOND_PAGE)) {
//                Intent intent = new Intent(context, FlutterSecondPageActivity.class);
//                intent.putExtra("url", url);
//                ((Activity) context).startActivityForResult(intent, requestCode);
//                return true;
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//
//    }
}
