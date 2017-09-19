package com.example.manng.csc_25_07_navigationdrawerlayout2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.example.manng.csc_25_07_navigationdrawerlayout2.activity.CartActivity;
import com.example.manng.csc_25_07_navigationdrawerlayout2.object.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DINH TDT on 23/08/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    int layout;
    ArrayList<Products> arrayList;
    double total = 0;
    double price = 0;


    public CartAdapter(Context context, int layout, ArrayList<Products> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {

        Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgProduct);
//        holder.imgProduct.setImageResource(arrayList.get(position).getImage());
        holder.txtNameProduct.setText(arrayList.get(position).getName());
        price = arrayList.get(position).getPrice();
        holder.txtDiscountPrice.setText(String.valueOf(price) + " đ");
//        holder.txtOriginalPrice.setText(String.valueOf(arrayList.get(position).getPrice()) + " đ / 1item");

//        holder.txtOriginalPrice.setPaintFlags(holder.txtOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.imgButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(String.valueOf(holder.txtNumberItemCart.getText()));

                num += 1;
                holder.txtNumberItemCart.setText(num + "");

                total = price * num;
                holder.txtDiscountPrice.setText(total + "");
                arrayList.get(position).setTotal(total);

                CartActivity.updateTotalPrice();
            }
        });

        holder.imgButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(String.valueOf(holder.txtNumberItemCart.getText()));
                if (num > 1) {
                    num -= 1;
                    holder.txtNumberItemCart.setText(num + "");
                    total = price * num;
                    holder.txtDiscountPrice.setText(total + "");
                    arrayList.get(position).setTotal(total);
                    CartActivity.updateTotalPrice();
                }
            }
        });

        holder.cbItemCart.setOnCheckedChangeListener(null);
        holder.cbItemCart.setChecked(arrayList.get(position).isChoose());

        holder.cbItemCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    arrayList.get(position).setChoose(true);
                    CartActivity.updateTotalPrice();
                } else {
                    arrayList.get(position).setChoose(false);
                    CartActivity.updateTotalPrice();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        TextView txtNameProduct, txtDiscountPrice, txtOriginalPrice, txtNumberItemCart;
        ImageView imgProduct;
        CheckBox cbItemCart;
        ImageButton imgButtonMinus, imgButtonPlus, imgButtonLike, imgButtonDelete;

        public CartViewHolder(View itemView) {
            super(itemView);

            txtNameProduct = (TextView) itemView.findViewById(R.id.textNameItemCart);
            txtDiscountPrice = (TextView) itemView.findViewById(R.id.textDiscountPriceCart);
//            txtOriginalPrice = (TextView) itemView.findViewById(R.id.textOriginalPriceCart);
            txtNumberItemCart = (TextView) itemView.findViewById(R.id.textNumberItemCart);

            imgProduct = (ImageView) itemView.findViewById(R.id.imgItemCart);
            cbItemCart = (CheckBox) itemView.findViewById(R.id.checxBoxItemCart);


            imgButtonMinus = (ImageButton) itemView.findViewById(R.id.imgButtonMinus);
            imgButtonPlus = (ImageButton) itemView.findViewById(R.id.imgButtonPlus);
            imgButtonLike = (ImageButton) itemView.findViewById(R.id.imgButtonLike);
            imgButtonDelete = (ImageButton) itemView.findViewById(R.id.imgButtonDelete);

        }
    }
}
