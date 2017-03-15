package com.myexpandablelistview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 创建者：admin
 * <p>时间：2017/3/15 11:15
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    ArrayList<ArrayList<String>> mlist;
    ExpandableListView elv;

    public ExpandableListViewAdapter(ArrayList<ArrayList<String>> mlist, ExpandableListView elv) {
        this.mlist = mlist;
        this.elv = elv;
    }

    /**
     * 父条目的数量
     */
    @Override
    public int getGroupCount() {
        return mlist.size();
    }

    /**
     * 子条目的数量
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mlist.get(groupPosition).size();
    }

    /**
     * 父条目的布局
     */
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        View inflate = View.inflate(parent.getContext(), R.layout.item_1, null);
        TextView delete = (TextView) inflate.findViewById(R.id.delete);
        TextView tv = (TextView) inflate.findViewById(R.id.tv);
        tv.setText("父条目" + groupPosition);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.remove(groupPosition);
                notifyDataSetChanged();
                for (int i = 0; i < mlist.size(); i++) {
                    elv.expandGroup(i);
                }
            }
        });
        return inflate;
    }

    /**
     * 子条目的布局
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View inflate = View.inflate(parent.getContext(),
                R.layout.item_2, null);
        TextView ztv = (TextView) inflate.findViewById(R.id.ztv);
        String object = mlist.get(groupPosition).get(childPosition);
        ztv.setText(object);
        return inflate;
    }

    @Override
    public Object getChild(int arg0, int arg1) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override//TODO 就改了这里，把返回0改成了groupPosition
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

