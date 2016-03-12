package com.punished.pirate.amazingviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhangyuwen 2016-01-05
 */
public class PagerFragment extends Fragment {
  private TabLayout mTabLayout;
  private ViewPager mViewPager;
  private PagerAdapter mPagerAdapter;

  public static PagerFragment newInstance() {
    Bundle args = new Bundle();
    PagerFragment fragment = new PagerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_pager, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
    mViewPager = (ViewPager) view.findViewById(R.id.pager);
    mPagerAdapter = new PagerAdapter(getChildFragmentManager());
    mViewPager.setAdapter(mPagerAdapter);
    mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        mTabLayout.getTabAt(position).select();
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
    mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
  }

  private static class PagerAdapter extends AmazingPagerAdapter {
    private static final String[] title = new String[] {
        "下载榜",
        "畅销榜"
    };

    public PagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
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
