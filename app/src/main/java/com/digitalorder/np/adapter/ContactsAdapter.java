package com.digitalorder.np.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.DetailActivity;
import com.digitalorder.np.Onclickfood;
import com.digitalorder.np.R;
import com.digitalorder.np.model.Contacts;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{

    Context context;
    List<Contacts> contactsList;

    public ContactsAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
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
        final Contacts contacts = contactsList.get(position);
        holder.imgProfile.setImageResource(contacts.getImageId());
        holder.tvName.setText(contacts.getName());
        holder.tvPrice.setText(contacts.getPrice());
        holder.tvCategory.setText(contacts.getCategory());

        //Adding click listener in an imageview;
        holder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, Onclickfood.class);
                intent.putExtra("Image", contacts.getImageId());
                intent.putExtra("Name", contacts.getName());
                intent.putExtra("Price", contacts.getPrice());
                intent.putExtra("Category", contacts.getCategory());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
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
