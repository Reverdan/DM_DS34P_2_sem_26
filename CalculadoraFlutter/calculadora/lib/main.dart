import 'package:flutter/material.dart';

void main() 
{
  runApp(const MyApp());
}

class MyApp extends StatelessWidget
{
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) 
  {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: _buildHome(),
    );
  }
}

Widget _buildHome()
{
  return Scaffold(
    appBar: _buildAppBar(),
    body: _buildBody(),
  );
}

AppBar _buildAppBar()
{
  return AppBar(
    title: const Text("Calculadora"),
    backgroundColor: Colors.blue,
    foregroundColor: Colors.black,
  );
}

Widget _buildBody()
{
  return Container(
    padding: EdgeInsets.fromLTRB(20.0, 20.0, 20.0, 20.0),
    color: Colors.grey.shade200,

    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        const Text("Digite o primeiro número"),
        const SizedBox(height: 10.0,),
        const TextField(
          keyboardType: TextInputType.number,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
          ),
        ),
        const SizedBox(height: 10.0,),
        const Text("Digite o segundo número"),
        const SizedBox(height: 10.0,),
        const TextField(
          keyboardType: TextInputType.number,
          decoration: InputDecoration(
            border: OutlineInputBorder(),
          ),
        ),
      ],

    )
    
  );
}