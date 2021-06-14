package com.willianhdz.crudmysql.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.willianhdz.crudmysql.Dtos.Categorias;
import com.willianhdz.crudmysql.R;
import com.willianhdz.crudmysql.ui.categorias.Categoria;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaHolder> {

    List<Categorias> listaCategorias;

    public CategoriaAdapter(List<Categorias> listaCategorias){
        this.listaCategorias = listaCategorias;
    }

    @Override
    public CategoriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias_recyclerview,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new CategoriaHolder(vista);
    }

    @Override
    public void onBindViewHolder(CategoriaHolder holder, int position) {
        Categorias dto = listaCategorias.get(position);
        holder.tvId.setText(String.valueOf(dto.getId_categoria()));
        holder.tvNombre.setText(dto.getNom_categoria());
        holder.tvEstado.setText(String.valueOf(dto.getEstado_categoria()));
        //holder.tvId.setText(listaCategorias.get(position).getId_categoria());
        //holder.tvNombre.setText(listaCategorias.get(position).getNom_categoria());
        //holder.tvEstado.setText(listaCategorias.get(position).getEstado_categoria());
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class CategoriaHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvNombre, tvEstado;

        public CategoriaHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvEstado = (TextView) itemView.findViewById(R.id.tvEstado);
        }
    }
}
