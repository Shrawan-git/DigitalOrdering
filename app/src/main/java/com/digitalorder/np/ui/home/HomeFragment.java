package com.digitalorder.np.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private Button pizzaButton, burgerButton, saladButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        viewR = root.findViewById(R.id.recycleV);
        pizzaButton = root.findViewById(R.id.pizzaButton);

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
                Call<List<OrderMod>> orderCall = usersAPI.getOrderCategory(Url.token);

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