package com.example.shop.classoop;

import java.util.ArrayList;

public class ListCartProduct {
    ArrayList<CartProduct> list;

    public ListCartProduct(ArrayList<CartProduct> list) {
        this.list = list;
    }

    public ArrayList<CartProduct> getList() {
        return list;
    }

    public void setList(ArrayList<CartProduct> list) {
        this.list = list;
    }

    public void setItemList(CartProduct cartProduct, int i) {
        list.set(i, cartProduct);
    }
}
