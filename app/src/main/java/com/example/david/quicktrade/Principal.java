package com.example.david.quicktrade;

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

    private EditText editCorreo, editContraseña;
    private Button botonRegistrar, botonIniciar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        editCorreo = (EditText)findViewById(R.id.editCorreo);
        editContraseña = (EditText)findViewById(R.id.editContra);

        botonRegistrar = (Button) findViewById(R.id.botonRegistrar);
        botonIniciar = (Button) findViewById(R.id.botonIniciar);



        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String correo = editCorreo.getText().toString();
                String contraseña = editContraseña.getText().toString();

                registrar(correo, contraseña);
            }
        });

    }

    private void registrar (String correo, String contraseña){

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(Principal.this, "Registro completado.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Principal.this, "Fallo en el registro.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

}
