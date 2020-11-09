package com.elvisoft.listcontacchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elvisoft.listcontacchat.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        final Button btnresetpassword = findViewById(R.id.btnforgoutFinal);
        final EditText emailtxt = findViewById(R.id.textEmailForgout);

        mDialog= new ProgressDialog(this);
        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stremail = emailtxt.getText().toString();
                if(!stremail.isEmpty()){
                    mDialog.setMessage("Espere porfavor...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    ResetearxEmail(stremail);
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this,"Debe ingresar email correctamente!",Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void ResetearxEmail(String stremail) {
        auth.setLanguageCode("en");
        auth.sendPasswordResetEmail(stremail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPasswordActivity.this,"Se envio un email para restablecer contraseña!",Toast.LENGTH_LONG).show();
                    final EditText emailtxt2 = findViewById(R.id.textEmailForgout);
                    emailtxt2.setText("");
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this,"No se pudo enviar email!",Toast.LENGTH_LONG).show();
                }
                mDialog.dismiss();
            }
        });
    }
}