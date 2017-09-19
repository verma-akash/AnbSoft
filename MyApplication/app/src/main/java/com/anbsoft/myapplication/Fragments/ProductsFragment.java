package com.anbsoft.myapplication.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anbsoft.myapplication.Adapter.ProductsAdapter;
import com.anbsoft.myapplication.Helper.DatabaseHelper;
import com.anbsoft.myapplication.Models.Product;
import com.anbsoft.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Akash on 19-Sep-17.
 */

public class ProductsFragment extends Fragment {

    private RecyclerView recycleContainer;
    private ArrayList<Product> productArrayList;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        productArrayList = databaseHelper.getAllProducts();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recycleContainer = view.findViewById(R.id.recycleContainer);
        ProductsAdapter productsAdapter = new ProductsAdapter(getActivity(), productArrayList);
        recycleContainer.setAdapter(productsAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleContainer.setLayoutManager(layoutManager);

        return view;
    }
}
