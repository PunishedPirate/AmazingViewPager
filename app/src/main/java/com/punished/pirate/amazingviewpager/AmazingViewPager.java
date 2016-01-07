package com.punished.pirate.amazingviewpager;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 支持以下特性的ViewPager
 * 1.嵌套滑动（done）
 * 2.当子View是ListView时，进场的ItemView做飞入动画，类似MIUI的应用商店，游戏中心（done）
 * 3.支持自动、循环（unfinished）
 *
 * @author zhangyuwen 2015-11-21
 */
public class AmazingViewPager extends ViewPager {
  protected AmazingPagerAdapter mAdapter;
  private float mOldX;
  private float mCurX;
  private PageTransformer mPageTransformer = new PageTransformer() {
    @Override
    public void transformPage(View page, float position) {
      if (position < -1 || position > 1) {
        return;
      }

      if (page instanceof AmazingTransformer) {
        ((AmazingTransformer) page).transformPage(page, position, scrollToLeft(), scrollToRight());
        return;
      }

      if (page instanceof ViewGroup) {
        translateViewGroup((ViewGroup) page, position, scrollToLeft(), scrollToRight());
        return;
      }
    }
  };

  public AmazingViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    setPageTransformer(false, mPageTransformer);
  }

  @Override
  public void setPageTransformer(boolean reverseDrawingOrder, final PageTransformer transformer) {
    PageTransformer mWrapper = new PageTransformer() {
      @Override
      public void transformPage(View page, float position) {
        if (transformer != null) {
          transformer.transformPage(page, position);
        }
        mPageTransformer.transformPage(page, position);
      }
    };
    super.setPageTransformer(reverseDrawingOrder, mWrapper);
  }

  @Override
  public boolean arrowScroll(int direction) {
    PagerAdapter adapter = getAdapter();
    if (adapter != null) {
      if ((direction == FOCUS_LEFT && getCurrentItem() == 0) ||
          (direction == FOCUS_RIGHT && getCurrentItem() == adapter.getCount() - 1)) {
        return false;
      }
    }
    return super.arrowScroll(direction);
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    final int action = MotionEventCompat.getActionMasked(ev);
    switch (action & MotionEventCompat.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN: {
        mOldX = mCurX = ev.getX();
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        mCurX = ev.getX();
        break;
      }
    }
    return super.onInterceptTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    final int action = MotionEventCompat.getActionMasked(ev);
    switch (action & MotionEventCompat.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN: {
        mOldX = mCurX = ev.getX();
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        mCurX = ev.getX();
        break;
      }
    }
    return super.onTouchEvent(ev);
  }

  public void translateViewGroup(ViewGroup parent, float position, boolean scrollToLeft,
      boolean scrollToRight) {
    AmazingUtil.translateViewGroup(parent, position, scrollToLeft, scrollToRight);
  }

  private boolean scrollToLeft() {
    return mCurX > mOldX;
  }

  private boolean scrollToRight() {
    return mCurX < mOldX;
  }

  @Override
  public AmazingPagerAdapter getAdapter() {
    return mAdapter;
  }

  @Override
  public void setAdapter(PagerAdapter adapter) {
    super.setAdapter(adapter);
    if (adapter instanceof AmazingPagerAdapter) {
      mAdapter = (AmazingPagerAdapter) adapter;
    } else {
      throw new IllegalArgumentException("adapter must be inherited from AmazingFragmentAdapter! ");
    }
  }

  public interface AmazingTransformer {
    void transformPage(View page, float position, boolean scrollToLeft, boolean scrollToRight);
  }
}
