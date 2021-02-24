import 'package:flutter/material.dart';

class TwoPageWidget extends StatelessWidget {
  TwoPageWidget({this.params, this.message});

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
              'Flutter Page::TwoPage',
              style: TextStyle(fontSize: 28.0, color: Colors.blue),
            ),
            Text(
              "This message is,${this.message}",
              style: TextStyle(fontSize: 16.0, color: Colors.deepOrangeAccent),
            ),
            Text(
              "This params is,${this.params}",
              style: TextStyle(fontSize: 16.0, color: Colors.deepOrangeAccent),
            ),
            RaisedButton(
              onPressed: _onPressBtn,
              child: new Text('点击去原生页面x2'),
              color: Colors.blue,
            )
          ],
        ),
      ),
    );
  }

  void _onPressBtn() {
//    FlutterBoost.singleton.open("flutterbus://native_second_page", {},{});
  }
}