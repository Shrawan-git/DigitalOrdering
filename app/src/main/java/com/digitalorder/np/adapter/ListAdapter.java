package com.digitalorder.np.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.Onclickfood;
import com.digitalorder.np.R;
import com.digitalorder.np.model.Product;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        final Product product = productList.get(position);

        holder.tvName.setText(product.getFoodName());
        holder.tvPrice.setText(product.getFoodPrice());
        holder.tvCategory.setText(product.getFoodCategory());

    }
    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class OrdersViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvPrice,tvCategory;
        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
