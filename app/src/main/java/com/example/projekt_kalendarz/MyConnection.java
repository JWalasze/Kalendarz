package com.example.projekt_kalendarz;

import android.os.AsyncTask;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

class ConnectMySql extends AsyncTask<String, Void, String> {
    String res = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/android", "root", "root");
            System.out.println("Databaseection success");

            String result = "Database Connection Successful\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select distinct Country from tblCountries");
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                result += rs.getString(1).toString() + "\n";
            }
            res = result;
        } catch (Exception e) {
            e.printStackTrace();
            res = e.toString();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String result) {
        //txtData.setText(result);
    }
}
