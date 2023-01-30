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

public class Page_5 extends AppCompatActivity {
    MainActivity SCN = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);
        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();

        Button next = (Button) findViewById(R.id.next_button);
        Button back = (Button) findViewById(R.id.next_button2);

        EditText MedicineTaken = (EditText) findViewById(R.id.editTextTextPersonName4);
        EditText MedicineDoses = (EditText) findViewById(R.id.editTextTextPersonName6);
        EditText MedicinePillPerDay = (EditText) findViewById(R.id.editTextTextPersonName5);

        TextView H_DrugAllergies = (TextView) findViewById(R.id.textView17);
        RadioGroup Have_DrugAllergies = (RadioGroup) findViewById(R.id.Radio1111);
        EditText Drug_AllergiesNames = (EditText) findViewById(R.id.editTextTextPersonName7);

        TextView eQ = (TextView) findViewById(R.id.textView19);
        CheckBox FamiliyMemberDiabetes = (CheckBox) findViewById(R.id.checkBox37);
        CheckBox FamiliyMemberOvarianCnacer = (CheckBox) findViewById(R.id.checkBox39);
        CheckBox FamiliyMemberBreastCancer = (CheckBox) findViewById(R.id.checkBox38);
        CheckBox FamiliyMemberColonCancer = (CheckBox) findViewById(R.id.checkBox40);
        CheckBox NoFamiliyMemberDiseases = (CheckBox) findViewById(R.id.checkBox41);

        EditText AnotherFamiliyMemberDiseases = (EditText) findViewById(R.id.editTextTextPersonName8);
        EditText NamesFamiliyMemberDiseases = (EditText) findViewById(R.id.editTextTextPersonName9);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int selId_1 = Have_DrugAllergies.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selId_1);

                // default colors
                H_DrugAllergies.setTextColor(Color.BLACK);
                eQ.setTextColor(Color.BLACK);
                try {

                    Statement stm = myDb.createStatement();

                    // editText Validation
                    if(MedicineTaken.getText().toString().isEmpty() || MedicineDoses.getText().toString().isEmpty() || MedicinePillPerDay.getText().toString().isEmpty()){
                        if(MedicineTaken.getText().toString().isEmpty()){
                            MedicineTaken.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(MedicineDoses.getText().toString().isEmpty()){
                            MedicineDoses.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        if(MedicinePillPerDay.getText().toString().isEmpty()){
                            MedicinePillPerDay.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                    }
                    // RadioGroup Validation
                    else if(Have_DrugAllergies.getCheckedRadioButtonId() == -1){
                        H_DrugAllergies.setTextColor(Color.RED);
                    }
                    else if(radioButton.getText().toString().equals("نعم")){
                        if(Drug_AllergiesNames.getText().toString().isEmpty()){
                            Drug_AllergiesNames.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                    }
                    // checkBox & fields related Validation
                    else if(FamiliyMemberDiabetes.getText().toString().isEmpty() && FamiliyMemberOvarianCnacer.getText().toString().isEmpty() && FamiliyMemberBreastCancer.getText().toString().isEmpty() && FamiliyMemberColonCancer.getText().toString().isEmpty() && NoFamiliyMemberDiseases.getText().toString().isEmpty() && NamesFamiliyMemberDiseases.getText().toString().isEmpty()){
                        eQ.setTextColor(Color.RED);
                    }
                    else if(NamesFamiliyMemberDiseases.getText().toString().isEmpty()){
                        NamesFamiliyMemberDiseases.setError("لا يمكن ترك هذا الحقل فارغاً");
                    }
                    else{
                        int update = stm.executeUpdate("UPDATE clinic.patient_history SET Medicine_Taken = '"+MedicineTaken.getText().toString()+"', Medicine_Doses '"+MedicineDoses.getText().toString()+"',\n" +
                                "Medicine_Pill_PerDay = '"+MedicinePillPerDay.getText().toString()+"', Have_Drug_Allergies  = '"+radioButton.getText().toString()+"', Drug_Allergies_Names = '"+Drug_AllergiesNames.getText().toString()+"', FamiliyMember_Diabetes = '"+FamiliyMemberDiabetes.getText().toString()+"',\n" +
                                "FamiliyMember_OvarianCnacer = '"+FamiliyMemberOvarianCnacer.getText().toString()+"', FamiliyMember_BreastCancer = '"+FamiliyMemberBreastCancer.getText().toString()+"', FamiliyMember_ColonCancer = '"+FamiliyMemberColonCancer.getText().toString()+"',\n" +
                                "Another_FamiliyMember_Diseases = '"+AnotherFamiliyMemberDiseases.getText().toString()+"', No_FamiliyMember_Diseases = '"+NoFamiliyMemberDiseases.getText().toString()+"', Names_FamiliyMember_Diseases = '"+NamesFamiliyMemberDiseases.getText()+"', WHERE PatientNationalID = '"+SCN.getSCN()+"';");

                        if(update > 0){
                            Intent page_6 = new Intent(Page_5.this,Page_6.class);
                            startActivity(page_6);
                            finish();
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Page_5.this,"فشل في حفظ البيانات",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Page_5.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_4 = new Intent(Page_5.this,Page_4.class);
                startActivity(page_4);
                finish();
            }
        });

    }
}