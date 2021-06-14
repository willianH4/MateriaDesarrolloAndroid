package com.mjgl.demomysql2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.ProductViewHolder> {

    private Context mCtx;
    private List<dto_catprod> productList;

    public ProductAdapter1(Context mCtx, List<dto_catprod> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.rv_consulta1, null);
        return new ProductViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter1.ProductViewHolder holder, int position) {
        dto_catprod datos = productList.get(position);

        holder.textViewNombre1.setText(datos.getId_producto() + ":" + datos.getNombre_producto());
        holder.textViewDescripcion1.setText(datos.getDes_producto());
        holder.textViewPrecio1.setText(String.valueOf(datos.getPrecio()));
        holder.textViewStock1.setText(String.valueOf(datos.getStock()));
        holder.textViewCategoria1.setText(String.valueOf(datos.getCategoria()) + ":" + datos.getNombre_categoria());
        holder.textViewFecha.setText(datos.getFecha());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre1, textViewDescripcion1, textViewPrecio1, textViewStock1, textViewCategoria1, textViewFecha;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre1 = itemView.findViewById(R.id.textViewNombre1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
            textViewPrecio1 = itemView.findViewById(R.id.textViewPrecio1);
            textViewStock1 = itemView.findViewById(R.id.textViewStock1);
            textViewCategoria1 = itemView.findViewById(R.id.textViewCategoria1);
            textViewFecha = itemView.findViewById(R.id.textViewFecha);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}













//Autor: Prof. Manuel de Jesús Gámez López


