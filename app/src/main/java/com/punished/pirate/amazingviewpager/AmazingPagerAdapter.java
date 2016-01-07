package com.punished.pirate.amazingviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * @author zhangyuwen 2016-01-06
 */

public abstract class AmazingPagerAdapter extends FragmentPagerAdapter {
  protected SparseArray<Fragment> mArray = new SparseArray<>();

  public AmazingPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    Fragment fragment = (Fragment) super.instantiateItem(container, position);
    mArray.put(position, fragment);
    return fragment;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    super.destroyItem(container, position, object);
    mArray.remove(position);
  }

  public Fragment getFragment(int position) {
    return mArray.get(position);
  }
}
