package com.riyan.wisatasemarang.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.riyan.wisatasemarang.R;
import com.riyan.wisatasemarang.activities.DetailWisataActivity;
import com.riyan.wisatasemarang.helper.Constant;
import com.riyan.wisatasemarang.model.Wisata;

import java.util.List;

/**
 * Created by riyan on 20/12/2017.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder> {


    private Context context;
    private List<Wisata> listWisata;
    public WisataAdapter(Context context, List<Wisata> listWisata) {
        this.context = context;
        this.listWisata = listWisata;
    }

    @Override
    public WisataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new WisataViewHolder(itemView);

    }

    public class WisataViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemGambarWisata;
        TextView tvItemNamaWisata;
        TextView tvItemAlamatWisata;

        public WisataViewHolder(View itemView) {
            super(itemView);
            ivItemGambarWisata = (ImageView) itemView.findViewById(R.id.iv_item_gambar);
            tvItemNamaWisata = (TextView) itemView.findViewById(R.id.tv_item_nama);
            tvItemAlamatWisata = (TextView) itemView.findViewById(R.id.tv_item_alamat);
        }
    }

    @Override
    public void onBindViewHolder(WisataViewHolder holder, final int position) {
        String linkGambar = listWisata.get(position).getGambarWisata();
        Glide.with(context).load("https://drive.google.com/thumbnail?id=" + linkGambar)
                .into(holder.ivItemGambarWisata);

        holder.tvItemNamaWisata.setText(listWisata.get(position).getNamaWisata());
        holder.tvItemAlamatWisata.setText(listWisata.get(position).getAlamatWisata());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle b = new Bundle();
                b.putString(Constant.ID_WISATA,listWisata.get(position).getIdWisata());
                b.putString(Constant.NAMA_WISATA,listWisata.get(position).getNamaWisata());
                b.putString(Constant.GAMBAR_WISATA,listWisata.get(position).getGambarWisata());
                b.putString(Constant.ALAMAT_WISATA,listWisata.get(position).getAlamatWisata());
                b.putString(Constant.DESKRIPSI_WISATA,listWisata.get(position).getDeskripsiWisata());
                b.putString(Constant.LATITUDE_WISATA,listWisata.get(position).getLatitudeWisata());
                b.putString(Constant.LONGITUDE_WISATA,listWisata.get(position).getLongitudeWisata());
                //
                Intent i = new Intent(context, DetailWisataActivity.class);
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }
}
