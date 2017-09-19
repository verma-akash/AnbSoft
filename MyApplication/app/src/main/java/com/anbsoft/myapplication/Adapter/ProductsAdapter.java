package com.anbsoft.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anbsoft.myapplication.MainActivity;
import com.anbsoft.myapplication.Models.Product;
import com.anbsoft.myapplication.ProductActivity;
import com.anbsoft.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Akash on 19-Sep-17.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private Context context;
    private ArrayList<Product> arrayList;

    public ProductsAdapter(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ProductsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.single_product_row, parent, false);
        return new ProductsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductsHolder holder, int position) {
        holder.productName.setText(arrayList.get(position).getProductName());
        holder.productDesc.setText(arrayList.get(position).getDescription());
        holder.productPrice.setText("Rs. "+String.valueOf(arrayList.get(position).getPrice()));

        if (position % 6 == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_camera, context.getTheme()));
            } else {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_camera));
            }
        } else if (position % 6 == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_gallery, context.getTheme()));
            } else {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_gallery));
            }
        } else if (position % 6 == 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_manage, context.getTheme()));
            } else {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_manage));
            }
        } else if (position % 6 == 3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_send, context.getTheme()));
            } else {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_send));
            }
        } else if (position % 6 == 4) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_share, context.getTheme()));
            } else {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_share));
            }
        } else if (position % 6 == 5) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_slideshow, context.getTheme()));
            } else {
                holder.productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_menu_slideshow));
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProductsHolder extends RecyclerView.ViewHolder {

        public TextView productName;
        public TextView productDesc;
        public TextView productPrice;
        public ImageView productImage;

        public ProductsHolder(final View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productDesc = itemView.findViewById(R.id.productDesc);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Intent intent = new Intent(context, ProductActivity.class);
                        intent.putExtra("PROD_NAME", arrayList.get(getAdapterPosition()).getProductName());
                        intent.putExtra("PROD_DESC", arrayList.get(getAdapterPosition()).getDescription());
                        intent.putExtra("PROD_PRICE", arrayList.get(getAdapterPosition()).getPrice());
                        intent.putExtra("PROD_IMAGE", getAdapterPosition());

                        itemView.setTransitionName("PRODUCT_IMAGE");
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation((MainActivity) context, (View) productImage, "PRODUCT_IMAGE");
                        context.startActivity(intent, options.toBundle());
                    } else {
                        // code for pre lollpop devices
                    }
                }
            });
        }
    }
}
