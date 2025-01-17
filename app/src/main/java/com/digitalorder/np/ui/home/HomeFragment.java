package com.digitalorder.np.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.OrderRating;
import com.digitalorder.np.R;
import com.digitalorder.np.adapter.OrderAdapter;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView viewR;
    private Button pizzaButton, burgerButton, saladButton, seafoodButton, soupButton;
    private ImageButton imageButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewR = root.findViewById(R.id.recycleV);
        imageButton = root.findViewById(R.id.imageButton);
        pizzaButton = root.findViewById(R.id.pizzaButton);
        burgerButton = root.findViewById(R.id.burgerButton);
        saladButton = root.findViewById(R.id.saladButton);
        seafoodButton = root.findViewById(R.id.seafoodButton);
        soupButton = root.findViewById(R.id.soupButton);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<OrderMod>> orderCall = usersAPI.getOrderDetails(Url.token);

        orderCall.enqueue(new Callback<List<OrderMod>>() {
            @Override
            public void onResponse(Call<List<OrderMod>> call, Response<List<OrderMod>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<OrderMod> orderModList = response.body();
                OrderAdapter orderAdapter = new OrderAdapter(getActivity(), orderModList);
                viewR.setAdapter(orderAdapter);
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<OrderMod>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        pizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<OrderMod>> orderCall = usersAPI.getPizzaCategory(Url.token);

                orderCall.enqueue(new Callback<List<OrderMod>>() {
                    @Override
                    public void onResponse(Call<List<OrderMod>> call, Response<List<OrderMod>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<OrderMod> orderModList = response.body();
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), orderModList);
                        viewR.setAdapter(orderAdapter);
                        viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    @Override
                    public void onFailure(Call<List<OrderMod>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        burgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<OrderMod>> orderCall = usersAPI.getBurgerCategory(Url.token);

                orderCall.enqueue(new Callback<List<OrderMod>>() {
                    @Override
                    public void onResponse(Call<List<OrderMod>> call, Response<List<OrderMod>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<OrderMod> orderModList = response.body();
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), orderModList);
                        viewR.setAdapter(orderAdapter);
                        viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    @Override
                    public void onFailure(Call<List<OrderMod>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        saladButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<OrderMod>> orderCall = usersAPI.getSaladCategory(Url.token);

                orderCall.enqueue(new Callback<List<OrderMod>>() {
                    @Override
                    public void onResponse(Call<List<OrderMod>> call, Response<List<OrderMod>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<OrderMod> orderModList = response.body();
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), orderModList);
                        viewR.setAdapter(orderAdapter);
                        viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    @Override
                    public void onFailure(Call<List<OrderMod>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        seafoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<OrderMod>> orderCall = usersAPI.getSeafoodCategory(Url.token);

                orderCall.enqueue(new Callback<List<OrderMod>>() {
                    @Override
                    public void onResponse(Call<List<OrderMod>> call, Response<List<OrderMod>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<OrderMod> orderModList = response.body();
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), orderModList);
                        viewR.setAdapter(orderAdapter);
                        viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    @Override
                    public void onFailure(Call<List<OrderMod>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        soupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
                Call<List<OrderMod>> orderCall = usersAPI.getSoupCategory(Url.token);

                orderCall.enqueue(new Callback<List<OrderMod>>() {
                    @Override
                    public void onResponse(Call<List<OrderMod>> call, Response<List<OrderMod>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        List<OrderMod> orderModList = response.body();
                        OrderAdapter orderAdapter = new OrderAdapter(getActivity(), orderModList);
                        viewR.setAdapter(orderAdapter);
                        viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    @Override
                    public void onFailure(Call<List<OrderMod>> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return root;
    }
}