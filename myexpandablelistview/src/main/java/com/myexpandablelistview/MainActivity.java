package com.myexpandablelistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;

/**
 * 删除父条目子条目的数量不会乱
 */
public class MainActivity extends Activity {

    private ExpandableListView elv;
    private ArrayList<ArrayList<String>> mlist;
    private ExpandableListViewAdapter ExpandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlist = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 5; i++) {
            ArrayList<String> arrayList = new ArrayList<String>();
            for (int n = 0; n < 5; n++) {
                arrayList.add("" + n);
            }
            mlist.add(arrayList);
        }

        elv = (ExpandableListView) findViewById(R.id.elv);
        elv.setGroupIndicator(null); // 去掉ExpandableListAdapter左边的箭头
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { //
            // 点击父条目不收缩的方法
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        ExpandableListViewAdapter = new ExpandableListViewAdapter(mlist, elv);
        elv.setAdapter(ExpandableListViewAdapter);
        int groupCount = elv.getCount();
        for (int i = 0; i < groupCount; i++) {// 这么设置就是让默认全都展开的
            elv.expandGroup(i);
        }
    }
}
