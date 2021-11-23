package com.example.storedam.ui.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedam.R;
import com.example.storedam.databinding.FragmentGalleryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GalleryFragment extends Fragment {

    private TextView tev_galeria;
    private Spinner spn_categorias;

    private String productos = "[{\"nombre\":\"XBOX ONE\",\"categoria\":\"Consolas\",\"precio\":1550000,\"enStock\":true},{\"nombre\":\"Nintendo Switch\",\"categoria\":\"Consolas\",\"precio\":1649000,\"enStock\":true},{\"nombre\":\"PS4\",\"categoria\":\"Consolas\",\"precio\":1699000,\"enStock\":true},{\"nombre\":\"PS5\",\"categoria\":\"Consolas\",\"precio\":3769000,\"enStock\":true}]";

    private RecyclerView rev_productos;
    private RecyclerView.Adapter mAdapter;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



//Inicio Spinner
        //tev_galeria = root.findViewById(R.id.tev_galeria);
        //tev_galeria.setText("hola mundo");

       // spn_categorias = root.findViewById(R.id.spn_categorias);

      /*  String[] categorias = new String[]{"Beisbol", "Atletismo", "Karate", "Salto triple"};

       ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,
                categorias);
        spn_categorias.setAdapter(adaptador);*/


    /*    spn_categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoria = spn_categorias.getSelectedItem().toString();
                tev_galeria.setText(categoria);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/// Fin Spinner

        rev_productos= root.findViewById(R.id.rev_productos);
        rev_productos.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rev_productos.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        try {
            JSONArray jsonProductos = new JSONArray(productos);

            mAdapter = new ProductosAdapter(jsonProductos, getActivity());
            rev_productos.setAdapter(mAdapter);
            JSONObject producto0 = jsonProductos.getJSONObject(0);
            String nombre = producto0.getString("nombre");
            Toast.makeText(getActivity(), "Nombre: "+ nombre, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private JSONArray productos;
    private Activity miActividad;

    public ProductosAdapter(JSONArray productos, Activity miActividad) {
        this.productos = productos;
        this.miActividad = miActividad;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_productos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            Log.e("POSICION", "POS: " + position);
            String nombre = this.productos.getJSONObject(position).getString("nombre");
            String categoria = this.productos.getJSONObject(position).getString("categoria");
            int precio = this.productos.getJSONObject(position).getInt("precio");
            /*String imagen = this.productos.getJSONObject(position).getString("imagen");*/

            holder.tev_item_name.setText(nombre);
            holder.tev_item_categoria.setText(categoria);
            holder.tev_item_precio.setText("$" + precio);

           /* if (imagen.equals("picasso")) {

                holder.imv_item_producto.setImageResource(miActividad.getResources().getIdentifier(imagen, "drawable", miActividad.getPackageName()));

                //holder.imv_item_producto.setImageDrawable(miActividad.getDrawable(R.drawable.picasso));
            } else {
                Glide.with(miActividad)
                        .load(imagen)
                        .placeholder(new ColorDrawable(Color.BLACK))
                        .into(holder.imv_item_producto);
            }*/


        } catch (JSONException e) {
            holder.tev_item_name.setText("error");
        }

    }

    @Override
    public int getItemCount() {
        Log.e("CANTIDAD_PRODUCTOS", "" + this.productos.length());
        return this.productos.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tev_item_name;
        private TextView tev_item_categoria;
        private TextView tev_item_precio;
        private ImageView imv_item_producto;
        public ViewHolder(View v) {
            super(v);
            tev_item_name = v.findViewById(R.id.tev_item_name);
            tev_item_categoria = v.findViewById(R.id.tev_item_categoria);
            tev_item_precio = v.findViewById(R.id.tev_item_precio);
           /* imv_item_producto = v.findViewById(R.id.imv_item_producto);*/

        }
    }
}
