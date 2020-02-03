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
import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.strictmode.StrictModeClass;
import com.digitalorder.np.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ContactsViewHolder>{

    Context context;
    List<OrderMod> orderModList;

    public OrderAdapter(Context context, List<OrderMod> orderModList) {
        this.context = context;
        this.orderModList = orderModList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_view,parent,false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
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
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderModList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView tvName,tvPrice,tvCategory;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgFood);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
