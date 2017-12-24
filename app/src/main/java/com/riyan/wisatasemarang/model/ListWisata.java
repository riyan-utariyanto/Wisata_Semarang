package com.riyan.wisatasemarang.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by riyan on 20/12/2017.
 */

public class ListWisata {

    @SerializedName("semarang")
    private List<Wisata> listWisataSemarang;

    public List<Wisata> getListWisataSemarang() {
        return listWisataSemarang;
    }
}
