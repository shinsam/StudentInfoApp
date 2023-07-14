package com.example.studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText birthday, name, phone, warn;
    Spinner spinner;
    RadioButton r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        birthday = findViewById(R.id.birthValueTextView);
        name = findViewById(R.id.nameValueTextView);
        phone = findViewById(R.id.contactValueTextView);
        warn = findViewById(R.id.warnValueTextView);
        spinner = findViewById(R.id.bloodSpinner);
        r1 = findViewById(R.id.bloodTypePlus);
        r2 = findViewById(R.id.bloodTypeMinus);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.blood, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        findViewById(R.id.birthDateLayer).setOnClickListener(this);
        findViewById(R.id.saveButton).setOnClickListener(this);

        readData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birthDateLayer:
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String date = year + "/" + (month + 1) + "/" + day;
                        birthday = findViewById(R.id.birthValueTextView);
                        birthday.setText(date);
                    }
                };
                DatePickerDialog dlg = new DatePickerDialog(this, listener, 2023, 6, 30);
                dlg.show();
                break;
            case R.id.saveButton:
                savaData();
                break;
        }
    }

    private void savaData() {

        String str_name = name.getText().toString();
        String str_birth = birthday.getText().toString();
        String str_phone = phone.getText().toString();
        String str_warn = warn.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences("STUDENT", MODE_PRIVATE).edit();
        editor.putString("생일", str_birth);
        editor.putString("이름", str_name);
        editor.putString("전화", str_phone);
        editor.putString("주의", str_warn);
        if(r1.isChecked())
            editor.putString("혈액형",r1.getText() + " " + spinner.getSelectedItem().toString());
        else
            editor.putString("혈액형",r2.getText() + " " + spinner.getSelectedItem().toString());

        editor.apply();

        Toast.makeText(this, "저장성공", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void readData() {
        SharedPreferences sp = getSharedPreferences("STUDENT" ,MODE_PRIVATE );
        name.setText(sp.getString("이름" , ""));
        birthday.setText(sp.getString("생일" , ""));
        phone.setText(sp.getString("전화" , ""));
        warn.setText(sp.getString("주의" , "없음"));
        String tmp = sp.getString("혈액형", "Rh+ A");
        if (tmp.contains("Rh+"))
            r1.setChecked(true);
        else
            r2.setChecked(true);

        tmp = tmp.substring(tmp.indexOf(' ')+1);
        String[] blood = getResources().getStringArray(R.array.blood);
        for(int i = 0 ; i < blood.length; i++){
            if(blood[i].equals(tmp)) {
                Toast.makeText(this, tmp, Toast.LENGTH_SHORT).show();
                spinner.setSelection(i);
            }
        }


    }
}



