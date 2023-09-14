package com.example.grocera;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class CartViewModel extends AndroidViewModel {
    private LoadCart loadCart = new LoadCart();
   MutableLiveData<ArrayList<CartStructure>> cartData;
    public CartViewModel(@NonNull Application application) {
        super(application);
        cartData = loadCart.getCartData();
    }
    public MutableLiveData<ArrayList<CartStructure>> getCartData(){
        return cartData;
    }
}
