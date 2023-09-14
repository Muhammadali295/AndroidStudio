package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public void sendNow(View view){
        Toast.makeText(this, "data is send....", Toast.LENGTH_SHORT).show();
    }
    public void recieveNow(View view){
        Toast.makeText(this, "data is received....", Toast.LENGTH_SHORT).show();
    }
    public void DeleteNow(View view){
        Toast.makeText(this, "data is deleted....", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}