package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.editButton).setOnClickListener(this);
        findViewById(R.id.deleteButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editButton:
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                break;
            case R.id.deleteButton:
                break;
        }
    }
}