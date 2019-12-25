import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_boost_module/flutter_page_one.dart';
import 'package:flutter_boost_module/flutter_page_two.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  MyApp({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    //注册 native传递的打开flutter页面的key和页面的关系。
    FlutterBoost.singleton.registerPageBuilders({
      'first_boost': (pageName, params, _) => TwoPageWidget(
            params: params,
            message: pageName,
          ),
      'second_boost': (pageName, params, _) => TwoPageWidget(
            params: params,
            message: pageName,
          ),
      'tab_boost': (pageName, params, _) => TwoPageWidget(
            params: params,
            message: pageName,
          ),
      'flutterFragment_boost': (pageName, params, _) => TwoPageWidget(
            params: params,
            message: pageName,
          ),

      //可以在native层通过 getContainerParams 来传递参数
      'flutter_one_page_boost': (pageName, params, _) {
        print("flutter层：收到native参数params:$params");
        return OnePageWidget(
          params: params,
          message: pageName,
        );
      },
      'flutter_two_page_boost': (pageName, params, _) {
        print("flutter层：收到native参数params:$params");
        return TwoPageWidget(
          params: params,
          message: pageName,
        );
      },
      'flutter_three_page_boost': (pageName, params, _) => TwoPageWidget(
            params: params,
            message: pageName,
          ),
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      builder: FlutterBoost.init(postPush: _onRoutePushed),
      home: Container(),
    );
  }

  void _onRoutePushed(
      String pageName, String uniqueId, Map params, Route route, Future _) {}
}
