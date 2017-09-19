package com.example.manng.csc_25_07_navigationdrawerlayout2.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.example.manng.csc_25_07_navigationdrawerlayout2.object.Products;
import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by DINH TDT on 12/08/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ImageViewBH> {
    Context context;
    int layout;
    ArrayList<Products> arrayList;
    int key;

    public RecyclerViewAdapter(Context context, int layout, ArrayList<Products> arrayList, int key) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        this.key = key;
    }

    @Override
    public ImageViewBH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, parent, false);
        return new ImageViewBH(view);
    }

    //HomeActivity Event
    @Override
    public void onBindViewHolder(ImageViewBH holder, int position) {


        switch (key) {
            case 1:
                //slide
                Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgSlide);
//                holder.imgSlide.setImageResource(arrayList.get(position).getImage());
                break;
            case 2:
                //search popular
                Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgSearch);
//                holder.imgSearch.setImageResource(arrayList.get(position).getImage());
                holder.txtSearch.setText(arrayList.get(position).getName());
                break;
            case 3:
                //Flash Sale
                Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgFlashSale);
//                holder.imgFlashSale.setImageResource(arrayList.get(position).getImage());
                holder.txtCountFlashSale.setText(arrayList.get(position).getName());
                holder.txtPriceFlashSale.setText(arrayList.get(position).getDescript());
                break;
            case 4:
                //Shopping Mall
                Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgShoppingMall1);
//                    holder.imgShoppingMall1.setImageResource(arrayList.get(position).getImage());
//                    holder.imgShoppingMall2.setImageResource(arrayList.get(position).getImage());
                break;
            case 5:
                //Y Kien Hom Nay
                Glide.with(context).load(arrayList.get(position).getImage()).into(holder.imgYKienHomNay);
//                holder.imgYKienHomNay.setImageResource(arrayList.get(position).getImage());
                holder.txtNameProduct.setText(arrayList.get(position).getName());
                break;
        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ImageViewBH extends RecyclerView.ViewHolder {
        ImageView imgSlide, imgSearch, imgFlashSale, imgShoppingMall1, imgShoppingMall2, imgYKienHomNay;
        TextView txtSearch, txtPriceFlashSale, txtCountFlashSale, txtNameProduct;

        public ImageViewBH(View itemView) {
            super(itemView);
            imgSlide = (ImageView) itemView.findViewById(R.id.imgSlideHome);


            imgSearch = (ImageView) itemView.findViewById(R.id.imgSearchPopular);
            txtSearch = (TextView) itemView.findViewById(R.id.textSearchPopular);

            imgFlashSale = (ImageView) itemView.findViewById(R.id.imgFlashSaleItem);
            txtCountFlashSale = (TextView) itemView.findViewById(R.id.txtCountFlashSale);
            txtPriceFlashSale = (TextView) itemView.findViewById(R.id.txtPriceFlashSale);

            imgShoppingMall1 = (ImageView) itemView.findViewById(R.id.imgShoppingMall1);
//            imgShoppingMall2 = (ImageView) itemView.findViewById(R.id.imgShoppingMall2);

            txtNameProduct = (TextView) itemView.findViewById(R.id.txtNameProduct);
            imgYKienHomNay = (ImageView) itemView.findViewById(R.id.imgGoiYHomNay);

        }
    }
}
