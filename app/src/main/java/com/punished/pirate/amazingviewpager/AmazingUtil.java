package com.punished.pirate.amazingviewpager;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhangyuwen 2016-01-06
 */
public class AmazingUtil {
  public static float getPercent(int i, int n, float p) {
    float end = 1.0f * (i + 1) / n;
    if (p <= 0) {
      return 0;
    }

    if (p >= end) {
      return 1;
    }

    return p / end;
  }

  public static void translateViewGroup(ViewGroup parent, float position, boolean scrollToLeft,
      boolean scrollToRight) {
    int w = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
    int n = parent.getChildCount();
    float percent;

    if (position >= 0 && scrollToRight) {
      for (int i = 0; i < n; ++i) {
        View child = parent.getChildAt(i);
        float dx = 1.0f * (i + 1) * w / n;
        percent = AmazingUtil.getPercent(i, n, 1 - position);
        child.setTranslationX(dx * (1 - percent));
      }
    }

    if (position <= 0 && scrollToLeft) {
      for (int i = 0; i < n; ++i) {
        View child = parent.getChildAt(i);
        float dx = 1.0f * (i + 1) * w / n;
        percent = AmazingUtil.getPercent(i, n, 1 + position);
        child.setTranslationX(dx * (percent - 1));
      }
    }
  }
}
