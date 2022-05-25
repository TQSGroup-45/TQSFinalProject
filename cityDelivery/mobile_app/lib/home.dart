import 'package:flutter/material.dart';
import 'package:mobile_app/map.dart';

class HomeView extends StatelessWidget {
  const HomeView({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('CityDeliver'),
        ),
        body: ListView(
            children: <Widget>[
              Center(
                  child: Padding(
                      padding: const EdgeInsets.symmetric(vertical: 20.0),
                      child: Text(
                        'Delivery Requests',
                        style: TextStyle(
                            color: Colors.blueGrey[900],
                            fontWeight: FontWeight.w600,
                            fontSize: 25),
                      ))
              ),
              Card(
                  elevation: 3,
                  shape: const RoundedRectangleBorder(
                    borderRadius: BorderRadius.all(Radius.circular(12)),
                  ),
                  child: Padding(
                      padding: const EdgeInsets.symmetric(vertical: 20.0),
                      child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: <Widget>[
                            ListTile(
                              contentPadding: const EdgeInsets.symmetric(horizontal: 9.0),
                              title: ListTile(
                                  leading: Icon(
                                    Icons.location_on,
                                    color: Colors.blueGrey[800],
                                    size: 25.0,
                                  ),
                                  title: Text(
                                      'aDress',
                                      style: TextStyle(
                                          fontSize: 18,
                                          color: Colors.blueGrey[800],
                                          fontWeight: FontWeight.w500
                                      )
                                  ),
                                  trailing:
                                  Column(
                                    mainAxisAlignment: MainAxisAlignment.center,
                                    children: [
                                      Container(
                                        decoration: BoxDecoration(
                                          color: Colors.deepPurple[100],
                                          borderRadius: BorderRadius.circular(15),
                                        ),
                                        child: Padding (
                                            padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 2.0),
                                            child: RichText(
                                              text: TextSpan(
                                                style: TextStyle(
                                                    fontSize: 16,
                                                    color: Colors.deepPurple[900],
                                                    fontWeight: FontWeight.w600
                                                ),
                                                children: [
                                                  WidgetSpan(
                                                      child: Icon(
                                                          Icons.delivery_dining,
                                                          color: Colors.deepPurple[900],
                                                          size: 20
                                                      )),
                                                  const TextSpan(
                                                    text: " 1.4 km",
                                                  ),
                                                ],
                                              ),
                                            )
                                        ),
                                      ),
                                      const SizedBox(
                                        height: 8,
                                      ),
                                      Container(
                                        decoration: BoxDecoration(
                                          color: Colors.blueGrey[100],
                                          borderRadius: BorderRadius.circular(15),
                                        ),
                                        child: Padding (
                                            padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 2.0),
                                            child: RichText(
                                              text: TextSpan(
                                                style: TextStyle(
                                                    fontSize: 16,
                                                    color: Colors.blueGrey[800],
                                                    fontWeight: FontWeight.w600
                                                ),
                                                children: [
                                                  WidgetSpan(
                                                      child: Icon(
                                                          Icons.timer,
                                                          color: Colors.blueGrey[800],
                                                          size: 20
                                                      )),
                                                  const TextSpan(
                                                    text: " 15 min",
                                                  ),
                                                ],
                                              ),
                                            )
                                        ),
                                      ),
                                    ],
                                  ),
                              ),
                            ),
                            const SizedBox(
                              height: 20,
                            ),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: <Widget>[
                                TextButton(
                                  style: ButtonStyle(
                                      padding: MaterialStateProperty.all<EdgeInsets>(
                                          const EdgeInsets.all(15)
                                      )
                                  ),
                                  child: const Text('DECLINE'),
                                  onPressed: () {

                                  },
                                ),
                                ElevatedButton(
                                  style: ButtonStyle(
                                      padding: MaterialStateProperty.all<EdgeInsets>(
                                          const EdgeInsets.all(15)
                                      )
                                  ),
                                  child: const Text('ACCEPT DELIVERY'),
                                  onPressed: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(builder: (context) => const MapView())
                                    );
                                  },
                                ),
                              ],
                            )

                          ]
                      )
                  )
              ),
            ]
        )
    );
  }
}







        //       Center(
        //           child: ListTile(
        //             title: const ListTile(
        //               leading: Icon(
        //                 Icons.delivery_dining,
        //                 color: Colors.deepPurple,
        //                 size: 30.0,
        //               ),
        //               title: Text(
        //                   '1.2 km',
        //                   style: TextStyle(
        //                       fontSize: 30,
        //                       color: Colors.deepPurple,
        //                       fontWeight: FontWeight.w900
        //                   )
        //               ),
        //               minLeadingWidth : 30,
        //             ),
        //             subtitle: ListTile(
        //                   leading: Icon(
        //                     Icons.timer,
        //                     color: Colors.blueGrey[700],
        //                     size: 30.0,
        //                   ),
        //                   title: Text(
        //                       '15 min',
        //                       style: TextStyle(
        //                           fontSize: 30,
        //                           color: Colors.blueGrey[700],
        //                           fontWeight: FontWeight.w900
        //                       )
        //                   ),
        //                   minLeadingWidth : 10,
        //             )
        //           )
        //       ),
        //     ),
        //   ),
        // ],
        // child: ElevatedButton(
        //   child: const Card(
        //     child: ListTile(
        //       title: Text('One-line with trailing widget'),
        //       trailing: Icon(Icons.more_vert),
        //     ),
        //   ),
        //   onPressed: () {
        //     Navigator.push(
        //       context,
        //       MaterialPageRoute(builder: (context) => const MapView())
        //     );
        //   },
        // ),
//       ),
//     );
//   }
// }