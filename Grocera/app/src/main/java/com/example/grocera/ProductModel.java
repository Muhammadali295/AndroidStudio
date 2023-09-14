package com.example.grocera;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ProductModel extends AndroidViewModel {
    private LoadProducts loadProducts = new LoadProducts();
    private MutableLiveData<ArrayList<DrinkModel>> drinkData;
    private MutableLiveData<ArrayList<DairyModel>> dairyData;
    private MutableLiveData<ArrayList<FrozenModel>> frozenData;
    private MutableLiveData<ArrayList<SnacksModel>> snackData;
    public ProductModel(@NonNull Application application) {
        super(application);
        drinkData = loadProducts.getDrinkModel();
        dairyData = loadProducts.getDairyModel();
        frozenData = loadProducts.getFrozenModels();
        snackData = loadProducts.getSnacksModels();
    }
    public MutableLiveData<ArrayList<DrinkModel>> getDrinkData(){
        return drinkData;
    }

    public MutableLiveData<ArrayList<DairyModel>> getDairyData() {
        return dairyData;
    }

    public MutableLiveData<ArrayList<SnacksModel>> getSnackData() {
        return snackData;
    }

    public MutableLiveData<ArrayList<FrozenModel>> getFrozenData() {
        return frozenData;
    }
}
