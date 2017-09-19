package com.example.manng.csc_25_07_navigationdrawerlayout2.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.example.manng.csc_25_07_navigationdrawerlayout2.object.Products;

public class ProductDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout relativeCart;
    TextView txtNumberCart;
    Button btnAddCart, btnCart;

    Animation shake;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar_detail_product);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAddCart = (Button) findViewById(R.id.btn_add_cart);


        shake = AnimationUtils.loadAnimation(ProductDetailActivity.this, R.anim.shake);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeCart.startAnimation(shake);
//                MainActivity.num = Integer.parseInt(txtNumberCart.getText()+"");
                MainActivity.num++;
                if (MainActivity.num > 0) {
                    txtNumberCart.setVisibility(View.VISIBLE);
                    MainActivity.txtNumberCart.setVisibility(View.VISIBLE);
                }

                txtNumberCart.setText(MainActivity.num + "");
                MainActivity.txtNumberCart.setText(MainActivity.num + "");
            }
        });

        Intent intent = getIntent();
        Products products = (Products) intent.getSerializableExtra("product");

        imageView = (ImageView) findViewById(R.id.img_detail);
        imageView.setImageResource(products.getImage());

//        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        MenuItem item1 = menu.findItem(R.id.mnu_cart);
        MenuItemCompat.setActionView(item1, R.layout.layout_nptification_add_to_cart);
        relativeCart = (RelativeLayout) MenuItemCompat.getActionView(item1);
        txtNumberCart = relativeCart.findViewById(R.id.notification_number_update);
        txtNumberCart.setVisibility(View.GONE);
        if (MainActivity.num > 0) {
            txtNumberCart.setVisibility(View.VISIBLE);
            MainActivity.txtNumberCart.setVisibility(View.VISIBLE);
            txtNumberCart.setText(MainActivity.num + "");
            MainActivity.txtNumberCart.setText(MainActivity.num + "");
        }
        btnCart = relativeCart.findViewById(R.id.btn_cart_tool_bar);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
