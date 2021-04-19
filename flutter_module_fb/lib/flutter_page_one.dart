import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

class OnePageWidget extends StatelessWidget {
  OnePageWidget({this.params, this.message});

  final Map params;
  final String message;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Flutter Page::OnePage',
              style: TextStyle(fontSize: 28.0, color: Colors.blue),
            ),
            Text(
              "This message is,${this.message}",
              style: TextStyle(fontSize: 16.0, color: Colors.red),
            ),
            Text(
              "This params is,${this.params}",
              style: TextStyle(fontSize: 16.0, color: Colors.red),
            ),
            RaisedButton(
              onPressed: _onOpenNativePage,
              child: new Text('点击去原生页面x1'),
              color: Colors.blue,
            ),
            RaisedButton(
              onPressed: _onNewFlutterPag,
              child: new Text('新开一个flutter页面x1'),
              color: Colors.blue,
            ),
          ],
        ),
      ),
    );
  }

  //flutter跳转naitve页面
  void _onOpenNativePage() {
    FlutterBoost.singleton.open("flutter://native_page_one", urlParams: {
      "query": {"param1": "aa1", "param2": "aa2", "param3": "aa3"}
    }).then((Map value) {
      //当结束该页面调用
      print(
          "call me when page is finished. did recieve second route result $value");
    });
  }

  //flutter新开一个flutter页面
  void _onNewFlutterPag() {
    FlutterBoost.singleton
        .open("flutter://flutter_new_page_from_flutter", urlParams: {
      "query": {"param4": "bbb4", "param5": "bbb5", "param6": "bbb6"}
    }).then((Map value) {
      print(
          "call me when page is finished. did recieve second route result $value");
    });
  }
}
