package com.example.u.registeration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GPSListAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<String> gpsArrayGroup;
    private HashMap<String, ArrayList<GPSChild>> gpsArrayChild;

    public GPSListAdapter(Context context, List<String> gpsArrayGroup, HashMap<String, ArrayList<GPSChild>> gpsArrayChild){
        super();
        this.context = context;
        this.gpsArrayGroup = gpsArrayGroup;
        this.gpsArrayChild = gpsArrayChild;
    }

    @Override
    public int getGroupCount() {
        return gpsArrayGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gpsArrayGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition);
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            v = (LinearLayout)inflater.inflate( R.layout.lvi_group, null );
        }
        TextView DV = (TextView) v.findViewById( R.id.textGroup );
        DV.setText( gpsArrayGroup.get(groupPosition) );

        v.setTag( gpsArrayGroup.get(groupPosition) );

        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout)inflater.inflate( R.layout.lvi_child, null );
        }
        TextView STRT_TM = (TextView) v.findViewById(R.id.start_tm);
        TextView END_TM = (TextView) v.findViewById(R.id.end_tm);
        TextView COURSE_NM = (TextView) v.findViewById(R.id.textChild);
        TextView PROFSR_NM = (TextView) v.findViewById(R.id.textProfessor);
        TextView CLSTM_CD = (TextView) v.findViewById(R.id.classtime);

        COURSE_NM.setText(gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition).getCOURSE_NM() + " / ");
        PROFSR_NM.setText(gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition).getPROFSR_NM());
        STRT_TM.setText(gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition).getSTRT_TM() + " ~ ");
        END_TM.setText(gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition).getEND_TM());
        CLSTM_CD.setText(gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition).getCLSTM_CD()+"교시");

        v.setTag( gpsArrayChild.get(gpsArrayGroup.get(groupPosition)).get(childPosition).getCOURSE_NM() );
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
