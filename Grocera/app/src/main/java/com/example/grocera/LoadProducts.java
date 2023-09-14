package com.example.grocera;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadProducts {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    public LoadProducts(){}
    MutableLiveData<ArrayList<DrinkModel>> drinkModels;
MutableLiveData<ArrayList<DairyModel>> dairyModels;
MutableLiveData<ArrayList<SnacksModel>> snacksModels;
MutableLiveData<ArrayList<FrozenModel>> frozenModels;
    public androidx.lifecycle.MutableLiveData<ArrayList<DrinkModel>> getDrinkModel() {
        if (drinkModels==null)
            drinkModels = new MutableLiveData<>();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loadDrinksFromFirebase();

            }
        });
        return drinkModels;
    }

    public MutableLiveData<ArrayList<DairyModel>> getDairyModel() {
    if (dairyModels==null)
        dairyModels = new MutableLiveData<>();
    executor.execute(new Runnable() {
        @Override
        public void run() {
            loadDairyFromFirebase();
        }
    });
    return dairyModels;
    }

    public MutableLiveData<ArrayList<FrozenModel>> getFrozenModels() {
    if (frozenModels==null)
        frozenModels = new MutableLiveData<>();
    executor.execute(new Runnable() {
        @Override
        public void run() {
            loadFrozenFromFirebase();

        }
    });
        return frozenModels;
    }

    public MutableLiveData<ArrayList<SnacksModel>> getSnacksModels() {
    if (snacksModels==null)
        snacksModels = new MutableLiveData<>();
    executor.execute(new Runnable() {
        @Override
        public void run() {
            loadSnacksFromFirebase();

        }
    });
        return snacksModels;
    }

    private void loadDrinksFromFirebase(){
    ArrayList<DrinkModel> newDrinkModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot drinkSnapshot : snapshot.getChildren()) {
                                DrinkModel drinkModel = drinkSnapshot.getValue(DrinkModel.class);
                                newDrinkModels.add(drinkModel);
                            }
                            drinkModels.setValue(newDrinkModels);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Error", "Can't find drink");
                    }
                });
    }
    private void loadDairyFromFirebase(){
        ArrayList<DairyModel> newDairyModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Dairy")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot drinkSnapshot : snapshot.getChildren()) {
                                DairyModel dairyModel = drinkSnapshot.getValue(DairyModel.class);
                               newDairyModels.add(dairyModel);
                            }
                            dairyModels.setValue(newDairyModels);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Error", "Can't find drink");
                    }
                });
    }
    private void loadSnacksFromFirebase(){
        ArrayList<SnacksModel> newSnackModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Snacks")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot drinkSnapshot : snapshot.getChildren()) {
                                SnacksModel snacksModel = drinkSnapshot.getValue(SnacksModel.class);
                                newSnackModels.add(snacksModel);
                            }
                            snacksModels.setValue(newSnackModels);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Error", "Can't find drink");
                    }
                });
    }
    private void loadFrozenFromFirebase(){
        ArrayList<FrozenModel> newFrozenModel = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Frozenitems")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot drinkSnapshot : snapshot.getChildren()) {
                                FrozenModel frozenModel = drinkSnapshot.getValue(FrozenModel.class);
                                newFrozenModel.add(frozenModel);
                            }
                            frozenModels.setValue(newFrozenModel);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Error", "Can't find drink");
                    }
                });
    }
}