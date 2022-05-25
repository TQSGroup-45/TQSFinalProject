

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mobile_app/authentication/register.dart';

import '../main.dart';

class LoginView extends StatelessWidget {
  const LoginView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Login'),
          elevation: 0,
          backgroundColor: Colors.white,
          foregroundColor: Colors.deepPurple,
          centerTitle: true,
        ),
        body: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget> [
              const Center(
                child:
                Icon(
                  Icons.person,
                  size: 72,
                  color: Colors.deepPurple,
                ),
              ),

              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 60, vertical: 16),
                child: TextFormField(
                  decoration: const InputDecoration(
                    border: UnderlineInputBorder(),
                    labelText: 'Enter your email',
                  ),
                ),
              ),
              const SizedBox(
                height: 12,
              ),

              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 60),
                child: TextFormField(
                  decoration: const InputDecoration(
                    border: UnderlineInputBorder(),
                    labelText: 'Enter your password',
                  ),
                  obscureText: true,
                  enableSuggestions: false,
                  autocorrect: false,
                ),
              ),
              const SizedBox(
                height: 32,
              ),
              SizedBox(
                width: 150,
                child: ElevatedButton(
                    style: ButtonStyle(
                        padding: MaterialStateProperty.all<EdgeInsets>(
                            const EdgeInsets.symmetric(vertical: 18)),
                        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                            RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(32.0)
                            )
                        )
                    ),

                    onPressed: () {
                      Navigator.pushAndRemoveUntil(
                        context,
                        MaterialPageRoute(builder: (context) => const MyStatefulWidget()),
                            (Route<dynamic> route) => false,
                      );
                    },
                    child: const Text(
                      'LOGIN',
                      style: TextStyle(
                          fontSize: 18
                      ),
                    )
                ),
              ),
              const SizedBox(
                height: 12,
              ),
              SizedBox(
                width: 150,
                child: OutlinedButton(
                    style: ButtonStyle(
                        padding: MaterialStateProperty.all<EdgeInsets>(
                            const EdgeInsets.symmetric(vertical: 18)),
                        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                            RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(32.0)
                            )
                        )
                    ),
                    onPressed: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => const RegisterView())
                      );
                    },
                    child: const Text(
                      'REGISTER',
                      style: TextStyle(
                          fontSize: 18
                      ),
                    )
                ),
              ),
            ]
        )
    );
  }
}