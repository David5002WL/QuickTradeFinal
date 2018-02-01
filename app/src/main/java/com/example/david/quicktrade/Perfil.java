package com.example.david.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Perfil extends AppCompatActivity {
    Button botonAgregarUsuario, botonAgregarProducto, botonIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        botonAgregarUsuario = (Button)findViewById(R.id.boton_agrUsu);
        botonAgregarProducto = (Button)findViewById(R.id.boton_agrPro);


        botonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Agregar_usuario.class);
                startActivity(intent);
            }
        });

        botonAgregarProducto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Perfil.this, Agregar_producto.class);
                startActivity(intent);
            }
        });
    }
}
