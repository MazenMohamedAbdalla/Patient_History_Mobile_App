package com.example.patienthistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    private static String SCN;

    public String getSCN() {return SCN; }

    public void setSCN(String SCN) {MainActivity.SCN = SCN;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();
        Button enter = (Button) findViewById(R.id.enter_button);
        EditText SCN = (EditText) findViewById(R.id.editTextNumber);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = SCN.getText().toString();
                try {
                    Statement stm = myDb.createStatement();
                    ResultSet query = stm.executeQuery("SELECT * FROM clinic.patient_history WHERE PatientNationalID = '" + number + "';");
                    ResultSet query_2 = stm.executeQuery("SELECT * FROM clinic.patient WHERE PatientID = '" + number + "';");

                    if(TextUtils.isEmpty(number)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"أدخل الرقم القومي",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(number.length() < 16){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"أقل من 18 رقم",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(!query_2.next()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"غير مسجل",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else if(query.next()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"لا يمكن الملء مرة أخرى",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        int insertion = stm.executeUpdate("INSERT INTO clinic.patient_history (PatientNationalID) VALUES ('"+number+"');");
                        if(insertion > 0){
                            setSCN(number);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"تم التسجيل بنجاح",Toast.LENGTH_LONG).show();
                                }
                            });
                            Intent Page_1 = new Intent(MainActivity.this,Page_1.class);
                            startActivity(Page_1);
                            finish();
                        }
                    }
                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

    }
}