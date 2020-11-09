package com.elvisoft.listcontacchat.ui.login;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elvisoft.listcontacchat.MainActivity;
import com.elvisoft.listcontacchat.R;
import com.elvisoft.listcontacchat.RegisterActivity;
import com.elvisoft.listcontacchat.ResetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        final EditText usernameEditText = findViewById(R.id.txtusername);
        final EditText passwordEditText = findViewById(R.id.txtpassword);
        final Button loginButton = findViewById(R.id.btnloginnew);
        final Button registerButton = findViewById(R.id.btnregister1);
        final Button forgoutbtn =findViewById(R.id.btnforgout);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String email = usernameEditText.getText().toString();
                String password= passwordEditText.getText().toString();
                if(email.length()==0 || password.length()==0){
                    Toast.makeText(LoginActivity.this,"Debe ingresar email y password!",Toast.LENGTH_LONG).show();
                }
                else{
                    loginUser(email,password);
                }

                loadingProgressBar.setVisibility(View.GONE);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
        forgoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                finish();
            }
        });
    }

    private void loginUser(String email, String pass){
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
                else {

                    Toast.makeText(LoginActivity.this,"El Usuario y la contraseña no estan registrados!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}