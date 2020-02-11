package com.example.android.cerkinventory.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsDTO {

    @SerializedName("products")
    public List<ProductDTO> products;

    public class ProductDTO {

        @SerializedName("nameProduct")
        public String nameProduct;

        @SerializedName("price")
        public float price;

        @SerializedName("quantity")
        public int quantity;


    }
}
