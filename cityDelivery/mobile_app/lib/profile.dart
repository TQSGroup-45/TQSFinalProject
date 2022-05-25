import 'package:flutter/material.dart';

class ProfileView extends StatelessWidget {
  const ProfileView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Profile'),
        centerTitle: true,
      ),
        body: Padding(
            padding: const EdgeInsets.symmetric(vertical: 60),
            child: Column (
              children: const [
                Center(
                    child: Icon(
                      Icons.person,
                      color: Colors.deepPurple,
                      size: 64.0,
                    )
                ),
              ],
            )
        )
    );
  }

}