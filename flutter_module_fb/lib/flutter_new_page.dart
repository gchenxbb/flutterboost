import 'package:flutter/material.dart';

class NewPageWidget extends StatelessWidget {
  NewPageWidget({this.params, this.message});

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
              'Flutter Page:NewPage',
              style: TextStyle(fontSize: 28.0, color: Colors.blue),
            ),
          ],
        ),
      ),
    );
  }
}
