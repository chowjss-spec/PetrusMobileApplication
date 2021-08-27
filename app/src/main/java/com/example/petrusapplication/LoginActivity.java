package com.example.petrusapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petrusapplication.clients.MyLoopjTask;
import com.example.petrusapplication.clients.OnLoopjCompleted;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity implements OnLoopjCompleted {

    EditText userName;
    EditText passWord;
    Button btnSearch;
    MyLoopjTask myLoopjTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.etSearchTerms);
        passWord = (EditText) findViewById(R.id.password);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        myLoopjTask = new MyLoopjTask(this,this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userName.getText().toString();
                String password = passWord.getText().toString();
                userName.setText("");
                passWord.setText("");
                // make Loopj HTTP call
                try {
                    myLoopjTask.executeLoopjCall(username,password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        checkIfSignedOn();
    }

    @Override
    public void taskCompleted(String results) {
        if (results!="");
        {
            Toast toast=Toast.makeText(getApplicationContext(),"Success!", Toast.LENGTH_LONG);
            toast.show();
            SharedPreferences appSharedPrefs = this.getSharedPreferences(
                    "petrus", Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
            prefsEditor.putString("userDetails", results);
            prefsEditor.commit();
            Intent intent=new Intent(getApplicationContext(),UserActivity.class);
            startActivity(intent);
        }
    }
    public void checkIfSignedOn(){
        SharedPreferences appSharedPrefs = this.getSharedPreferences(
                "petrus", Context.MODE_PRIVATE);
        String results = appSharedPrefs.getString("userDetails", "");
        if (!results.equals(""))
        {
            Intent intent=new Intent(getApplicationContext(),UserActivity.class);
            startActivity(intent);
        }
    }
}