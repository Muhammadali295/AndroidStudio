package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewActivity1 extends AppCompatActivity {
TextView tv2;
Button add;
Button Sub;
Button mul;
Button Div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new1);
        Intent intent= getIntent();
        int n1=intent.getIntExtra("a",0);
        int n2=intent.getIntExtra("c",0);
        TextView tv2= findViewById(R.id.tv2);
        tv2.setText(String.valueOf(n1));
        add=findViewById(R.id.Add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(NewActivity1.this,MainActivity.class);
                int n3= n1+n2;
                intent1.putExtra("d",n3);
                startActivity(intent1);
            }
        });
        Sub=findViewById(R.id.Sub);
        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(NewActivity1.this,MainActivity.class);
                int n3= n1-n2;
                intent1.putExtra("d",n3);
                startActivity(intent1);
            }
        });
        mul=findViewById(R.id.mul);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(NewActivity1.this,MainActivity.class);
                int n3= n1*n2;
                intent1.putExtra("d",n3);
                startActivity(intent1);
            }
        });
        Div=findViewById(R.id.Div);
        Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(NewActivity1.this,MainActivity.class);
                int n3= n1/n2;
                intent1.putExtra("d",n3);
                startActivity(intent1);
            }
        });

        };
    }

