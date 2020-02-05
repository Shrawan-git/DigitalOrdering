package com.digitalorder.np.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.R;
import com.digitalorder.np.model.Product;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>{
        Context context;
        List<Product> productList;

        public FavouriteAdapter(Context context, List<Product> productList) {
            this.context = context;
            this.productList = productList;
        }

        @NonNull
        @Override
        public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.favourite_view,parent,false);
            return new FavouriteViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
            final Product product = productList.get(position);

            holder.tvName.setText(product.getFoodName());
            holder.tvPrice.setText(product.getFoodPrice());
            holder.tvCategory.setText(product.getFoodCategory());

        }
        @Override
        public int getItemCount() {
            return productList.size();
        }


        public class FavouriteViewHolder extends RecyclerView.ViewHolder{

            TextView tvName,tvPrice,tvCategory;
            public FavouriteViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvName);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvCategory = itemView.findViewById(R.id.tvCategory);
            }
        }
    }


