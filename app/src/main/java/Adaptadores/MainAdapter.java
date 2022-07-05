package Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.edu.stonemixapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.lang.ref.Reference;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

import Model.MainModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.myViewHolder> {

    ArrayList<MainModel> lista;
    Context context;

    public MainAdapter(Context context,ArrayList<MainModel> lista ) {
        this.context = context;
        this.lista = lista;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //en este apartado estoy haciendo que se pueda mostrar el layout main_item con el inflate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    //-no va -public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) { super(options); }

    @Override
    //public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        MainModel model  = lista.get(position);

        holder.nombrePro.setText(model.getNombreMaterial());
        holder.descripcionPro.setText(model.getDescripcionMaterial());
        holder.cantidadPro.setText(model.getCantidadMaterial());

        Glide.with(holder.img.getContext())
                .load(model.getUrlMaterial())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);
        //---
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();
            //dialogPlus.show()
                View view = dialogPlus.getHolderView();

                EditText nombrePro = view.findViewById(R.id.txtNombreMaterial);
                EditText cantidadPro = view.findViewById(R.id.txtCantidadMaterial);
                EditText descripcionPro = view.findViewById(R.id.txtDescripcionMaterial);
                EditText urlMaterial = view.findViewById(R.id.txtUrlMaterial);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                nombrePro.setText(model.getNombreMaterial());
                cantidadPro.setText(model.getCantidadMaterial());
                descripcionPro.setText(model.getDescripcionMaterial());
                urlMaterial.setText(model.getUrlMaterial());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("nombrePro",nombrePro.getText().toString());
                        map.put("cantidadPro",cantidadPro.getText().toString());
                        map.put("descripcionPro",descripcionPro.getText().toString());
                        map.put("urlMaterial",urlMaterial.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("materiales")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nombrePro.getContext(), "Data Update Succesfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.nombrePro.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

            }
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }




    public static class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nombrePro, descripcionPro, cantidadPro;

        Button btnEdit, btnDelete;

        //Constructor de myViewHolder
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img =  itemView.findViewById(R.id.img1);
            nombrePro  =  itemView.findViewById(R.id.nametext);
            descripcionPro  =  itemView.findViewById(R.id.descripciontext);
            cantidadPro  =  itemView.findViewById(R.id.cantidadtext);
            //botones
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }

}
