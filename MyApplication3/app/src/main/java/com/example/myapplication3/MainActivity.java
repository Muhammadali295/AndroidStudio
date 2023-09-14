package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    public static final int a ="com.My Application3.ORDER";
//    public static final String a ="com.My Application3.ORDER";
Button b;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b= findViewById(R.id.Send);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,NewActivity1.class);
                EditText edtxt1= findViewById(R.id.edtxt1);
                EditText edtxt2= findViewById(R.id.edtxt2);
                int n1= Integer.parseInt(edtxt1.getText().toString());
                int n2= Integer.parseInt(edtxt2.getText().toString());
                intent.putExtra("a",n1);
                intent.putExtra("c",n2);
                startActivity(intent);
            }
        });
        Intent intent1=getIntent();
        TextView tv=findViewById(R.id.tv);
        int n3= intent1.getIntExtra("d",0);
        tv.setText(String.valueOf(n3));
    }}
