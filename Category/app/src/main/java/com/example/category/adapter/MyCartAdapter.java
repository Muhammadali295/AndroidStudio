package com.example.category.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.category.R;
import com.example.category.model.CartModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder> {
    private Context context;
    private List<CartModel> cartModelList;

    public MyCartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;

    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCartViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.cart_row,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {
        Glide.with(context)
                .load(cartModelList.get(position).getImage())
                .into(holder.cartimg);
        holder.cartprice.setText(new StringBuilder("$").append(cartModelList.get(position).getPrice()));
        holder.companyname.setText(new StringBuilder().append(cartModelList.get(position).getCompanyname()));
        holder.productname.setText(new StringBuilder().append(cartModelList.get(position).getName()));
        holder.quantity.setText(new StringBuilder().append(cartModelList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cartimg)
        ImageView cartimg;
        @BindView(R.id.company_name)
        TextView companyname;
        @BindView(R.id.product_name)
        TextView productname;
        @BindView(R.id.cartprice)
        TextView cartprice;
        @BindView(R.id.quantity)
        TextView quantity;

        Unbinder unbinder;
        public MyCartViewHolder(@NonNull View itemView){
            super(itemView);
            unbinder= ButterKnife.bind(this,itemView);
        }
    }
}
