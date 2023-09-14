package com.example.grocera;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemFragment extends Fragment {


    public ItemFragment() {
        // Required empty public constructor
    }
Context context;
int command;
private ProductModel productViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        assert container != null;
        context = container.getContext();
        Bundle newBundle = getArguments();
        if(newBundle!=null){
            command = newBundle.getInt("command");
        }

        productViewModel = new ViewModelProvider(this).get(ProductModel.class);
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        switch (command){
            case 1:
                productViewModel.getDairyData().observe(getViewLifecycleOwner(), new Observer<ArrayList<DairyModel>>() {
                    @Override
                    public void onChanged(ArrayList<DairyModel> dairyModels) {
                        ItemAdapter itemAdapter = new ItemAdapter(context, dairyModels);
                        recyclerView.setAdapter(itemAdapter);
                    }
                });
                break;
            case 2:
                productViewModel.getDrinkData().observe(getViewLifecycleOwner(), new Observer<ArrayList<DrinkModel>>() {
                    @Override
                    public void onChanged(ArrayList<DrinkModel> drinkModels) {
                        DrinkAdapter drinkAdapter = new DrinkAdapter(context, drinkModels);
                        recyclerView.setAdapter(drinkAdapter);
                    }
                });
                break;
            case 3:
                productViewModel.getFrozenData().observe(getViewLifecycleOwner(), new Observer<ArrayList<FrozenModel>>() {
                    @Override
                    public void onChanged(ArrayList<FrozenModel> frozenModels) {
                        FrozenAdapter frozenAdapter = new FrozenAdapter(context, frozenModels);
                        recyclerView.setAdapter(frozenAdapter);
                    }
                });
            case 4:
                productViewModel.getSnackData().observe(getViewLifecycleOwner(), new Observer<ArrayList<SnacksModel>>() {
                    @Override
                    public void onChanged(ArrayList<SnacksModel> snacksModels) {
                        SnackAdapter snackAdapter = new SnackAdapter(context, snacksModels);
                        recyclerView.setAdapter(snackAdapter);
                    }
                });
                break;
        }
    }
}