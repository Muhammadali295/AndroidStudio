package com.example.grocera;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    ListView preview;
    private CartViewModel cartViewModel;
    Context context;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        assert container != null;
        context = container.getContext();
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preview = view.findViewById(R.id.preview);
        cartViewModel.getCartData().observe(getViewLifecycleOwner(), new Observer<ArrayList<CartStructure>>() {
            @Override
            public void onChanged(ArrayList<CartStructure> cartStructures) {
                ListAdapter adapter = new ListAdapter(context, R.layout.objcard, cartStructures);
                preview.setAdapter(adapter);
            }
        });
    }
}