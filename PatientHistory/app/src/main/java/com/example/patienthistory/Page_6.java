package com.example.patienthistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class Page_6 extends AppCompatActivity {
    MainActivity SCN = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);
        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();

        Button submit = (Button) findViewById(R.id.next_button);
        Button back = (Button) findViewById(R.id.next_button2);

        TextView eQ = (TextView) findViewById(R.id.textView18);
        CheckBox Weight_Loss_Recently = (CheckBox) findViewById(R.id.checkBox18);
        CheckBox Weight_Gain_Recently = (CheckBox) findViewById(R.id.checkBox42);
        CheckBox Hair_Growth_Recently = (CheckBox) findViewById(R.id.checkBox43);
        CheckBox Hair_Loss_Recently = (CheckBox) findViewById(R.id.checkBox44);
        CheckBox ChangeInEnergy_Recently = (CheckBox) findViewById(R.id.checkBox45);
        CheckBox BreastDischarge_Recently = (CheckBox) findViewById(R.id.checkBox46);
        EditText AnotherSymptoms_Recently = (EditText) findViewById(R.id.editTextTextPersonName4);
        CheckBox NoSymptoms_Recently = (CheckBox) findViewById(R.id.checkBox47);
        CheckBox patientSigned = (CheckBox) findViewById(R.id.checkBox48);

        submit.setOnClickListener(new View.OnClickListener() {
            int update;
            @Override
            public void onClick(View view) {
                try {
                    Statement stm = myDb.createStatement();

                    if(Weight_Loss_Recently.getText().toString().isEmpty() || Weight_Gain_Recently.getText().toString().isEmpty() || Hair_Growth_Recently.getText().toString().isEmpty() || Hair_Loss_Recently.getText().toString().isEmpty() || ChangeInEnergy_Recently.getText().toString().isEmpty() || BreastDischarge_Recently.getText().toString().isEmpty() || NoSymptoms_Recently.getText().toString().isEmpty()){
                        eQ.setTextColor(Color.RED);
                    }
                    else {
                        if (Weight_Loss_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Weight_Loss_Recently = 'نعم',AnotherSymptoms_Recently = '" + AnotherSymptoms_Recently.getText().toString() + "' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        if (Weight_Gain_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Weight_Gain_Recently = 'نعم',AnotherSymptoms_Recently = '" + AnotherSymptoms_Recently.getText() + "' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        if (Hair_Growth_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Hair_Growth_Recently = 'نعم',AnotherSymptoms_Recently = '" + AnotherSymptoms_Recently.getText() + "' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        if (Hair_Loss_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Hair_Loss_Recently = 'نعم'  ,AnotherSymptoms_Recently = '" + AnotherSymptoms_Recently.getText() + "'WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        if (ChangeInEnergy_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET ChangeInEnergy_Recently = 'نعم' ,AnotherSymptoms_Recently = '" + AnotherSymptoms_Recently.getText() + "' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        if (BreastDischarge_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET BreastDischarge_Recently = 'نعم' ,AnotherSymptoms_Recently = '" + AnotherSymptoms_Recently.getText() + "' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        if (NoSymptoms_Recently.isChecked()) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET NoSymptoms_Recently = 'نعم' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                    }

                    // patient agreement
                    if(patientSigned.isChecked()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Page_6.this,"تم التسجيل",Toast.LENGTH_LONG).show();
                            }
                        });
                        Intent signIn = new Intent(Page_6.this,MainActivity.class);
                        startActivity(signIn);
                        finish();
                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Page_6.this,"يرجى التوقبع لاتمام تسجيل البيانات",Toast.LENGTH_LONG).show();
                            }
                        });
                    }


                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Page_6.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_5 = new Intent(Page_6.this,Page_5.class);
                startActivity(page_5);
                finish();
            }
        });

    }
}