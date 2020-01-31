package com.digitalorder.np.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.R;
import com.digitalorder.np.adapter.ContactsAdapter;
import com.digitalorder.np.model.OrderMod;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView viewR;
    private Button pizzaButton, burgerButton, saladButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        viewR = root.findViewById(R.id.recycleV);
        pizzaButton = root.findViewById(R.id.pizzaButton);
        burgerButton = root.findViewById(R.id.burgerButton);
        saladButton = root.findViewById(R.id.saladButton);

        pizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a list of contacts to display in RecyclerView
                List<OrderMod> orderModList = new ArrayList<>();
                // Adding all the contacts object in list
                orderModList.add(new OrderMod("Salami Pizza" ,"$550", "Pizza", R.drawable.pizza));
                orderModList.add(new OrderMod("Bacon Pizza" ,"$500", "Pizza",R.drawable.pizza1));
                orderModList.add(new OrderMod("Salami Pizza" ,"$550", "Pizza",R.drawable.pizza));


                ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), orderModList);
                viewR.setAdapter(contactsAdapter);

                //Display all the contacts in linear layour (vertically)
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });

        burgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a list of contacts to display in RecyclerView
                List<OrderMod> orderModList = new ArrayList<>();
                // Adding all the contacts object in list
                orderModList.add(new OrderMod("veggie burger" ,"$550", "Burger",R.drawable.burger));
                orderModList.add(new OrderMod("ham burger" ,"$500", "Burger",R.drawable.burger));
                orderModList.add(new OrderMod("Salami burger" ,"$550", "Burger",R.drawable.burger));

                ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), orderModList);
                viewR.setAdapter(contactsAdapter);

                //Display all the contacts in linear layour (vertically)
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
        });
        saladButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a list of contacts to display in RecyclerView
                List<OrderMod> orderModList = new ArrayList<>();
                // Adding all the contacts object in list
                orderModList.add(new OrderMod("veggie salad" ,"$550", "Salad",R.drawable.salad));
                orderModList.add(new OrderMod("mix salad" ,"$500", "Salad",R.drawable.salad1));
                orderModList.add(new OrderMod("fresh salad" ,"$550", "Salad",R.drawable.salad));

                ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(), orderModList);
                viewR.setAdapter(contactsAdapter);

                //Display all the contacts in linear layour (vertically)
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
        });
        return root;
    }
}