package com.punished.pirate.amazingviewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author zhangyuwen 2016-01-06
 */
public class AmazingLinearLayout extends LinearLayout
    implements
    AmazingViewPager.AmazingTransformer {

  public AmazingLinearLayout(Context context) {
    super(context);
  }

  public AmazingLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public AmazingLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public void transformPage(View page, float position, boolean scrollToLeft, boolean scrollToRight) {
    AmazingViewPager pager = (AmazingViewPager) findViewById(R.id.pager);
    if (pager != null) {
      AmazingPagerAdapter adapter = pager.getAdapter();
      if (adapter == null) {
        return;
      }
      Fragment fragment = adapter.getFragment(pager.getCurrentItem());
      if (fragment == null) {
        return;
      }
      ViewGroup parent = (ViewGroup) fragment.getView();
      if (parent == null) {
        return;
      }
      pager.translateViewGroup(parent, position, scrollToLeft, scrollToRight);
    }
  }
}
