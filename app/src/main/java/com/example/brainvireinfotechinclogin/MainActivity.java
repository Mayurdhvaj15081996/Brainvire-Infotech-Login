package com.example.brainvireinfotechinclogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    TextInputLayout name,email,password;
    Button login;
    CoordinatorLayout layout;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextInputLayout) findViewById(R.id.name);
        email = (TextInputLayout) findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        layout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);

        // onClick method to get all values from textInputLayout and move to next activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getEditText().getText().toString();
                String getEmail = email.getEditText().getText().toString();
                String getPassword = password.getEditText().getText().toString();

                dbHelper = new DBHelper(MainActivity.this,"MyDB.db",null,1);
                //Here I am checking user enters data or not..
                if(getName.isEmpty()){
                    name.setError("Please Enter Name");
                    Snackbar.make(layout,"Please Enter Name",Snackbar.LENGTH_LONG).show();
                }else if(getEmail.isEmpty()) {
                    email.setError("Please Enter Email");
                    Snackbar.make(layout,"Please Enter Email",Snackbar.LENGTH_LONG).show();
                }else if(getPassword.isEmpty()) {
                    password.setError("Please Enter Password");
                    Snackbar.make(layout,"Please Enter Password",Snackbar.LENGTH_LONG).show();
                }else{
                //Here i am entering Data into Database..
                boolean result = dbHelper.insertData(getName,getEmail,getPassword);
                if(result == true){
                    Snackbar.make(layout,"Welcome "+getName,Snackbar.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,Welcome.class);
                    startActivity(intent);
                }else{
                    Snackbar.make(layout,"You Are Not Authorized",Snackbar.LENGTH_LONG).show();
                }
                }
            }
        });
    }

}
