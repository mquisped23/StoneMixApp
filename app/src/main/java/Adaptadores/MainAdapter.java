package Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.edu.stonemixapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

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

    @Override
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

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }




    public static class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nombrePro, descripcionPro, cantidadPro;
        //Constructor de myViewHolder
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img =  itemView.findViewById(R.id.img1);
            nombrePro  =  itemView.findViewById(R.id.nametext);
            descripcionPro  =  itemView.findViewById(R.id.descripciontext);
            cantidadPro  =  itemView.findViewById(R.id.cantidadtext);

        }
    }

}
