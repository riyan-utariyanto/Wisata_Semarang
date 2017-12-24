package com.riyan.wisatasemarang.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.riyan.wisatasemarang.R;
import com.riyan.wisatasemarang.adapter.WisataAdapter;
import com.riyan.wisatasemarang.db.DatabaseHelper;
import com.riyan.wisatasemarang.model.Wisata;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    RecyclerView rvFavorit;
    DatabaseHelper database;
    ArrayList<Wisata> listWisataFavorite;
    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container,
                false);
        rvFavorit = (RecyclerView) view.findViewById(R.id.rv_favorite);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvFavorit.setLayoutManager(llm);

        database = new DatabaseHelper(getActivity());
        listWisataFavorite = database.getDataFavorite();
        WisataAdapter adapter = new WisataAdapter(getActivity(), listWisataFavorite);
        rvFavorit.setAdapter(adapter);
        return view;

    }

}
