package com.digitalorder.np;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.digitalorder.np.adapter.ViewPagerAdapter;
import com.digitalorder.np.fragments.Login_Fragment;
import com.digitalorder.np.fragments.Signup_Fragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Login_Fragment());
        viewPagerAdapter.addFragment(new Signup_Fragment());

        viewPager.setAdapter(viewPagerAdapter);

    }
}
