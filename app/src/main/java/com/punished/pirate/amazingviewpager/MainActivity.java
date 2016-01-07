package com.punished.pirate.amazingviewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
  private TabLayout mTabLayout;
  private ViewPager mViewPager;
  private MainPagerAdapter mPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initWindow();
    setContentView(R.layout.activity_main);
    mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
    mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    mViewPager.setAdapter(mPagerAdapter);
    mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
  }

  @TargetApi(19)
  private void initWindow() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
  }

  private static class MainPagerAdapter extends AmazingPagerAdapter {
    private static final String[] title = new String[] {
        "推荐",
        "精品",
        "排行",
        "分类",
        "新品"
    };

    public MainPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      if (position == 2) {
        return PagerFragment.newInstance();
      }
      return SimpleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
      return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return title[position];
    }
  }
}
