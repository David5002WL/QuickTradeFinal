package com.example.david.quicktrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.david.quicktrade.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Agregar_producto extends AppCompatActivity {

    private Button botonAnadirPro;
    private EditText editNomPro;
    private RadioButton radio1, radio2, radio3;
    DatabaseReference bbdd, bbdd2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        mAuth = FirebaseAuth.getInstance();

        botonAnadirPro = (Button)findViewById(R.id.botonAnadirPro);
        editNomPro = (EditText)findViewById(R.id.editNomPro);
        radio1 = (RadioButton)findViewById(R.id.radioButton);
        radio2 = (RadioButton)findViewById(R.id.radioButton2);
        radio3 = (RadioButton)findViewById(R.id.radioButton3);

        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_usuario));

        /*bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = datasnapshot.getValue(Usuario.class);

                    String nombreusuario = usuario.getNombreusuario();
                    listado.add(nombreusuario);

                }

                adaptador = new ArrayAdapter<String>(Agregar_producto.this, android.R.layout.simple_list_item_1, listado);
                spinner.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */

        botonAnadirPro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String productoNombre = editNomPro.getText().toString();


                if(!TextUtils.isEmpty(productoNombre)){

                    bbdd2 = FirebaseDatabase.getInstance().getReference(getString(R.string.nodo_producto));
                    String clave = bbdd2.push().getKey();

                    if(radio1.isChecked()) {
                        Producto producto = new Producto(R.mipmap.item, productoNombre, "Tecnología", mAuth.getCurrentUser().getUid());
                        Toast.makeText(Agregar_producto.this, "Producto añadido", Toast.LENGTH_LONG).show();
                        bbdd2.child(clave).setValue(producto);
                    }
                    if(radio2.isChecked()) {
                        Producto producto = new Producto( R.mipmap.item, productoNombre, "Coches", mAuth.getCurrentUser().getUid());
                        Toast.makeText(Agregar_producto.this, "Producto añadido", Toast.LENGTH_LONG).show();
                        bbdd2.child(clave).setValue(producto);
                    }
                    if(radio3.isChecked()) {
                        Producto producto = new Producto( R.mipmap.item, productoNombre, "Hogar", mAuth.getCurrentUser().getUid());
                        Toast.makeText(Agregar_producto.this, "Producto añadido", Toast.LENGTH_LONG).show();
                        bbdd2.child(clave).setValue(producto);
                    }
                }else{
                    Toast.makeText(Agregar_producto.this, "Debes introducir un producto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
