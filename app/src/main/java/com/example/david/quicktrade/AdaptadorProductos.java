package com.example.david.quicktrade;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.david.quicktrade.model.Producto;

import java.util.List;

/**
 * Created by David on 30/01/2018.
 */

public class AdaptadorProductos
        extends RecyclerView.Adapter<AdaptadorProductos.ProductViewHolder>
        implements View.OnClickListener {
    private List<Producto> items;
    private View.OnClickListener listener;


    public AdaptadorProductos(List<Producto> items) {
        this.items = items;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.plantilla_productos, viewGroup, false);
        v.setOnClickListener(this);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
        Producto item = items.get(i);
        viewHolder.bindProducto(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagenProducto;
        public TextView nombreProducto, categoriaProducto, UID;


        public ProductViewHolder(View v) {
            super(v);
            imagenProducto = (ImageView) v.findViewById(R.id.imagenProducto);
            nombreProducto = (TextView) v.findViewById(R.id.NombreProducto);
            categoriaProducto = (TextView) v.findViewById(R.id.CategoriaProducto);
            UID = (TextView) v.findViewById(R.id.UID);



        }

        public void bindProducto(Producto item) {
            imagenProducto.setImageResource(item.getImagen_producto());
            nombreProducto.setText(item.getNombreProducto());
            categoriaProducto.setText(item.getCategoria());
            UID.setText(item.getUID());

        }
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}



/*public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ProductoviewHolder> {

    List<Producto> listaproducto;

    public AdaptadorProductos(List<Producto> listaproducto) {
        this.listaproducto = listaproducto;
    }

    @Override
    public ProductoviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_productos, parent, false);
        ProductoviewHolder holder = new ProductoviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductoviewHolder holder, int position) {
        holder.NombreProducto.setText(listaproducto.get(position).getNombreProducto());
        holder.CategoriaProducto.setText(listaproducto.get(position).getCategoria());
        holder.UID.setText(listaproducto.get(position).getUID());
        holder.imagenProducto.setImageResource(listaproducto.get(position).getImagen_producto());

    }

    @Override
    public int getItemCount() {
        return listaproducto.size();
    }

    public static class ProductoviewHolder extends RecyclerView.ViewHolder{

        TextView NombreProducto, CategoriaProducto, UID;
        ImageView imagenProducto;

        public ProductoviewHolder(View itemView){
            super(itemView);

            NombreProducto = (TextView) itemView.findViewById(R.id.NombreProducto);
            CategoriaProducto = (TextView) itemView.findViewById(R.id.CategoriaProducto);
            UID = (TextView) itemView.findViewById(R.id.UID);
            imagenProducto = (ImageView) itemView.findViewById(R.id.imagenProducto);

        }
    }
}*/
