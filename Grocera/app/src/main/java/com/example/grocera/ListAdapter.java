package com.example.grocera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends ArrayAdapter<CartStructure>{
    Context context;
    int resourceLayout;
    public ListAdapter(@NonNull Context context, int resource, @NonNull List<CartStructure> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(context).inflate(resourceLayout,null);
        }
        CartStructure cartStructure= getItem(position);
        if(cartStructure!=null){
            ImageView imageView = view.findViewById(R.id.item_pic);
            TextView textView = view.findViewById(R.id.price_id);
            TextView textView1 = view.findViewById(R.id.company_id);
            TextView textView2 = view.findViewById(R.id.qty);
            Glide.with(context)
                    .load(cartStructure.getImage())
                    .into(imageView);
            textView.setText(cartStructure.getPrice());
            textView1.setText(cartStructure.getCompany_name());
            textView2.setText(cartStructure.getQuantity());
        }
        return view;
    }
}
