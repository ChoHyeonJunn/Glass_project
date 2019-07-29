package com.example.u.registeration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GPSBottomSheetDialog extends BottomSheetDialogFragment implements  View.OnClickListener{

    public static GPSBottomSheetDialog getInstance() { return new GPSBottomSheetDialog();}

    private ExpandableListView gpsListView;
    private GPSListAdapter adapter;
    private List<String> GPSGroupList;
    private HashMap<String, ArrayList<GPSChild>> GPSChildList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gps_bottom_sheet_dialog, container,false);
        //gpsListView = (ExpandableListView) v.findViewById(R.id.gpsListView);

        gpsListView = (ExpandableListView) getView().findViewById( R.id.gpsListView );
        GPSGroupList = new ArrayList<String>(  );
        GPSChildList = new HashMap<String, ArrayList<GPSChild>>(  );

        return v;
    }

    @Override
    public void onClick(View view) {

    }
}
