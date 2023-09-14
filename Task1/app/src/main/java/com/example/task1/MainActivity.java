package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Next(View view){
        Intent intent= new Intent(this,Activity2.class);
        EditText edtxt1= findViewById(R.id.edtxt1);
        EditText edtxt2= findViewById(R.id.edtxt2);
        intent.putExtra(name,edtxt1);
        intent.putExtra(ay,edtxt2);
        StartActivity
    }
}