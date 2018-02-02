package com.example.david.quicktrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Principal extends AppCompatActivity {

    private EditText editCorreo, editContrasenya;
    private Button botonRegistrar, botonIniciar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mAuth = FirebaseAuth.getInstance();

        editCorreo = (EditText)findViewById(R.id.editCorreo);
        editContrasenya = (EditText)findViewById(R.id.editContra);

        botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        botonIniciar = (Button) findViewById(R.id.botonIniciar);



        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String correo = editCorreo.getText().toString();
                String contrasenya = editContrasenya.getText().toString();

                registrar(correo, contrasenya);
            }
        });

        botonIniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String correo = editCorreo.getText().toString();
                String contrasenya = editContrasenya.getText().toString();

                iniciar(correo, contrasenya);

            }
        });


    }

    private void registrar (String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(Principal.this, "Registro completado.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Principal.this, "Fallo en el registro." + task.getException(),
                                    Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });
    }

    private void iniciar (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Principal.this, "Inicio completado.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Principal.this, Perfil.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Principal.this, "Inicio de sesión fallido.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}
