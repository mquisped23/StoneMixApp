package com.edu.stonemixapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adaptadores.MainAdapter;
import Model.MainModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    DatabaseReference databaseReference;
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


        FirebaseRecyclerOptions<MainModel> options = new FirebaseRecyclerOptions.Builder<MainModel>()
                .setQuery(FirebaseDatabase.getInstance("https://stonemix-f5c00-default-rtdb.firebaseio.com/")
                        .getReference().child("Materiales"), MainModel.class)
                .build();

        for (MainModel m : lista){
            System.out.println("respuesta"+m.getNombreMaterial());
            System.out.println(m.getDescripcionMaterial());
            System.out.println(m.getCantidadMaterial());
            System.out.println(m.getUrlMaterial());
        }

        //Metodo




        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        listarDatos();
    }

    public void listarDatos() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Materiales").addValueEventListener(new ValueEventListener() {
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                lista.clear();
                                for (DataSnapshot ds : snapshot.getChildren()) {

                                    String nombre = ds.child("nombre").getValue().toString();
                                    String descripcion = ds.child("descripcion").getValue().toString();
                                    String categoria = ds.child("cantidad").getValue().toString();
                                    String url = ds.child("url").getValue().toString();

                                    lista.add(new MainModel( "nombre","descripcion","categoria","url"));

                                }
                            }

                        }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





    @Override
    protected  void onStart(){
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected  void onStop(){
        super.onStop();
        mainAdapter.stopListening();
    }




}