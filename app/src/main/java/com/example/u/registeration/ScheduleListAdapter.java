package com.example.u.registeration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ScheduleListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> arrayGroup;
    private HashMap<String, ArrayList<ScheduleChild>> arrayChild;

    public ScheduleListAdapter(Context context, List<String> arrayGroup, HashMap<String, ArrayList<ScheduleChild>> arrayChild){
        super();
        this.context = context;
        this.arrayGroup = arrayGroup;
        this.arrayChild = arrayChild;
    }

    @Override
    public int getGroupCount() {
        return arrayGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayChild.get(arrayGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout)inflater.inflate(R.layout.lvi_group, null);
        }
        TextView DY = (TextView) v.findViewById(R.id.textGroup);
        DY.setText(arrayGroup.get(groupPosition));

        v.setTag(arrayGroup.get(groupPosition));
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //String childName = arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout)inflater.inflate(R.layout.lvi_child, null);
        }
        TextView STRT_TM = (TextView) v.findViewById(R.id.start_tm);
        TextView END_TM = (TextView) v.findViewById(R.id.end_tm);
        TextView COURSE_NM = (TextView) v.findViewById(R.id.textChild);
        TextView PROFSR_NM = (TextView) v.findViewById(R.id.textProfessor);
        TextView CLSTM_CD = (TextView) v.findViewById(R.id.classtime);

        COURSE_NM.setText(arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition).getCOURSE_NM() + " / ");
        PROFSR_NM.setText(arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition).getPROFSR_NM());
        STRT_TM.setText(arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition).getSTRT_TM() + " ~ ");
        END_TM.setText(arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition).getEND_TM());
        CLSTM_CD.setText(arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition).getCLSTM_CD()+"교시");

        v.setTag(arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition).getCOURSE_NM());
        return v;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
