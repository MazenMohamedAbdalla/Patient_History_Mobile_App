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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

import javax.microedition.khronos.egl.EGLDisplay;

public class Page_4 extends AppCompatActivity {
    MainActivity SCN = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();

        Button next = (Button) findViewById(R.id.next_button);
        Button back = (Button) findViewById(R.id.next_button2);
        TextView aSurgeries = (TextView) findViewById(R.id.textView6);
        RadioGroup AnotherSurgeries = (RadioGroup) findViewById(R.id.Radio111);
        EditText SurgeryType = (EditText) findViewById(R.id.editTextTextPersonName4);
        EditText SurgeryYear = (EditText) findViewById(R.id.editTextNumber21);
        EditText DatelastPapSmear = (EditText) findViewById(R.id.editTextDate2);
        CheckBox HaveArthritis = (CheckBox) findViewById(R.id.checkBox20);

        CheckBox HaveHighBloodPresaure = (CheckBox) findViewById(R.id.checkBox30);
        CheckBox HaveHeartDiseases = (CheckBox) findViewById(R.id.checkBox33);
        CheckBox HaveKidneyDiseases = (CheckBox) findViewById(R.id.checkBox31);
        CheckBox HaveEmphysema = (CheckBox) findViewById(R.id.checkBox32);
        CheckBox HaveEatingDisorder = (CheckBox) findViewById(R.id.checkBox29);
        CheckBox HaveThyroidDiseases = (CheckBox) findViewById(R.id.checkBox35);
        CheckBox HaveEpileps = (CheckBox) findViewById(R.id.checkBox36);
        CheckBox HaveBloodTransfustions = (CheckBox) findViewById(R.id.checkBox34);
        EditText HaveAnotherDiseases = (EditText) findViewById(R.id.editTextTextPersonName3);

        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Surgery_Type = SurgeryType.getText().toString();
                String Surgery_Year = SurgeryYear.getText().toString();
                String Date_last_PapSmear = DatelastPapSmear.getText().toString();
                String Have_Arthritis = HaveArthritis.getText().toString();
                String Have_HighBloodPresaure = HaveHighBloodPresaure.getText().toString();

                String Have_HeartDiseases = HaveHeartDiseases.getText().toString();
                String Have_KidneyDiseases = HaveKidneyDiseases.getText().toString();
                String Have_Emphysema = HaveEmphysema.getText().toString();
                String Have_Epileps = HaveEpileps.getText().toString();
                String Have_EatingDisorder = HaveEatingDisorder.getText().toString();
                String Have_BloodTransfustions = HaveBloodTransfustions.getText().toString();
                String Have_ThyroidDisease = HaveThyroidDiseases.getText().toString();

                String Have_AnotherDiseases = HaveAnotherDiseases.getText().toString();

                // AnotherSurgeries
                int selId_1 = AnotherSurgeries.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selId_1);

                // default Colors
                aSurgeries.setTextColor(Color.BLACK);
                try {
                    Statement stm = myDb.createStatement();

                    // RadioButton Validation
                    if(AnotherSurgeries.getCheckedRadioButtonId() == -1){
                        aSurgeries.setTextColor(Color.RED);
                    }
                    else if(radioButton.getText().toString().equals("نعم")){
                        if(Surgery_Type.isEmpty()){
                            SurgeryType.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Surgery_Year.isEmpty()){
                            SurgeryYear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(Date_last_PapSmear.isEmpty()){
                            DatelastPapSmear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                    }
                    else {
                        int update = stm.executeUpdate("UPDATE clinic.patient_history SET Another_Surgeries = '" + radioButton + "', Surgery_Type = '" + Surgery_Type + "',Surgery_Year = '" + Surgery_Year + "',Date_last_PapSmear = '" + Date_last_PapSmear + "', Have_Arthritis = '" + Have_Arthritis + "',Have_HighBloodPresaure = '" + Have_HighBloodPresaure + "',\n" +
                                " Have_HeartDiseases = '" + Have_HeartDiseases + "', Have_KidneyDiseases = '" + Have_KidneyDiseases + "', Have_Emphysema = '" + Have_Emphysema + "', Have_EatingDisorder = '" + Have_EatingDisorder + "', Have_BloodTransfustions = '" + Have_BloodTransfustions + "',\n" +
                                " Have_ThyroidDiseases = '" + Have_ThyroidDisease + "',Have_Epileps = '" + Have_Epileps + "',Have_AnotherDiseases = '" + Have_AnotherDiseases + "' WHERE PatientNationalID = '" + SCN.getSCN() + "';");
                        if(update > 0){
                            Intent page_4 = new Intent(Page_4.this,Page_4.class);
                            startActivity(page_4);
                            finish();
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Page_4.this,"فشل في حفظ البيانات",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Page_4.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_3 = new Intent(Page_4.this,Page_3.class);
                startActivity(page_3);
                finish();
            }
        });

    }
}