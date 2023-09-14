package com.example.grocera;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    TextView title, company, quantity, price;
    ImageView product_image;
    Button addCart;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.product_name);
        company = itemView.findViewById(R.id.product_cmp);
        quantity = itemView.findViewById(R.id.product_qty);
        price = itemView.findViewById(R.id.product_prc);
        product_image = itemView.findViewById(R.id.product_image);
        addCart = itemView.findViewById(R.id.addCart);
    }
}
