package com.anbsoft.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class LoginActivity extends AppCompatActivity {

    private EditText loginId;
    private EditText password;
    private Button login;
    private TextView signUpText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        loginId = (EditText) findViewById(R.id.loginId);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        signUpText = (TextView) findViewById(R.id.signUpLink);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginId.getText().toString() == null || loginId.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString() == null || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);

                    boolean userExist = databaseHelper.checkUser(loginId.getText().toString());
                    if (!userExist) {
                        Toast.makeText(LoginActivity.this, "User with this username doesn't exists", Toast.LENGTH_SHORT).show();
                    } else {
                        User currentUser = databaseHelper.getUser(loginId.getText().toString(), password.getText().toString());
                        if (currentUser == null) {
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name", currentUser.getUsername());
                            editor.putString("email", currentUser.getEmail());
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
