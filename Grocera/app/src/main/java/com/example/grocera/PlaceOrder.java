package com.example.grocera;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class PlaceOrder extends Fragment {

TextView custName, custContact, custAddress;
String getName, getContact, getAddress;
Toolbar toolbar;
Button placeOrder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_place_order, container, false);
        custName = view.findViewById(R.id.custName);
        custContact = view.findViewById(R.id.custContact);
        custAddress = view.findViewById(R.id.custAddress);
        placeOrder = view.findViewById(R.id.placeOrder);
        Bundle getBundle = getArguments();
        if (getBundle!=null){
            getName = getBundle.getString("Name");
            getContact = getBundle.getString("Contact");
            getAddress = getBundle.getString("Address");
            custName.setText(getName);
            custContact.setText(getContact);
            custAddress.setText(getAddress);
        }

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, OrderPlaced.class, null)
                        .commit();
            }
        });
        return view;

    }
}