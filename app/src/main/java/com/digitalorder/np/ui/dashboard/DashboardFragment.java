package com.digitalorder.np.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalorder.np.R;
import com.digitalorder.np.adapter.ListAdapter;
import com.digitalorder.np.adapter.OrderAdapter;
import com.digitalorder.np.api.UsersAPI;
import com.digitalorder.np.model.OrderMod;
import com.digitalorder.np.model.Product;
import com.digitalorder.np.url.Url;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    RecyclerView viewR;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = root.findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        viewR = root.findViewById(R.id.recycleV);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<Product>> productCall = usersAPI.orderUserDisplay(Url.token);

        productCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Product> productList = response.body();
                ListAdapter list = new ListAdapter(getActivity(), productList);
                viewR.setAdapter(list);
                viewR.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
