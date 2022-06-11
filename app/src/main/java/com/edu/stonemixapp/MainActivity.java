package com.edu.stonemixapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Adaptadores.MainAdapter;
import Model.MainModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    DatabaseReference databaseReference;
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
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        //Aqui hacemos que el recyclerView este en linea    r layout, y predeterminamente esto es vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MainModel> options = new FirebaseRecyclerOptions.Builder<MainModel>()
                .setQuery(FirebaseDatabase.getInstance("https://stonemix-f5c00-default-rtdb.firebaseio.com/")
                        .getReference("Materiales"), MainModel.class)
                .build();


        //Metodo




        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);


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