package com.riyan.wisatasemarang.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.riyan.wisatasemarang.R;
import com.riyan.wisatasemarang.adapter.WisataAdapter;
import com.riyan.wisatasemarang.helper.ServiceClient;
import com.riyan.wisatasemarang.helper.ServiceGenerator;
import com.riyan.wisatasemarang.model.ListWisata;
import com.riyan.wisatasemarang.model.Wisata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerViewHome;
    List<Wisata> wisataList = new ArrayList<>();
    ProgressDialog pd;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewHome = (RecyclerView) view.findViewById(R.id.rv_home);
        recyclerViewHome.setLayoutManager(new GridLayoutManager(getActivity(),2));

//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerViewHome.setLayoutManager(linearLayoutManager);
        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        Call<ListWisata> getListWisata = service.getWisata("semarang");

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Load Data From Server");
        pd.show();

        getListWisata.enqueue(new Callback<ListWisata>() {
            @Override
            public void onResponse(Call<ListWisata> call, Response<ListWisata> response) {
                pd.dismiss();
                wisataList = response.body().getListWisataSemarang();
                WisataAdapter adapter = new WisataAdapter(getActivity(),wisataList);
                recyclerViewHome.setAdapter(adapter);
                Toast.makeText(getActivity(), "Item Count : "+adapter.getItemCount(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ListWisata> call, Throwable t) {
                Toast.makeText(getActivity(), "Info : "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

}
