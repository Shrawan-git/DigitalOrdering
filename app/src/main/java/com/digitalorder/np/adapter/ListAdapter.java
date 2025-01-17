package com.digitalorder.np.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
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

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.OrdersViewHolder>{

    Context context;
    List<Product> productList;

    public ListAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ListAdapter.OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_view,parent,false);
        return new OrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, final int position) {
        final Product product = productList.get(position);

        holder.tvName.setText(product.getFoodName().trim());
        holder.tvPrice.setText(product.getFoodPrice());
        holder.tvCategory.setText(product.getFoodCategory());

        holder.orderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<Product>> voidCall = usersAPI.orderUserDelete(Url.token, product.getFoodName());

                voidCall.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, "deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "" + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });
            }
        });

    }
    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class OrdersViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvPrice,tvCategory;
        Button orderDelete;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            orderDelete = itemView.findViewById(R.id.orderDelete);
        }
    }
}
