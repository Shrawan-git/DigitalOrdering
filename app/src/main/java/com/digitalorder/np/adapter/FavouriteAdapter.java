package com.digitalorder.np.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.Onclickfood;
import com.digitalorder.np.R;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Product;
import com.digitalorder.np.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        public void onBindViewHolder(@NonNull final FavouriteViewHolder holder, int position) {
            final Product product = productList.get(position);

            holder.tvName.setText(product.getFoodName());
            holder.tvPrice.setText(product.getFoodPrice());
            holder.tvCategory.setText(product.getFoodCategory());
            holder.favorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String foodName = holder.tvName.getText().toString().trim();
                    String foodPrice = holder.tvPrice.getText().toString().trim();
                    String foodCategory = holder.tvCategory.getText().toString().trim();

                    Product product = new Product(foodName, foodPrice, foodCategory);

                    UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                    Call<Void> orderCall = usersAPI.orderUser(Url.token, product);

                    orderCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(context, "" + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            });
        }
        @Override
        public int getItemCount() {
            return productList.size();
        }
        public class FavouriteViewHolder extends RecyclerView.ViewHolder{

            TextView tvName,tvPrice,tvCategory;
            Button favorder;
            public FavouriteViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvName);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvCategory = itemView.findViewById(R.id.tvCategory);
                favorder = itemView.findViewById(R.id.favorder);
            }
        }
    }


