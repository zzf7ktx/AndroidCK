package com.example.shop.classoop;

import java.util.ArrayList;

public class ListFavoriteProduct {
    ArrayList<FavoriteProduct> list;

    public ListFavoriteProduct(ArrayList<FavoriteProduct> list) {
        this.list = list;
    }

    public ArrayList<FavoriteProduct> getList() {
        return list;
    }

    public void setList(ArrayList<FavoriteProduct> list) {
        this.list = list;
    }
}
