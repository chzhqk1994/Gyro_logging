package com.example.user.sensorlogging;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Click_destroyed(View v){
        Intent intent = new Intent(getApplicationContext(), DestroyedActivity.class);
        startActivity(intent);
    }


    public void Click_bump(View v){
        Intent intent = new Intent(getApplicationContext(), BumpActivity.class);
        startActivity(intent);
    }

}