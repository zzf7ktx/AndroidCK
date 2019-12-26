package com.example.shop.classoop;
import java.util.Comparator;

public class NameComparator implements Comparator<Product> {
    public int compare(Product s1, Product s2) {
        return s1.Tensanpham.compareTo(s2.Tensanpham);
    }
}
