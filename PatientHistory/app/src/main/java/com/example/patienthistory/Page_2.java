package com.example.patienthistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class Page_2 extends AppCompatActivity {

    MainActivity SCN = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();

        Button next = (Button) findViewById(R.id.next_button);
        Button back = (Button) findViewById(R.id.next_button2);
        EditText lastPeriodDate = (EditText) findViewById(R.id.editTextDate);
        TextView pPain = (TextView) findViewById(R.id.textView6);
        RadioGroup periodPain = (RadioGroup) findViewById(R.id.Radio11);
        TextView pPainTime = (TextView) findViewById(R.id.textView7);
        RadioGroup periodPainTime = (RadioGroup) findViewById(R.id.Radio22);
        CheckBox hadBeforePreg = (CheckBox) findViewById(R.id.checkBox16);
        CheckBox hadBeforeAbor = (CheckBox) findViewById(R.id.checkBox17);
        EditText year = (EditText) findViewById(R.id.editTextNumber6);
        EditText place = (EditText) findViewById(R.id.editTextNumber8);
        EditText duration = (EditText) findViewById(R.id.editTextNumber66);
        EditText hours = (EditText) findViewById(R.id.editTextNumber10);
        EditText type = (EditText) findViewById(R.id.editTextNumber11);
        EditText gender = (EditText) findViewById(R.id.editTextNumber15);
        EditText weight = (EditText) findViewById(R.id.editTextNumber12);
        EditText medStatus = (EditText) findViewById(R.id.editTextNumber13);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // editText group
                String Last_PeriodDate = lastPeriodDate.getText().toString();
                String PregnancyorAbortion_Year = year.getText().toString();
                String Pregnancyorbortion_Place = place.getText().toString();
                String Pregnancy_Duration = duration.getText().toString();
                String HoursOfLabor = hours.getText().toString();
                String TypeofDelivery = type.getText().toString();
                String Baby_Gender = gender.getText().toString();
                String Baby_BirthWeight = weight.getText().toString();
                String Baby_PresentHealth = medStatus.getText().toString();
                String Had_Pregnant = hadBeforePreg.getText().toString();
                String Had_Abortion = hadBeforeAbor.getText().toString();

                // periodPain
                int selId_1 = periodPain.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selId_1);

                // periodPainTime
                int selId_2 = periodPainTime.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selId_2);

                pPain.setTextColor(Color.BLACK);
                pPainTime.setTextColor(Color.BLACK);
                try{
                    Statement stm = myDb.createStatement();

                    // independent editTextFiled Validation
                    if(Last_PeriodDate.isEmpty()){
                        lastPeriodDate.setError("لا يمكن ترك هذا الحقل فارغا");
                    }
                    // dependent radioGroup Validation
                    else if(periodPain.getCheckedRadioButtonId() == -1){
                        pPain.setTextColor(Color.RED);
                    }
                    else if(radioButton.getText().toString().equals("نعم") || radioButton.getText().toString().equals("احياناً")){
                        if(periodPainTime.getCheckedRadioButtonId() == -1){
                            pPainTime.setTextColor(Color.RED);
                        }
                    }
                    // checkBox Validation
                    else if(hadBeforePreg.isChecked() || hadBeforeAbor.isChecked()){
                        if(PregnancyorAbortion_Year.isEmpty()){
                            year.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Pregnancyorbortion_Place.isEmpty()){
                            place.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Pregnancy_Duration.isEmpty()){
                            duration.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(HoursOfLabor.isEmpty()){
                            hours.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(TypeofDelivery.isEmpty()){
                            type.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Baby_Gender.isEmpty()){
                            gender.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Baby_BirthWeight.isEmpty()){
                            weight.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Baby_PresentHealth.isEmpty()){
                            medStatus.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                    }
                    else {
                        int update = stm.executeUpdate("UPDATE clinic.patient_history SET Last_PeriodDate = '"+Last_PeriodDate+"',\n" +
                                " Period_Pain_Yes = '"+radioButton+"', Period_Pain_No = '"+radioButton+"' , Period_Pain_Sometimes = '"+radioButton+"' , Pain_Before_Period = '"+radioButton2+"', Pain_During_Period = '"+radioButton2+"' ,\n" +
                                " Pain_BeforeandDuring_Period = '"+radioButton2+"' , Had_Pregnant = '"+Had_Pregnant+"' , Had_Abortion = '"+Had_Abortion+"' , PregnancyorAbortion_Year = '"+PregnancyorAbortion_Year+"' ,\n" +
                                " Pregnancyorbortion_Place = '"+Pregnancyorbortion_Place+"',Pregnancy_Duration = '"+Pregnancy_Duration+"', HoursOfLabor = '"+HoursOfLabor+"', TypeofDelivery = '"+TypeofDelivery+"', Baby_Gender = '"+Baby_Gender+"',Baby_BirthWeight = '"+Baby_BirthWeight+"' ,\n" +
                                " Baby_PresentHealth = '"+Baby_PresentHealth+"'  WHERE PatientNationalID = '"+SCN.getSCN()+"';");

                        if(update > 0){
                            Intent page_3 = new Intent(Page_2.this,Page_3.class);
                            startActivity(page_3);
                            finish();
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Page_2.this,"فشل في حفظ البيانات",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Page_2.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_1 = new Intent(Page_2.this,Page_1.class);
                startActivity(page_1);
                finish();
            }
        });

    }
}