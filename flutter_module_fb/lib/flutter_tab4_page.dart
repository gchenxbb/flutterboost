import 'package:flutter/material.dart';

class Tab4Page extends StatelessWidget {
  Tab4Page({this.params, this.message});

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
              'Flutter Page::Tab4',
              style: TextStyle(fontSize: 28.0, color: Colors.blue),
            ),
          ],
        ),
      ),
    );
  }
}
