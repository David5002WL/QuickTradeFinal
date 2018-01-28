package com.example.david.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Principal extends AppCompatActivity {

    private Button botonAgregarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        botonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent ListSong = new Intent(Principal.this, Agregar_usuario.class);
                startActivity(ListSong);
            }
        });
    }

}
