package com.example.storedam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.storedam.databinding.FragmentGalleryBinding;
import com.example.storedam.databinding.FragmentProductoBinding;

import org.xmlpull.v1.XmlPullParser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductoFragment extends Fragment {
    private Button btn_cancelar_agregar_producto;
    private Button btn_agregar_producto;
    private FragmentProductoBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductoFragment newInstance(String param1, String param2) {
        ProductoFragment fragment = new ProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_producto, container, false);

        binding = FragmentProductoBinding.inflate(inflater, container, false);
        //View myView = inflater.inflate(R.layout.fragment_producto, container, false);
        View root = binding.getRoot();

        btn_cancelar_agregar_producto = root.findViewById(R.id.btn_cancelar_Agregar_producto);
        btn_agregar_producto=root.findViewById(R.id.btn_agregar_producto);



        /*btn_cancelar_agregar_producto.setOnClickListener();
        btn_agregar_producto.setOnClickListener(ProductoFragment;*/

return root;

}}