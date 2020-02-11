package com.example.android.cerkinventory.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.android.cerkinventory.activity.HomeActivity;
import com.example.android.cerkinventory.db.ProductDAOImpl;
import com.example.android.cerkinventory.entity.Product;
import com.example.android.cerkinventory.entity.ProductsDTO;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class utils {

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static void loadProductIfNeeded(HomeActivity homeActivity) {
        ArrayList<Product> prods = ProductDAOImpl.getAllProduct();
        if (prods.size() == 0) {
            String json = getJsonString(homeActivity);

            try {
                Gson gson = new Gson();
                ProductsDTO entity = gson.fromJson(json, ProductsDTO.class);

                ArrayList<Product> products = new ArrayList<>();
                for (ProductsDTO.ProductDTO productDTO : entity.products) {
                    Product product = new Product();
                    product.setNameProduct(productDTO.nameProduct);
                    product.setPrice(productDTO.price);
                    product.setQuantity(productDTO.quantity);

                    products.add(product);
                }

                ProductDAOImpl.insert(products);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    private static String getJsonString(HomeActivity homeActivity) {
        try {
            StringBuilder sb = new StringBuilder();
            InputStream is = homeActivity.getAssets().open("productsDataset.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }
}
