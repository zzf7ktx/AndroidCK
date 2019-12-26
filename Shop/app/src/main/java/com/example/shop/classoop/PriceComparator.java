package com.example.shop.classoop;

import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    public int compare(Product s1, Product s2) {
        if (s1.Giasanpham == s2.Giasanpham)
            return 0;
        else if (s1.Giasanpham > s2.Giasanpham)
            return 1;
        else
            return -1;
    }
}
