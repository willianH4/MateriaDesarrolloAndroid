package com.willianhdz.crud_sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorArticulos extends
 RecyclerView.Adapter<AdaptadorArticulos.ArticulosViewHolder>{

 //Propiedad de la clase AdaptadorArticulos.
 private Context mCtx;
 private List<Dto> articulosList;

//Añadimos un contructor a partir de las propiedades definidas en esta clase
//(AdaptorArticulos).
    public AdaptadorArticulos(Context mCtx, List<Dto> articulosList) {
        this.mCtx = mCtx;
        this.articulosList = articulosList;
    }

    /***********************************************************************************************/
/*ReccylerView.Adapter tiene tres métodos abstractos a los que debemos aplicar el
modificador override.
/***********************************************************************************************/
/*
El siguiente método es llamado cuando el ViewHolder necesita ser inicializado.
Especificamos el layout que cada elemento de RecyclerView debería usar.
Ésto se hace al inflar el layout usando LayoutInflater, pasando el resultado
al constructor del ViewHolder
*/

    @Override
    public ArticulosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_recyclerview, null);
        return new ArticulosViewHolder(view);
    }

    /*
        En el mètodo onBindViewHolder se especifica el contenido de cada elemento del
        RecyclerView.
        Éste método es muy similar al método getView de un adaptador de ListView.
        En nuestro ejemplo, aquí es donde se tiene que establecer los valores de los campos de
        codigo, descripcion y precio del CardView.
        */

    @Override
    public void onBindViewHolder(ArticulosViewHolder holder, int position) {
    //Toast.makeText(mCtx, "Se encontraron: "+getItemCount()+ " Registros.",
   //     Toast.LENGTH_SHORT).show();
        Dto dto = articulosList.get(position);
        holder.textViewCodigo1.setText(String.valueOf(dto.getCodigo()));
        holder.textViewDescripcion1.setText(dto.getDescripcion());
        holder.textViewPrecio1.setText(String.valueOf(dto.getPrecio()));
        holder.textViewOtro.setText(String.valueOf("Registro #:" + (position+1)) + "/" +
        getItemCount());
    }

    /*
    Éste mètodo regresara el número de elementos presentes en los datos. Como nuestro
    datos están en forma de una List,
    sólo necesitamos llamar al método size en el objeto List:
    */

    @Override
    public int getItemCount() {
        return articulosList.size();
    }
 //Dentro de esta clase se define el mapeo o referencia de cada control
//en la vista (Layout o xml) Personalizado. Este es el diseño personalizado para pintar
//    cada registro en el recyclerview.

    public static class ArticulosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodigo1, textViewDescripcion1, textViewPrecio1, textViewOtro;
        //ImageView imageView;

        //Dentro del constructor de nuestro ViewHolder, inicializa las views (vistas)
        //que pertenezcan a los elementos de nuestro RecyclerView.

        public ArticulosViewHolder(View itemView) {
            super(itemView);
            textViewCodigo1 = itemView.findViewById(R.id.textViewCodigo1);
            textViewDescripcion1 = itemView.findViewById(R.id.textViewDescripcion1);
            textViewPrecio1= itemView.findViewById(R.id.textViewPrecio1);
            textViewOtro = itemView.findViewById(R.id.textViewOtro);
            //imageView = itemView.findViewById(R.id.imageView);
        }
    }

    /*
    Finalmente, necesitamos hacer el override en el método onAttachedToRecyclerView.
    Por ahora, simplemente podemos utilizar la implementación de la superclase de éste
    método
    */

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

