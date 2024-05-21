import 'package:flutter/material.dart';
import 'package:contacts_permission_handler/contacts_permission_handler.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final plugin = ContactsPermissionHandler();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('Plugin example app'),
          ),
          body: Container(
            constraints: const BoxConstraints.expand(),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TextButton(
                  onPressed: () async {
                    final status = await plugin.checkStatus();
                    print(status);
                  },
                  child: const Text("Check Status"),
                ),
                const SizedBox(height: 40),
                TextButton(
                  onPressed: () async {
                    final result = await plugin.requestAccess();
                    print(result);
                  },
                  child: const Text("Request Permission"),
                ),
              ],
            ),
          )),
    );
  }
}
