package com.example.david.quicktrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.david.quicktrade.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Todos_productos extends AppCompatActivity {

    DatabaseReference bbdd, bbdd2, clave;
    private RecyclerView recycler;
    AdaptadorProductos adaptador;
    private Spinner spinner;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    ArrayAdapter<String> comboAdapter;
    private String seleccion;
    String filtros[] = {"Ver todo", "Mis productos", "Por Categoría: Tecnología",
            "Por Categoría: Hogar", "Por Categoría: Coche"};
    private ArrayList<Producto> listado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_productos);

        spinner = (Spinner) findViewById(R.id.spinner);

        recycler = (RecyclerView) findViewById(R.id.lista);
        bbdd = FirebaseDatabase.getInstance().getReference().child("producto");

        comboAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filtros);
        //Cargo el spinner con los datos
        spinner.setAdapter(comboAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        bbdd.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
                                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                    Producto p = datasnapshot.getValue(Producto.class);
                                    listado.add(p);
                                }
                                adaptador = new AdaptadorProductos(listado);

                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Todos_productos.this, InformacionProducto.class);
                                        //Cogemos la posición, elegimos la key de esta y la mandamos a info activity
                                        i.putExtra("uid",listado.get(recycler.getChildAdapterPosition(v)).getUID().toString());
                                        i.putExtra("nombre",listado.get(recycler.getChildAdapterPosition(v)).getNombreProducto().toString());
                                        i.putExtra("categoria",listado.get(recycler.getChildAdapterPosition(v)).getCategoria().toString());
                                        startActivity(i);
                                    }
                                });

                                recycler.setAdapter(adaptador);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        recycler.setLayoutManager(new LinearLayoutManager(Todos_productos.this, LinearLayoutManager.VERTICAL, false));
                        break;

                    case 1:
                        mAuth = FirebaseAuth.getInstance();
                        Query uno = bbdd.orderByChild(getString(R.string.campo_UID)).equalTo(mAuth.getCurrentUser().getUid());
                        uno.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    Producto p = datasnapshot.getValue(Producto.class);
                                    listado.add(p);
                                }
                                adaptador = new AdaptadorProductos(listado);

                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Todos_productos.this, InformacionProducto.class);
                                        //Cogemos la posición, elegimos la key de esta y la mandamos a info activity
                                        i.putExtra("uid",listado.get(recycler.getChildAdapterPosition(v)).getUID().toString());
                                        startActivity(i);
                                    }
                                });

                                recycler.setAdapter(adaptador);

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        recycler.setLayoutManager(new LinearLayoutManager(Todos_productos.this, LinearLayoutManager.VERTICAL, false));
                        break;

                    case 2:
                        Query dos = bbdd.orderByChild(getString(R.string.campo_categoria)).equalTo("Tecnología");
                        dos.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    Producto p = datasnapshot.getValue(Producto.class);
                                    listado.add(p);
                                }
                                adaptador = new AdaptadorProductos(listado);

                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Todos_productos.this, InformacionProducto.class);
                                        //Cogemos la posición, elegimos la key de esta y la mandamos a info activity
                                        i.putExtra("uid",listado.get(recycler.getChildAdapterPosition(v)).getUID().toString());
                                        startActivity(i);
                                    }
                                });

                                recycler.setAdapter(adaptador);

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        recycler.setLayoutManager(new LinearLayoutManager(Todos_productos.this, LinearLayoutManager.VERTICAL, false));
                        break;

                    case 3:
                        Query tres = bbdd.orderByChild(getString(R.string.campo_categoria)).equalTo("Hogar");
                        tres.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    Producto p = datasnapshot.getValue(Producto.class);
                                    listado.add(p);
                                }
                                adaptador = new AdaptadorProductos(listado);

                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Todos_productos.this, InformacionProducto.class);
                                        //Cogemos la posición, elegimos la key de esta y la mandamos a info activity
                                        i.putExtra("uid",listado.get(recycler.getChildAdapterPosition(v)).getUID().toString());
                                        startActivity(i);
                                    }
                                });

                                recycler.setAdapter(adaptador);

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        recycler.setLayoutManager(new LinearLayoutManager(Todos_productos.this, LinearLayoutManager.VERTICAL, false));

                        break;
                    case 4:
                        Query cuatro = bbdd.orderByChild(getString(R.string.campo_categoria)).equalTo("Coches");
                        cuatro.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                listado.clear();
                                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                    Producto p = datasnapshot.getValue(Producto.class);
                                    listado.add(p);
                                }
                                adaptador = new AdaptadorProductos(listado);

                                adaptador.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Todos_productos.this, InformacionProducto.class);
                                        //Cogemos la posición, elegimos la key de esta y la mandamos a info activity
                                        i.putExtra("uid",listado.get(recycler.getChildAdapterPosition(v)).getUID().toString());
                                        startActivity(i);
                                    }
                                });

                                recycler.setAdapter(adaptador);

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                        recycler.setLayoutManager(new LinearLayoutManager(Todos_productos.this, LinearLayoutManager.VERTICAL, false));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}



        /*mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();

        mDatabase = database.getReference()(FirebaseR)

        // Obtenemos una referencia a la base de datos
        mDatabase = FirebaseDatabase.getInstance().getReference() //getReference nos proporciona una referencia al nodo raiz
                .child(getString(R.string.nodo_producto)); // bajamos al nodo productos



        mDatabase = FirebaseDatabase.getInstance().getReference();

        RecyclerView rv = (RecyclerView) findViewById(R.id.lista);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        List<Producto> listaproducto = Arrays.asList(new Producto(R.mipmap.ic_launcher, "David"),
                new Producto(R.mipmap.ic_launcher, "Dav"));

        AdaptadorProductos adapter = new AdaptadorProductos(listaproducto);
        rv.setAdapter(adapter);
        */






