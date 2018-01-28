package com.example.david.quicktrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.david.quicktrade.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Agregar_usuario extends AppCompatActivity {

    EditText textNombreUsuario;
    EditText textCorreo;
    EditText textNombre;
    EditText textApellidos;
    EditText textDireccion;
    Button botonAnadir;
    Button botonModificar;
    ListView lista;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        textNombreUsuario = (EditText) findViewById(R.id.editNombreUsuario);
        textCorreo = (EditText) findViewById(R.id.editCorreo);
        textNombre = (EditText) findViewById(R.id.editNombre);
        textApellidos = (EditText) findViewById(R.id.editApellidos);
        textDireccion = (EditText) findViewById(R.id.editDireccion);
        botonAnadir = (Button) findViewById(R.id.botonAnadir);
        botonModificar = (Button) findViewById(R.id.botonModificar);
        lista = (ListView) findViewById(R.id.ListView);

        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_usuario));

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = datasnapshot.getValue(Usuario.class);

                    String nombreusuario = usuario.getNombreusuario();
                    listado.add(nombreusuario);

                }

                adaptador = new ArrayAdapter<String>(Agregar_usuario.this, android.R.layout.simple_list_item_1, listado);
                lista.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        botonAnadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                String nombreusuario = textNombreUsuario.getText().toString();
                String correo = textCorreo.getText().toString();
                String nombre = textNombre.getText().toString();
                String apellidos = textApellidos.getText().toString();
                String direccion = textDireccion.getText().toString();

                if (!TextUtils.isEmpty(nombreusuario)) {
                    if (!TextUtils.isEmpty(correo)) {
                        if (!TextUtils.isEmpty(nombre)) {
                            if (!TextUtils.isEmpty(apellidos)) {
                                if (!TextUtils.isEmpty(direccion)) {

                                    Usuario u = new Usuario(nombreusuario, correo, nombre, apellidos, direccion);
                                    String clave = bbdd.push().getKey();
                                    bbdd.child(clave).setValue(u);

                                    Toast.makeText(Agregar_usuario.this, "Usuario Añadido", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(Agregar_usuario.this, "Debes introducir una direccion", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Agregar_usuario.this, "Debes introducir un apellido", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(Agregar_usuario.this, "Debes introducir un nombre", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(Agregar_usuario.this, "Debes introducir un correo", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Agregar_usuario.this, "Debes introducir un nombre de usuario", Toast.LENGTH_LONG).show();
                }
            }
        });

        botonModificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String nombreusuario = textNombreUsuario.getText().toString();

                if(!TextUtils.isEmpty(nombreusuario)){
                    Query q=bbdd.orderByChild(getString(R.string.campo_nombreUsuario)).equalTo(nombreusuario);

                    q.addListenerForSingleValueEvent(new ValueEventListener(){

                        public void onDataChange(DataSnapshot dataSnapshot){
                            for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                String clave = datasnapshot.getKey();
                                bbdd.child(clave).child(getString(R.string.campo_correo)).setValue(textCorreo.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_nombre)).setValue(textNombre.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_apellidos)).setValue(textApellidos.getText().toString());
                                bbdd.child(clave).child(getString(R.string.campo_direccion)).setValue(textDireccion.getText().toString());
                            }
                        }

                        public void onCancelled(DatabaseError databaseError){

                        }
                    });
                    Toast.makeText(Agregar_usuario.this, "El elemento se ha modificado", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Agregar_usuario.this, "Debes introducir un nombre de usuario", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}