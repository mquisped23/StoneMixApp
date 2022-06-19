package com.edu.stonemixapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptadores.MainAdapter;
import Model.MainModel;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    DatabaseReference databaseReference;

    //instancion la clase  FloatingActionButton para el boton flotante
    FloatingActionButton floatingActionButton;

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Materiales");

    ArrayList<MainModel> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///--------------------------------------------------------///
        //esto es parte del splashScreem
        //Ponemos un thread para que la pantalla de carga dure 2 segundos
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //hacemos que despues de cargar la pantalla de carga se muestre la pantalla normal
        setTheme(R.style.Theme_StoneMixApp);

        ///--------------------------------------------------------///


        //Ahora guardo el valor de mi recycler view en la variable recyclerView
        recyclerView =  findViewById(R.id.rv);
        //Aqui hacemos que el recyclerView este en linea    r layout, y predeterminamente esto es vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainAdapter = new MainAdapter(this, lista);

        recyclerView.setAdapter(mainAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    lista.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        MainModel modelito =  new MainModel();
                        modelito = ds.getValue(MainModel.class);
                        String nombre = (String) ds.child("nombre").getValue();
                        String descripcion = (String) ds.child("descripcion").getValue();
                        String cantidad = (String) ds.child("cantidad").getValue();
                        String url = (String) ds.child("url").getValue();

                        modelito.setNombreMaterial(nombre);
                        modelito.setDescripcionMaterial(descripcion);
                        modelito.setCantidadMaterial(cantidad);
                        modelito.setUrlMaterial(url);

                        lista.add(modelito);


                    }
                    mainAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Meto el valor del valor flotante en la variable creada
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //Codigo para el boton flotante
        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Codigo al presionar que me mande al activity add
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });

    }









}