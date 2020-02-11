package com.example.android.cerkinventory.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.cerkinventory.Application;
import com.example.android.cerkinventory.R;
import com.example.android.cerkinventory.db.ProductDAOImpl;
import com.example.android.cerkinventory.entity.Product;
import com.example.android.cerkinventory.fragment.InventorFragment;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;
    private InventorFragment parent;

    public ProductAdapter(InventorFragment inventorFragment, List<Product> products) {
        this.products = products;
        this.parent = inventorFragment;
    }

    public void resetValues() {
        this.products = ProductDAOImpl.getAllProduct();
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventor, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.name.setText(products.get(position).getNameProduct());
        holder.quantity.setText(String.valueOf(products.get(position).getQuantity()));


    }

    @Override
    public int getItemCount() {
        return products.size();
    }



    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView name;
        public AppCompatEditText quantity;
        public AppCompatButton delete, less, more;

        public ProductViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tProduitName);
            quantity = view.findViewById(R.id.eQuantity);
            delete = view.findViewById(R.id.bDelete);
            less = view.findViewById(R.id.bLess);
            more = view.findViewById(R.id.bMore);

    }
}

}
