package com.elvisoft.listcontacchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elvisoft.listcontacchat.Models.User;
import com.elvisoft.listcontacchat.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText username = findViewById(R.id.editTextPersonName);
        final EditText lastname = findViewById(R.id.editTextPersonLastName);
        final EditText email = findViewById(R.id.editTextEmailAddress);
        final EditText telephone = findViewById(R.id.editTextPhone);
        final Button btnregistrar =findViewById(R.id.btnRegistrarahora);
        final EditText password = findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String correo = email.getText().toString();
                final String passwordstr = password.getText().toString();
                final String name = username.getText().toString();
                final String apellido = lastname.getText().toString();
                final String telefono = telephone.getText().toString();
                if(correo.length()>4 && passwordstr.length()>4){
                    mAuth.createUserWithEmailAndPassword(correo,passwordstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(RegisterActivity.this, "Se registro correctamente.", Toast.LENGTH_SHORT).show();
                            User usuario = new User();
                            usuario.setFirstname(name);
                            usuario.setLastname(apellido);
                            usuario.setEmail(correo);
                            usuario.setPhone(telefono);
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            DatabaseReference reference = database.getReference("Usuarios/" + currentUser.getUid());
                            reference.setValue(usuario);
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                            finish();
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Ingrese email y password correctamente", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}