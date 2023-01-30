package com.example.patienthistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Page_1 extends AppCompatActivity {
    RadioButton radioButton;
    MainActivity SCN = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();

        Button next = (Button) findViewById(R.id.next_button);
        EditText visitReason = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText husbandName = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText husbandAge = (EditText) findViewById(R.id.editTextNumber2);
        EditText firstPeriodAge = (EditText) findViewById(R.id.editTextNumber3);
        EditText periodDuration = (EditText) findViewById(R.id.editTextNumber4);

        TextView pStatus = (TextView) findViewById(R.id.textView5);
        RadioGroup periodStatus = (RadioGroup) findViewById(R.id.radioGroup2);
        TextView bBPeriods = (TextView) findViewById(R.id.textView8);
        RadioGroup bleedingBetweenPeriods = (RadioGroup) findViewById(R.id.radioGroup);
        TextView  bAfterSex = (TextView) findViewById(R.id.textView9);
        RadioGroup bleedingAfterSex = (RadioGroup) findViewById(R.id.radioGroup3);
        TextView bDuration = (TextView) findViewById(R.id.textView10);
        RadioGroup bleedingDuration = (RadioGroup) findViewById(R.id.radioGroup4);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // editText group
                String Visit_Reason = visitReason.getText().toString();
                String Husband_Name = husbandName.getText().toString();
                String Husband_Age = husbandAge.getText().toString();
                String FirstPeriod_Age = firstPeriodAge.getText().toString();
                String Period_Duration = periodDuration.getText().toString();

                // periodStatus
                int selId_1 = periodStatus.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selId_1);

                // bleedingBetweenPeriods
                int selId_2 = bleedingBetweenPeriods.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selId_2);

                // bleedingAfterSex
                int selId_3 = bleedingAfterSex.getCheckedRadioButtonId();
                RadioButton radioButton3 = (RadioButton) findViewById(selId_3);

                // bleedingDuration
                int selId_4 = bleedingDuration.getCheckedRadioButtonId();
                RadioButton radioButton4 = (RadioButton) findViewById(selId_4);

                // default colorSet
                pStatus.setTextColor(Color.BLACK);
                bBPeriods.setTextColor(Color.BLACK);
                bAfterSex.setTextColor(Color.BLACK);
                bDuration.setTextColor(Color.BLACK);

                int update;
                try{
                    Statement stm = myDb.createStatement();
                    // editText Validation
                    if(Visit_Reason.isEmpty() || Husband_Name.isEmpty() || Husband_Age.isEmpty() || FirstPeriod_Age.isEmpty() || Period_Duration.isEmpty()){
                        if(Visit_Reason.isEmpty()){
                            visitReason.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Husband_Name.isEmpty()){
                            husbandName.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Husband_Age.isEmpty()){
                            husbandAge.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(FirstPeriod_Age.isEmpty()){
                            firstPeriodAge.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Period_Duration.isEmpty()){
                            periodDuration.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                    }
                    // radioButtons Validation
                    else if(periodStatus.getCheckedRadioButtonId() == -1 || bleedingBetweenPeriods.getCheckedRadioButtonId() == -1 || bleedingAfterSex.getCheckedRadioButtonId() == -1 || bleedingDuration.getCheckedRadioButtonId() == -1){
                        if(periodStatus.getCheckedRadioButtonId() == -1){
                            pStatus.setTextColor(Color.RED);
                        }
                        if(bleedingBetweenPeriods.getCheckedRadioButtonId() == -1){
                            bBPeriods.setTextColor(Color.RED);;
                        }
                        if(bleedingAfterSex.getCheckedRadioButtonId() == -1){
                            bAfterSex.setTextColor(Color.RED);;
                        }
                        if(bleedingDuration.getCheckedRadioButtonId() == -1){
                            bDuration.setTextColor(Color.RED);;
                        }
                    }
                    // provided full information
                    else {
                        if (radioButton4.getText().toString().equals("4")) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Visit_Reason = '" + Visit_Reason + "', Husband_Name = '" + Husband_Name + "' ,Husband_Age = '" + Husband_Age + "' ,\n" +
                                    " FirstPeriod_Age = '" + FirstPeriod_Age + "', Period_Regular = '" + radioButton.getText().toString() + "',Period_IRegular = '" + radioButton.getText().toString() + "', Period_Duration = '" + Period_Duration + "', PeriodtoPeriod_bleeding = '" + radioButton2.getText().toString() + "' ,\n" +
                                    " Bleeding_AfterSex = '" + radioButton3.getText().toString() + "' , Period_Duration_4d = 'نعم' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        } else if (radioButton4.getText().toString().equals("7")) {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Visit_Reason = '" + Visit_Reason + "', Husband_Name = '" + Husband_Name + "' ,Husband_Age = '" + Husband_Age + "' ,\n" +
                                    " FirstPeriod_Age = '" + FirstPeriod_Age + "', Period_Regular = '" + radioButton.getText().toString() + "',Period_IRegular = '" + radioButton.getText().toString() + "', Period_Duration = '" + Period_Duration + "', PeriodtoPeriod_bleeding = '" + radioButton2.getText().toString() + "' ,\n" +
                                    " Bleeding_AfterSex = '" + radioButton3.getText().toString() + "' , Period_Duration_7d = 'نعم' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        } else {
                            update = stm.executeUpdate("UPDATE clinic.patient_history SET Visit_Reason = '" + Visit_Reason + "', Husband_Name = '" + Husband_Name + "' ,Husband_Age = '" + Husband_Age + "' ,\n" +
                                    " FirstPeriod_Age = '" + FirstPeriod_Age + "', Period_Regular = '" + radioButton.getText().toString() + "',Period_IRegular = '" + radioButton.getText().toString() + "', Period_Duration = '" + Period_Duration + "', PeriodtoPeriod_bleeding = '" + radioButton2.getText().toString() + "' ,\n" +
                                    " Bleeding_AfterSex = '" + radioButton3.getText().toString() + "' , Period_Duration_MoreThan7d = 'نعم' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        }
                        // inserted
                        if(update > 0){
                            Intent Page_1 = new Intent(Page_1.this,Page_2.class);
                            startActivity(Page_1);
                            finish();
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Page_1.this,"فشل في حفظ البيانات",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }catch(Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Page_1.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

}