package com.punished.pirate.amazingviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyuwen 2016-01-05
 */
public class SimpleFragment extends Fragment {
  private static final int ITEM_COUNT = 50;
  private static final String[] FROM = new String[] {
      "column_title",
      "column_subtitle"
  };
  private static final int[] TO = new int[] {
      R.id.title,
      R.id.subtitle
  };

  private ListView mListView;
  private SimpleAdapter mAdapter;

  public static SimpleFragment newInstance(int position) {
    Bundle args = new Bundle();
    args.putInt("position", position);
    SimpleFragment fragment = new SimpleFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_simple, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mListView = (ListView) view.findViewById(R.id.list);
    mAdapter = new SimpleAdapter(getActivity(), getData(), R.layout.list_item_simple, FROM, TO);
    mListView.setAdapter(mAdapter);
  }

  private int getPosition() {
    Bundle args = getArguments();
    if (args == null) {
      return 0;
    }
    return args.getInt("position");
  }

  private List<Map<String, String>> getData() {
    List<Map<String, String>> list = new ArrayList<>();
    int start = getPosition() * ITEM_COUNT;
    int end = start + ITEM_COUNT;
    for (int i = start; i < end; ++i) {
      Map<String, String> map = new HashMap<>();
      map.put(FROM[0], "Title " + i);
      map.put(FROM[1], "Subtitle " + i);
      list.add(map);
    }
    return list;
  }
}
