package com.example.manng.csc_25_07_navigationdrawerlayout2.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manng.csc_25_07_navigationdrawerlayout2.R;

import java.util.ArrayList;

/**
 * Created by manng on 12-Aug-17.
 */

public class MenuListAdapter extends ArrayAdapter {
    Context context;
    ArrayList arrayList = new ArrayList();
    int layout;
    ImageView imageView;
    TextView textView;

    public MenuListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(layout, null);

        imageView = convertView.findViewById(R.id.img_row);
        textView = convertView.findViewById(R.id.txt_row_menu_name);

        textView.setText(arrayList.get(position).toString());

        switch (arrayList.get(position).toString()) {
            case "Trang chủ":
                imageView.setImageResource(R.drawable.ic_home);
                break;

            case "Tất cả danh mục":
                imageView.setImageResource(R.drawable.ic_format_list);
                break;

            case "Xu hướng":
                imageView.setImageResource(R.drawable.ic_trending_up_black_24dp);
                break;

            case "Khuyến mãi":
                imageView.setImageResource(R.drawable.ic_sale);
                break;

            case "Bán chạy":
                imageView.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
                break;

            case "Thương hiệu":
                imageView.setImageResource(R.drawable.ic_stars);
                break;

            case "Sự kiện":
                imageView.setImageResource(R.drawable.ic_date);
                break;

            case "Trang cá nhân":
                imageView.setImageResource(R.drawable.ic_account_black);
                break;

            case "Về chúng tôi":
                imageView.setImageResource(R.drawable.ic_favorite);
                break;

        }


        return convertView;
    }
}
