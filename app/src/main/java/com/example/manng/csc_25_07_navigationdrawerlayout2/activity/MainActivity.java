package com.example.manng.csc_25_07_navigationdrawerlayout2.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.MenuListAdapter;
import com.example.manng.csc_25_07_navigationdrawerlayout2.adapter.ViewPagerTabAdapter;
import com.example.manng.csc_25_07_navigationdrawerlayout2.database.DBHelper;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.BachHoaFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.DienTuFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.DoChoiFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.FirstFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.R;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.GiaDungFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.KhuyenMaiFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.LamDepFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.MaGiamGiaFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.MeBeFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.OtoXeMayFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.SecondFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.TheThaoFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.ThirdFragment;
import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.ThoiTrangFragment;

import java.util.ArrayList;

import com.example.manng.csc_25_07_navigationdrawerlayout2.fragment.TrangChuFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    View navigationHeader;
    BottomNavigationView bottomNavigationView;

    ImageView imageViewHeader;
    TextView textViewHeader;
    ListView listViewHeader;
    ArrayList arrayListMenu;
    MenuListAdapter menuListAdapter;

    DBHelper dbHelper;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerTabAdapter viewPagerAdapter;

    SearchView searchView;

    RelativeLayout relativeCart;
    static TextView txtNumberCart;
    Button btnCart;

    static int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();

    }

    private void addControl() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        dbHelper = new DBHelper(MainActivity.this);

        navigationView = (NavigationView) findViewById(R.id.navigationView);

        setupDrawerContent(navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);

        navigationHeader = navigationView.getHeaderView(0);

        imageViewHeader = navigationHeader.findViewById(R.id.img_header);
        textViewHeader = navigationHeader.findViewById(R.id.txt_header);
        listViewHeader = navigationHeader.findViewById(R.id.lst_header);

        arrayListMenu = dbHelper.getAllMenu();

        menuListAdapter = new MenuListAdapter(MainActivity.this, R.layout.layout_row, arrayListMenu);

        listViewHeader.setAdapter(menuListAdapter);

//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        //Tab matterial
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Trang chủ");
        tabLayout.getTabAt(1).setText("Mã giảm giá");
        tabLayout.getTabAt(2).setText("Khuyến mãi");
        tabLayout.getTabAt(3).setText("Thời trang");
        tabLayout.getTabAt(4).setText("Gia dụng");
        tabLayout.getTabAt(5).setText("Điện tử");
        tabLayout.getTabAt(6).setText("Mẹ & Bé");
        tabLayout.getTabAt(7).setText("Đồ chơi");
        tabLayout.getTabAt(8).setText("Làm đẹp");
        tabLayout.getTabAt(9).setText("Ô tô & Xe máy");
        tabLayout.getTabAt(10).setText("Thể thao");
        tabLayout.getTabAt(11).setText("Bách hóa");

//        searchView =
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void addEvent() {
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Intent intent;
//                switch (item.getItemId()) {
//                    case R.id.mnu_bottom_shopping:
//                        intent = new Intent(getBaseContext(), MainActivity.class);
//                        startActivity(intent);
//                        break;
//
//                    case R.id.mnu_bottom_chat:
//                        intent = new Intent(getBaseContext(), ChatActivity.class);
//                        startActivity(intent);
//                        break;
//
//                    case R.id.mnu_bottom_cart:
//                        intent = new Intent(getBaseContext(), CartActivity.class);
//                        startActivity(intent);
//                        break;
//                }
//                return true;
//            }
//        });
//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                return;
//            }
//        });

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {
        int id = item.getItemId();

//        showScreen(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

//    public void showScreen(int itemID) {
//        Fragment fragment = null;
//        switch (itemID) {
//            case R.id.nav_first_item:
//                fragment = new FirstFragment();
//                break;
//            case R.id.nav_second_item:
//                fragment = new SecondFragment();
//                break;
//            case R.id.nav_third_item:
//                fragment = new ThirdFragment();
//                break;
//            case R.id.nav_sub_item1:
//                fragment = new ThirdFragment();
//                break;
//
//        }
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frlContent, fragment);
//        ft.commit();
//    }

//    private ActionBarDrawerToggle setDrawerToggle() {
//        return new ActionBarDrawerToggle(MainActivity.this,
//                drawerLayout,
//                R.string.open,
//                R.string.close);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupViewPager() {
        viewPagerAdapter = new ViewPagerTabAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TrangChuFragment());
        viewPagerAdapter.addFragment(new KhuyenMaiFragment());
        viewPagerAdapter.addFragment(new MaGiamGiaFragment());
        viewPagerAdapter.addFragment(new ThoiTrangFragment());
        viewPagerAdapter.addFragment(new GiaDungFragment());
        viewPagerAdapter.addFragment(new DienTuFragment());
        viewPagerAdapter.addFragment(new MeBeFragment());
        viewPagerAdapter.addFragment(new DoChoiFragment());
        viewPagerAdapter.addFragment(new LamDepFragment());
        viewPagerAdapter.addFragment(new OtoXeMayFragment());
        viewPagerAdapter.addFragment(new TheThaoFragment());
        viewPagerAdapter.addFragment(new BachHoaFragment());
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void changeActivity() {

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
            txtNumberCart.setText(MainActivity.num + "");
        }
        btnCart = relativeCart.findViewById(R.id.btn_cart_tool_bar);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }

}
