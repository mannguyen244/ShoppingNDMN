package com.example.manng.csc_25_07_navigationdrawerlayout2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.CartAdapter;
import com.example.manng.csc_25_07_navigationdrawerlayout2.object.Products;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static ArrayList<Products> arrayList;
    CartAdapter cartAdapter;
    static TextView txtPriceCart;
    static TextView txtCoinsCart;
    Button btnPayment;

    static double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCart);
        txtPriceCart = (TextView) findViewById(R.id.textPriceCart);
        txtCoinsCart = (TextView) findViewById(R.id.textCoinsCart);
        btnPayment = (Button) findViewById(R.id.btnPaymentCart);


        arrayList = new ArrayList<>();
        arrayList.add(new Products(R.drawable.album1, "Product 1", 99.0));
        arrayList.add(new Products(R.drawable.album2, "Product 2", 99.0));
        arrayList.add(new Products(R.drawable.album3, "Product 3", 99.0));
        arrayList.add(new Products(R.drawable.album4, "Product 4", 99.0));
        arrayList.add(new Products(R.drawable.album5, "Product 5", 99.0));
        arrayList.add(new Products(R.drawable.album6, "Product 6", 99.0));
        arrayList.add(new Products(R.drawable.album7, "Product 7", 99.0));
        arrayList.add(new Products(R.drawable.album8, "Product 8", 99.0));
        arrayList.add(new Products(R.drawable.album9, "Product 9", 99.0));
        arrayList.add(new Products(R.drawable.album10, "Product 10", 99.0));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);

        cartAdapter = new CartAdapter(CartActivity.this, R.layout.layout_item_cart, arrayList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);

        updateTotalPrice();
    }

    public static void updateTotalPrice() {
        totalPrice = 0;
        for (Products product : arrayList) {
            if (product.isChoose()) {
                totalPrice += product.getTotal();
            }
        }
        txtPriceCart.setText(totalPrice + "");
        txtCoinsCart.setText(String.valueOf(Math.round(totalPrice*0.01)));

    }
}
