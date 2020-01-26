package com.digitalorder.np.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.R;
import com.digitalorder.np.adapter.ContactsAdapter;
import com.digitalorder.np.model.Contacts;

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
                List<Contacts> contactsList = new ArrayList<>();
                // Adding all the contacts object in list
                contactsList.add(new Contacts("Salami Pizza" ,"$550",R.drawable.pizza));
                contactsList.add(new Contacts("Bacon Pizza" ,"$500",R.drawable.pizza1));
                contactsList.add(new Contacts("Salami Pizza" ,"$550",R.drawable.pizza));
                contactsList.add(new Contacts("Bacon Pizza" ,"$500",R.drawable.pizza1));
                contactsList.add(new Contacts("Bacon Pizza" ,"$500",R.drawable.pizza1));
                contactsList.add(new Contacts("Bacon Pizza" ,"$500",R.drawable.pizza1));

                ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(),contactsList);
                viewR.setAdapter(contactsAdapter);

                //Display all the contacts in linear layour (vertically)
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });

        burgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a list of contacts to display in RecyclerView
                List<Contacts> contactsList = new ArrayList<>();
                // Adding all the contacts object in list
                contactsList.add(new Contacts("veggie burger" ,"$550",R.drawable.burger));
                contactsList.add(new Contacts("ham burger" ,"$500",R.drawable.burger));
                contactsList.add(new Contacts("Salami burger" ,"$550",R.drawable.burger));

                ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(),contactsList);
                viewR.setAdapter(contactsAdapter);

                //Display all the contacts in linear layour (vertically)
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
        });
        saladButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a list of contacts to display in RecyclerView
                List<Contacts> contactsList = new ArrayList<>();
                // Adding all the contacts object in list
                contactsList.add(new Contacts("veggie salad" ,"$550",R.drawable.salad));
                contactsList.add(new Contacts("mix salad" ,"$500",R.drawable.salad1));
                contactsList.add(new Contacts("fresh salad" ,"$550",R.drawable.salad));

                ContactsAdapter contactsAdapter = new ContactsAdapter(getActivity(),contactsList);
                viewR.setAdapter(contactsAdapter);

                //Display all the contacts in linear layour (vertically)
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));

            }
        });
        return root;
    }
}