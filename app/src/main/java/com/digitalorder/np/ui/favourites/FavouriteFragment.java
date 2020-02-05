package com.digitalorder.np.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.R;
import com.digitalorder.np.adapter.FavouriteAdapter;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.Product;
import com.digitalorder.np.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclefav;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclefav = root.findViewById(R.id.recyclefav);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<Product>> listCall = usersAPI.favouriteDetailList(Url.token);

        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Product> products = response.body();
                FavouriteAdapter favouriteAdapter = new FavouriteAdapter(getActivity(), products);
                recyclefav.setAdapter(favouriteAdapter);
                recyclefav.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }
}

