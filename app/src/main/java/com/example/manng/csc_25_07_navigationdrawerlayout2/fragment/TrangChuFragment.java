package com.example.manng.csc_25_07_navigationdrawerlayout2.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.manng.csc_25_07_navigationdrawerlayout2.RecyclerItemClickListener;
import com.example.manng.csc_25_07_navigationdrawerlayout2.activity.ProductDetailActivity;
import com.example.manng.csc_25_07_navigationdrawerlayout2.object.Products;
import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.RecyclerViewAdapter;
import com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.ViewPagerHomeSlideAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrangChuFragment extends Fragment {

    ViewPagerHomeSlideAdapter viewPagerAdapter;
    ViewPager viewPager;
    ArrayList<Integer> arrSlide;
    //    int[] img;
    Timer timer;
    LinearLayout arrDots;
    TextView[] dots;
    ArrayList<Products> arrayList;
    RecyclerView rcSearchPopular, rcFlashSale, rcShoppingMall,rcYKienHomNay;
    RecyclerViewAdapter recyclerViewAdapter;
    ProgressBar progressBar;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_trang_chu, null);

        viewPager = (ViewPager) view.findViewById(R.id.viewPagerSlideHome);
        arrDots = (LinearLayout) view.findViewById(R.id.dotsSliderHome);
        rcSearchPopular = (RecyclerView) view.findViewById(R.id.recyclerViewSearchPopular);
        rcFlashSale = (RecyclerView) view.findViewById(R.id.recyclerViewFlashSale);
        rcShoppingMall = (RecyclerView) view.findViewById(R.id.recyclerViewShoppingMall);
        rcYKienHomNay = (RecyclerView) view.findViewById(R.id.recyclerViewGoiYHomNay);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBarFlashSale);
//        progressBar.setProgress(80);

        // arrList Slide
        arrSlide = new ArrayList<>();
        arrSlide.add(R.drawable.banner1);
        arrSlide.add(R.drawable.banner2);
        arrSlide.add(R.drawable.banner3);
        arrSlide.add(R.drawable.banner4);

        //arrList Products
        arrayList = new ArrayList<>();
        arrayList.add(new Products("name 1", "descript 1",R.drawable.album1));
        arrayList.add(new Products("name 2", "descript 2",R.drawable.album2));
        arrayList.add(new Products("name 3", "descript 3",R.drawable.album3));
        arrayList.add(new Products("name 4", "descript 4",R.drawable.album4));
        arrayList.add(new Products("name 5", "descript 5",R.drawable.album5));
        arrayList.add(new Products("name 6", "descript 6",R.drawable.album6));
        arrayList.add(new Products("name 7", "descript 7",R.drawable.album7));
        arrayList.add(new Products("name 8", "descript 8",R.drawable.album8));
        arrayList.add(new Products("name 9", "descript 9",R.drawable.album9));
        arrayList.add(new Products("name 10", "descript 10",R.drawable.album10));
        arrayList.add(new Products("name 11", "descript 11",R.drawable.album11));

        //Search Popular
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),R.layout.layout_search_popular_item,arrayList,2);
        rcSearchPopular.setLayoutManager(linearLayoutManager);
        rcSearchPopular.setAdapter(recyclerViewAdapter);

        //Flash sale
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),R.layout.layout_flash_sale_item,arrayList,3);
        rcFlashSale.setLayoutManager(linearLayoutManager2);
        rcFlashSale.setAdapter(recyclerViewAdapter);

        //Shopping Mall
//        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),R.layout.layout_shopping_mall_item,arrayList,4);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeActivity.this,arrayList.size());
        rcShoppingMall.setLayoutManager(new GridLayoutManager(getActivity(),2,LinearLayoutManager.HORIZONTAL,false));
        rcShoppingMall.setAdapter(recyclerViewAdapter);

        //Goi Y Hom Nay
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),R.layout.layout_goi_y_hom_nay_item,arrayList,5);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
//        rcShoppingMall.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,LinearLayoutManager.HORIZONTAL,false));
        rcYKienHomNay.setLayoutManager(gridLayoutManager);
        rcYKienHomNay.setAdapter(recyclerViewAdapter);


        recyclerViewAdapter.notifyDataSetChanged();


        viewPagerAdapter = new ViewPagerHomeSlideAdapter(getContext(), arrSlide);
        viewPager.setAdapter(viewPagerAdapter);
        // Xu ly tu dong chuyen page
        timer = new Timer();
        timer.schedule(new MyTimerTask(),4000,3000);
        // show dots len view
        addBottomDots(0);
        //goi add su kien listener cua viewpager
        viewPager.addOnPageChangeListener(onPageChangeListener);

        rcYKienHomNay.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), rcYKienHomNay, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                intent.putExtra("product", arrayList.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        return view;

    }

    private void addBottomDots(int currentSlide) {
        dots = new TextView[arrSlide.size()];
        arrDots.removeAllViews();
        //page khong chon: dot mau trang
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#FFFFFF"));
            arrDots.addView(dots[i]);
        }
        //page duoc chon : mau cam
        if (dots.length > 0) {
            dots[currentSlide].setTextColor(Color.parseColor("#FBB117"));
        }

    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            viewPager.post(new Runnable() {
                @Override
                public void run() {
                    //auto quay lai trang dau tien khi den trang cuoi cung
                    viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % arrSlide.size());
                    dots = new TextView[arrSlide.size()];
                    arrDots.removeAllViews();
                    //update trang thai dau view khi ko chon (dots mau trang)
                    for (int i = 0; i < dots.length; i++) {
                        dots[i] = new TextView(getContext());
                        dots[i].setText(Html.fromHtml("&#8226;"));
                        dots[i].setTextSize(35);
                        dots[i].setTextColor(Color.parseColor("#FFFFFF"));
                        arrDots.addView(dots[i]);
                    }
                    //dot mau cam: page duoc chon
                    if (dots.length > 0) {
                        dots[viewPager.getCurrentItem()].setTextColor(Color.parseColor("#FBB117"));
                    }
                }
            });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    // su kien tac dong len viewpager
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //khi page duoc chon
            // Cap nhat dots khi lat sang trang
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
