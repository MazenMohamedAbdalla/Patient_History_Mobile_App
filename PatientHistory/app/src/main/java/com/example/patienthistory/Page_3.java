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

public class Page_3 extends AppCompatActivity {
    MainActivity SCN = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        DB_Connection connect = new DB_Connection();
        Connection myDb = connect.Connect_DB();

        Button next = (Button) findViewById(R.id.next_button);
        Button back = (Button) findViewById(R.id.next_button2);
        TextView sActivity = (TextView) findViewById(R.id.textView12);
        RadioGroup SexualActivity = (RadioGroup) findViewById(R.id.Radio111);
        TextView gSurgery = (TextView) findViewById(R.id.textView13);
        RadioGroup gynSurgury = (RadioGroup) findViewById(R.id.Radio222);

        CheckBox InfertilitySurgery = (CheckBox) findViewById(R.id.checkBox23);
        CheckBox OvarianSurgery = (CheckBox) findViewById(R.id.checkBox24);
        CheckBox CesareanSurgery = (CheckBox) findViewById(R.id.checkBox25);
        CheckBox TubalLigationSurgery = (CheckBox) findViewById(R.id.checkBox26);
        CheckBox HysterectomySurgery = (CheckBox) findViewById(R.id.checkBox27);
        CheckBox LaparoscopySurgery = (CheckBox) findViewById(R.id.checkBox28);

        EditText InfertilitySurgeryYear = (EditText) findViewById(R.id.editTextNumber5);
        EditText OvarianSurgeryYear = (EditText) findViewById(R.id.editTextNumber7);
        EditText CesareanSurgeryYear = (EditText) findViewById(R.id.editTextNumber16);
        EditText TubalLigationSurgery_Year = (EditText) findViewById(R.id.editTextNumber17);
        EditText HysterectomySurgery_Year = (EditText) findViewById(R.id.editTextNumber18);
        EditText LaparoscopySurgeryYear = (EditText) findViewById(R.id.editTextNumber19);

        EditText OtherGynSurgeries = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText OtherGynSurgeriesYear = (EditText) findViewById(R.id.editTextNumber21);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Infertility_Surgery = InfertilitySurgery.getText().toString();
                String Ovarian_Surgery = OvarianSurgery.getText().toString();
                String Cesarean_Surgery = CesareanSurgery.getText().toString();
                String Tubal_Ligation_Surgery = TubalLigationSurgery.getText().toString();
                String Hysterectomy_Surgery = HysterectomySurgery.getText().toString();
                String Laparoscopy_Surgery = LaparoscopySurgery.getText().toString();

                String Infertility_Surgery_Year = InfertilitySurgeryYear.getText().toString();
                String Ovarian_Surgery_Year = OvarianSurgeryYear.getText().toString();
                String Cesarean_Surgery_Year = CesareanSurgeryYear.getText().toString();
                String Tubal_Ligation_Surgery_Year = TubalLigationSurgery_Year.getText().toString();
                String Hysterectomy_Surgery_Year = HysterectomySurgery_Year.getText().toString();
                String Laparoscopy_Surgery_Year = LaparoscopySurgeryYear.getText().toString();
                String Other_Gyn_Surgeries = OtherGynSurgeries.getText().toString();
                String Other_Gyn_Surgeries_Year = OtherGynSurgeriesYear.getText().toString();

                // SexualActivity
                int selId_1 = SexualActivity.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selId_1);

                // gynSurgery
                int selId_2 = gynSurgury.getCheckedRadioButtonId();
                RadioButton radioButton2 = (RadioButton) findViewById(selId_2);

                // default colors
                sActivity.setTextColor(Color.BLACK);
                gSurgery.setTextColor(Color.BLACK);
                try {
                    // RadioButtons Validation
                    if(SexualActivity.getCheckedRadioButtonId() == -1 || gynSurgury.getCheckedRadioButtonId() == -1){
                        if(SexualActivity.getCheckedRadioButtonId() == -1){
                            sActivity.setTextColor(Color.RED);
                        }
                        if(gynSurgury.getCheckedRadioButtonId() == -1){
                            gSurgery.setTextColor(Color.RED);
                        }
                    }
                    // Radio & checkBox Validation
                    else if(radioButton2.getText().toString().equals("نعم")){
                        if(InfertilitySurgery.isChecked() && Infertility_Surgery_Year.isEmpty()){
                            InfertilitySurgeryYear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else if(OvarianSurgery.isChecked() && Ovarian_Surgery_Year.isEmpty()){
                            OvarianSurgeryYear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else if(CesareanSurgery.isChecked() && Cesarean_Surgery_Year.isEmpty()){
                            CesareanSurgeryYear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else if(TubalLigationSurgery.isChecked() && Tubal_Ligation_Surgery_Year.isEmpty()){
                            TubalLigationSurgery_Year.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else if(HysterectomySurgery.isChecked() && Hysterectomy_Surgery_Year.isEmpty()){
                            HysterectomySurgery_Year.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else if(LaparoscopySurgery.isChecked() && Laparoscopy_Surgery_Year.isEmpty()){
                            LaparoscopySurgeryYear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else if(!Other_Gyn_Surgeries.matches("") && Other_Gyn_Surgeries_Year.isEmpty()){
                            OtherGynSurgeriesYear.setError("لا يمكن ترك هذا الحقل فارغاً");
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Page_3.this,"تأكد من تحديد ما تم ملء حقله",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                    else{
                        Statement stm = myDb.createStatement();
                        int update = stm.executeUpdate("UPDATE clinic.patient_history SET SexualActivity_Iquiries = '"+radioButton+"', Had_Gyn_Surgries = '"+radioButton2+"', Infertility_Surgery = '"+Infertility_Surgery+"', Ovarian_Surgery = '"+Ovarian_Surgery+"',\n" +
                                " Cesarean_Surgery = '"+Cesarean_Surgery+"', Tubal_Ligation_Surgery = '"+Tubal_Ligation_Surgery+"', Hysterectomy_Surgery = '"+Hysterectomy_Surgery+"', Laparoscopy_Surgery = '"+Laparoscopy_Surgery+"',\n" +
                                " Infertility_Surgery_Year = '"+Infertility_Surgery_Year+"', Ovarian_Surgery_Year = '"+Ovarian_Surgery_Year+"', Cesarean_Surgery_Year = '"+Cesarean_Surgery_Year+"',Tubal_Ligation_Surgery_Year = '"+Tubal_Ligation_Surgery_Year+"',\n" +
                                " Hysterectomy_Surgery_Year = '"+Hysterectomy_Surgery_Year+"', Laparoscopy_Surgery_Year = '"+Laparoscopy_Surgery_Year+"',Other_Gyn_Surgeries = '"+Other_Gyn_Surgeries+"', Other_Gyn_Surgeries_Year = '"+Other_Gyn_Surgeries_Year+"',\n  WHERE PatientNationalID = '"+SCN.getSCN()+"';");
                        if(update > 0){
                            Intent page_4 = new Intent(Page_3.this,Page_4.class);
                            startActivity(page_4);
                            finish();
                        }
                        else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Page_3.this,"فشل في حفظ البيانات",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }catch (Exception e){
                    Log.e("Error",e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Page_3.this,"غير متصل",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page_2 = new Intent(Page_3.this,Page_2.class);
                startActivity(page_2);
                finish();
            }
        });
    }
}