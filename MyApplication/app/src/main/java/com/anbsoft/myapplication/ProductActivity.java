package com.anbsoft.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Akash on 19-Sep-17.
 */

public class ProductActivity extends AppCompatActivity {

    private TextView productName;
    private TextView productPrice;
    private TextView productDesc;
    private ImageView productImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productName = (TextView) findViewById(R.id.productName);
        productPrice = (TextView) findViewById(R.id.productPrice);
        productDesc = (TextView) findViewById(R.id.productDesc);
        productImage = (ImageView) findViewById(R.id.productImage);

        //setContentView(R.layout.activity_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            productImage.setTransitionName("PRODUCT_IMAGE");
        }

        int position = getIntent().getExtras().getInt("PROD_IMAGE");
        if (position % 6 == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera, getTheme()));
            } else {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_camera));
            }
        } else if (position % 6 == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_gallery, getTheme()));
            } else {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_gallery));
            }
        } else if (position % 6 == 2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_manage, getTheme()));
            } else {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_manage));
            }
        } else if (position % 6 == 3) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send, getTheme()));
            } else {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_send));
            }
        } else if (position % 6 == 4) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_share, getTheme()));
            } else {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_share));
            }
        } else if (position % 6 == 5) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_slideshow, getTheme()));
            } else {
                productImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_slideshow));
            }
        }

        productName.setText(getIntent().getExtras().getString("PROD_NAME"));
        productPrice.setText("Rs. " + getIntent().getExtras().getDouble("PROD_PRICE"));
        productDesc.setText(getIntent().getExtras().getString("PROD_DESC"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
