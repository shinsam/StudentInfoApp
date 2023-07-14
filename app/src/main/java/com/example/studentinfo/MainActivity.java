package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView birthday, name, phone, warn, blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthday = findViewById(R.id.birthValueTextView);
        name = findViewById(R.id.nameValueTextView);
        phone = findViewById(R.id.contactValueTextView);
        warn = findViewById(R.id.warnValueTextView);
        blood = findViewById(R.id.bloodTypeValueTextView);

        findViewById(R.id.deleteButton).setOnClickListener(this);
        findViewById(R.id.editButton).setOnClickListener(this);

        readData();
    }

    private void readData() {
        SharedPreferences sp = getSharedPreferences("STUDENT" ,MODE_PRIVATE );
        name.setText(sp.getString("이름" , ""));
        birthday.setText(sp.getString("생일" , ""));
        phone.setText(sp.getString("전화" , ""));
        warn.setText(sp.getString("주의" , "없음"));
        blood.setText(sp.getString("혈액형", "Rh+ A"));

   }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.deleteButton:
                //삭제 기능 구현
                SharedPreferences.Editor editor = getSharedPreferences("STUDENT" , MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                readData();
                break;
            case R.id.editButton:
                Intent intent = new Intent(MainActivity.this , EditActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        readData();
    }
}











