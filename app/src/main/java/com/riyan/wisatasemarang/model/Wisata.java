package com.riyan.wisatasemarang.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by riyan on 20/12/2017.
 */

public class Wisata {

    @SerializedName("id")
    private String idWisata;

    @SerializedName("nama")
    private String namaWisata;

    @SerializedName("gambar")
    private String gambarWisata;

    @SerializedName("deskripsi")
    private String deskripsiWisata;

    @SerializedName("alamat")
    private String alamatWisata;

    @SerializedName("lat")
    private String latitudeWisata;

    @SerializedName("long")
    private String longitudeWisata;

    public Wisata(String idWisata,String namaWisata,String gambarWisata,String deskripsiWisata,
                  String alamatWisata,String latitudeWisata,String longitudeWisata){
        this.idWisata = idWisata;
        this.namaWisata = namaWisata;
        this.gambarWisata = gambarWisata;
        this.deskripsiWisata = deskripsiWisata;
        this.alamatWisata = alamatWisata;
        this.latitudeWisata = latitudeWisata;
        this.longitudeWisata = longitudeWisata;
    }

    public String getIdWisata() {
        return idWisata;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public String getGambarWisata() {
        return gambarWisata;
    }

    public String getDeskripsiWisata() {
        return deskripsiWisata;
    }

    public String getAlamatWisata() {
        return alamatWisata;
    }

    public String getLatitudeWisata() {
        return latitudeWisata;
    }

    public String getLongitudeWisata() {
        return longitudeWisata;
    }
}
