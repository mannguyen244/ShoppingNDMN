package com.example.manng.csc_25_07_navigationdrawerlayout2.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.squareup.picasso.Picasso;

/**
 * Created by DINH TDT on 12/08/2017.
 */

public class ViewPagerHomeSlideAdapter extends PagerAdapter {
    Context context;
    ArrayList<Integer> arrayList;

    public ViewPagerHomeSlideAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_img_slide_home,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imgSlideHome);
        Glide.with(context).load(arrayList.get(position)).into(imageView);
//        imageView.setImageResource(arrayList.get(position));
        container.addView(view);
        return view;
    }
}
