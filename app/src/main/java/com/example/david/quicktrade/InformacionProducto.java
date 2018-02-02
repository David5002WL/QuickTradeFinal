package com.example.david.quicktrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformacionProducto extends AppCompatActivity {

    private String uid, opcion, cambioNombre, cambioCategoria, nombreForma, categoriaForma;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    DatabaseReference bbdd, bbdd2;
    private EditText nombre1, categoria1;
    private ToggleButton modificar;
    private Button borrar;
    private RadioGroup categoriaR;
    private RadioButton tecnologia, hogar, coche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_producto);

        uid = getIntent().getStringExtra("uid");
        nombreForma = getIntent().getStringExtra("nombre");
        categoriaForma = getIntent().getStringExtra("categoria");

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        bbdd = (FirebaseDatabase.getInstance().getReference("producto").child(uid));

        //String clave = bbdd.getKey();
        //bbdd2 = (FirebaseDatabase.getInstance().getReference("producto").child(clave));

        nombre1 = (EditText) findViewById(R.id.nombre);
        categoria1 = (EditText) findViewById(R.id.categoria);
        tecnologia = (RadioButton) findViewById(R.id.tecnologia);
        coche = (RadioButton) findViewById(R.id.coche);
        hogar = (RadioButton) findViewById(R.id.hogar);
        categoriaR = (RadioGroup) findViewById(R.id.categoriaR);

        borrar = (Button) findViewById(R.id.borrar);
        modificar = (ToggleButton) findViewById(R.id.modificar);

        tecnologia.setVisibility(View.INVISIBLE);
        coche.setVisibility(View.INVISIBLE);
        hogar.setVisibility(View.INVISIBLE);
        tecnologia.setEnabled(false);
        coche.setEnabled(false);
        hogar.setEnabled(false);
        nombre1.setEnabled(false);
        categoria1.setEnabled(false);

        nombre1.setText(nombreForma);

        if(categoriaForma.equals("Tecnología")){
            categoria1.setText("Tecnología");
        }
        if(categoriaForma.equals("Hogar")){
            categoria1.setText("Hogar");
        }
        if(categoriaForma.equals("Coches")){
            categoria1.setText("Coches");
        }


        if (uid.equals(mAuth.getCurrentUser().getUid())) {
            borrar.setVisibility(View.VISIBLE);
            modificar.setVisibility(View.VISIBLE);
        } else {
            borrar.setVisibility(View.INVISIBLE);
            modificar.setVisibility(View.INVISIBLE);
        }



        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InformacionProducto.this, "Producto eliminado.", Toast.LENGTH_SHORT).show();
                bbdd.removeValue();
                Intent i = new Intent(getApplicationContext(),Perfil.class);
                startActivity(i);
            }
        });

        /*bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bindInfoProducto(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        categoriaR.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tecnologia) {
                    opcion = tecnologia.getText().toString();
                    cambioCategoria = opcion;
                } else if (checkedId == R.id.coche) {
                    opcion = coche.getText().toString();
                    cambioCategoria = opcion;
                } else if (checkedId == R.id.hogar) {
                    opcion = hogar.getText().toString();
                    cambioCategoria = opcion;
                }

            }

        });


        modificar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    nombre1.setEnabled(true);
                    tecnologia.setEnabled(true);
                    coche.setEnabled(true);
                    hogar.setEnabled(true);
                    tecnologia.setChecked(true);
                    tecnologia.setVisibility(View.VISIBLE);
                    coche.setVisibility(View.VISIBLE);
                    hogar.setVisibility(View.VISIBLE);
                    categoria1.setVisibility(View.INVISIBLE);

                } else {

                    Toast.makeText(InformacionProducto.this, "Cambios guardados.", Toast.LENGTH_SHORT).show();
                    nombre1.setEnabled(false);
                    tecnologia.setVisibility(View.INVISIBLE);
                    coche.setVisibility(View.INVISIBLE);
                    hogar.setVisibility(View.INVISIBLE);
                    categoria1.setVisibility(View.VISIBLE);

                    cambioNombre = nombre1.getText().toString();

                    bbdd.child(getString(R.string.campo_nombrep)).setValue(cambioNombre);
                    bbdd.child(getString(R.string.campo_categoria)).setValue(cambioCategoria);

                    Intent i = new Intent(getApplicationContext(), Perfil.class);
                    startActivity(i);
                }
            }
        });

    }

    /*//Aquí se rellena la información del usuario
    private void bindInfoProducto(DataSnapshot dataSnapshot) {
        Producto p = dataSnapshot.getValue(Producto.class);
        nombre1.setText(p.getNombreProducto());
        categoria1.setText(p.getCategoria());

    }
*/
}
