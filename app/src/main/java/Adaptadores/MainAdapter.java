package Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edu.stonemixapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import Model.MainModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.nombrePro.setText(model.getNombreMaterial());
        holder.descripcionPro.setText(model.getDescripcionMaterial());
        holder.cantidadPro.setText(model.getCantidadMaterial());
        //Debo investigar esto para que sirve
        Glide.with(holder.img.getContext())
                .load(model.getUrlMaterial())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //en este apartado estoy haciendo que se pueda mostrar el layout main_item con el inflate
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
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
