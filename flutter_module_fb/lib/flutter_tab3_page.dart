import 'package:flutter/material.dart';

class Tab3Page extends StatelessWidget {
  Tab3Page({this.params, this.message});

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
              'Flutter Page::Tab3',
              style: TextStyle(fontSize: 28.0, color: Colors.blue),
            ),
          ],
        ),
      ),
    );
  }
}
