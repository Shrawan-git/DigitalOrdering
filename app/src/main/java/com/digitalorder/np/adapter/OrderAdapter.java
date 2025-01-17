package com.digitalorder.np.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.Onclickfood;
import com.digitalorder.np.OrderRating;
import com.digitalorder.np.R;
import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrdersViewHolder>{
    Context context;
    List<OrderMod> orderModList;

    public OrderAdapter(Context context, List<OrderMod> orderModList) {
        this.context = context;
        this.orderModList = orderModList;
    }
    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_view,parent,false);
        return new OrdersViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        final OrderMod orderMod = orderModList.get(position);

        String imgPath = Url.imagePath + orderMod.getImage();
        StrictModeClass.StrictMode();
        try{
            URL url = new URL(imgPath);
            holder.imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.tvName.setText(orderMod.getFoodName());
        holder.tvPrice.setText(orderMod.getFoodPrice());
        holder.tvCategory.setText(orderMod.getFoodCategory());

        //Adding click listener in an imageview;
        holder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, Onclickfood.class);
                intent.putExtra("Image", orderMod.getImage());
                intent.putExtra("Name", orderMod.getFoodName());
                intent.putExtra("Price", orderMod.getFoodPrice());
                intent.putExtra("Category", orderMod.getFoodCategory());
                intent.putExtra("Description", orderMod.getFoodDescription());
                context.startActivity(intent);
            }
        });

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderRating.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return orderModList.size();
    }

    public class OrdersViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView tvName,tvPrice,tvCategory;
        private ImageButton imageButton;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgFood);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            imageButton = itemView.findViewById(R.id.imageButton);
        }
    }
}
