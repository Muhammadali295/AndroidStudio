package com.example.grocera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
   Context context;
   ArrayList<DairyModel> itemList;
public ItemAdapter(Context context, ArrayList<DairyModel> itemList){
    this.context = context;
    this.itemList = itemList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                        .load(itemList.get(position).getImage())
                                .into(holder.product_image);
        holder.title.setText(itemList.get(position).getName());
        holder.company.setText("Company: "+itemList.get(position).getCompanyname());
//        holder.quantity.setText("Quantity: "+itemList.get(position).);
        holder.price.setText("Price: "+itemList.get(position).getPrice()+"Rs.");
        holder.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        new AddToCart().addToCart(itemList.get(position).getName(),itemList.get(position).getPrice(),itemList.get(position).getCompanyname(),itemList.get(position).getImage());
                        System.out.println(position);
//                        Toast.makeText(context, "Item added to cart successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
    if (itemList==null)
        return 0;
    return itemList.size();
    }
}
