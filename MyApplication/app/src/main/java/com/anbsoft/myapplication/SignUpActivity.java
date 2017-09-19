package com.anbsoft.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anbsoft.myapplication.Helper.DatabaseHelper;
import com.anbsoft.myapplication.Models.User;

/**
 * Created by Akash on 19-Sep-17.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private EditText username;
    private EditText phoneNumber;
    private EditText password;
    private EditText repeatPassword;
    private Button signUp;
    private TextView loginText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        password = (EditText) findViewById(R.id.password);
        repeatPassword = (EditText) findViewById(R.id.repeatPassword);
        signUp = (Button) findViewById(R.id.signUpButton);

        loginText = (TextView) findViewById(R.id.loginLink);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString() == null || email.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (phoneNumber.getText().toString() == null || phoneNumber.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Phone Number cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString() == null || username.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString() == null || password.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (repeatPassword.getText().toString() == null || repeatPassword.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Repeat Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!repeatPassword.getText().toString().contentEquals(password.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Repeat Password must be same as password", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(SignUpActivity.this);

                    boolean userExist = databaseHelper.checkUser(username.getText().toString());
                    if (userExist){
                        Toast.makeText(SignUpActivity.this, "User with this username already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseHelper.addUser(new User(username.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), password.getText().toString()));
                        Toast.makeText(SignUpActivity.this, "User Successfully Registered!!!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
