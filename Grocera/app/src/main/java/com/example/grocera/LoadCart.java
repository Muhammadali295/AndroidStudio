package com.example.grocera;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadCart {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    MutableLiveData<ArrayList<CartStructure>> cartList;
    public MutableLiveData<ArrayList<CartStructure>> getCartData(){
        if(cartList==null)
            cartList = new MutableLiveData<>();
       loadCartFromFirebase();
    return cartList; }
    private void loadCartFromFirebase(){
        ArrayList<CartStructure> cartStructureArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Cart").child(UserId.getUserId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot cartSnapshot:snapshot.getChildren()){
                                CartStructure cartStructure = cartSnapshot.getValue(CartStructure.class);
                                assert cartStructure!=null;
                                cartStructureArrayList.add(cartStructure);
                            }
                            cartList.setValue(cartStructureArrayList);
                            double sum = 0;
                            for(CartStructure cartStructure: cartStructureArrayList)
                            {
                                sum+=cartStructure.getTotal_price();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
