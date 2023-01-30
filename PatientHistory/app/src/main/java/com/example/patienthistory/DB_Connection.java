package com.example.patienthistory;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
    private static final String user = "root";
    private static final String pass = "";
    private static Connection connect =null;
    @SuppressLint("NewApi")
    public Connection Connect_DB(){
        StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        try{
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            String URL = "jdbc:mysql://127.0.0.1:3306/?user=root/";
            connect = DriverManager.getConnection(URL,user,pass);
        }catch (Exception e){
            Log.e("Error",e.getMessage());
        }
        return connect;
    }
}
