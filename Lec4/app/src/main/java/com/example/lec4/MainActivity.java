package com.example.lec4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] fruits={"mangoes","bananas"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView) findViewById(R.id.lv1);
        ArrayAdapter<String> fruitadapter = new ArrayAdapter<String>(this,
                R.layout.singleitem,R.id.tv1, fruits);
        listView.setAdapter(fruitadapter);
    }
    public void fruitadpt(View view){
        Toast.makeText(this, "fruits....", Toast.LENGTH_SHORT).show();
    }
}