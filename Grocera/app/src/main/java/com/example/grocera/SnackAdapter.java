package com.example.grocera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SnackAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    ArrayList<SnacksModel> itemList;
    public SnackAdapter(Context context, ArrayList<SnacksModel> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public com.example.grocera.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item,parent,false);
        return new com.example.grocera.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.grocera.ViewHolder holder, int position) {
        Glide.with(context)
                .load(itemList.get(position).getImage())
                .into(holder.product_image);
        holder.title.setText(itemList.get(position).getName());
        holder.company.setText("Company: "+itemList.get(position).getCompanyname());
//        holder.quantity.setText("Quantity: "+itemList.get(position).get);
        holder.price.setText("Price: "+itemList.get(position).getPrice()+"Rs.");
    }
    @Override
    public int getItemCount() {
        if (itemList==null)
            return 0;
        return itemList.size();
    }

}
