package com.example.u.registeration;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //캠퍼스 그룹
    private String courseUniversity="";

    //건물, 강의실명 관련 변수
    private ArrayAdapter BULD_NMAdapter;
    private Spinner BULD_NMSpinner;
    private ArrayAdapter CLSRM_NMAdapter;
    private Spinner CLSRM_NMSpinner;

    //익스펜더블리스트 관련 변수
    private ExpandableListView scheduleListView;
    private ScheduleListAdapter adapter;
    private List<String> ScheduleGroupList;
    private HashMap<String, ArrayList<ScheduleChild>> ScheduleChildList;

    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        final RadioGroup courseUniversityGroup=(RadioGroup) getView().findViewById(R.id.courseUniversityGroup);
        BULD_NMSpinner = (Spinner) getView().findViewById(R.id.BULD_NMSpinner);
        CLSRM_NMSpinner = (Spinner) getView().findViewById(R.id.CLSRM_NMSpinner);

        courseUniversityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                RadioButton courseButton = (RadioButton) getView().findViewById(i);//현재 선택된 라디오 버튼이 담김
                courseUniversity = courseButton.getText().toString();

                BULD_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.BULD_NM, android.R.layout.simple_spinner_dropdown_item);
                BULD_NMSpinner.setAdapter(BULD_NMAdapter);



                BULD_NMSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //건물명이 선택됬을 때 ~
                        if(BULD_NMSpinner.getSelectedItem().equals("IT대학")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.IT대학, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("가천관")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.가천관, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("공과대학1")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.공과대학1, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("공과대학2")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.공과대학2, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("교육대학원")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.교육대학원, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("글로벌센터")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.글로벌센터, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("바이오나노대학")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.바이오나노대학, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("바이오나노연구")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.바이오나노연구, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("비전타워")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.비전타워, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("산학협력관")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.산학협력관, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("예술대학1")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.예술대학1, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("예술대학2")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.예술대학2, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                        if(BULD_NMSpinner.getSelectedItem().equals("한의과대학")){
                            CLSRM_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.한의과대학, android.R.layout.simple_spinner_dropdown_item);
                            CLSRM_NMSpinner.setAdapter(CLSRM_NMAdapter);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //건물명이 아직 선택되지 않았을 때~
                    }
                });

            }
        });
        scheduleListView = (ExpandableListView) getView().findViewById(R.id.scheduleListView);
        ScheduleGroupList = new ArrayList<String>();
        ScheduleChildList = new HashMap<String, ArrayList<ScheduleChild>>();
        adapter = new ScheduleListAdapter(getContext().getApplicationContext(), ScheduleGroupList, ScheduleChildList);
        scheduleListView.setAdapter(adapter);

        Button searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask().execute();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //검색버튼 클릭시 background 작업으로 진행~
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://wjstkdduq.cafe24.com/FindClass.php?courseUniversity=" + URLEncoder.encode(courseUniversity, "UTF-8") +
                                                 "&BULD_NM=" + URLEncoder.encode(BULD_NMSpinner.getSelectedItem().toString(), "utf-8")+
                                                 "&CLSRM_NM=" + URLEncoder.encode(CLSRM_NMSpinner.getSelectedItem().toString(), "utf-8");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids){
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result){
            try{
                ScheduleGroupList.clear();
                ScheduleChildList.clear();

                ScheduleGroupList.add("월요일 MONDAY");
                ScheduleGroupList.add("화요일 TUESDAY");
                ScheduleGroupList.add("수요일 WEDNESDAY");
                ScheduleGroupList.add("목요일 THURSDAY");
                ScheduleGroupList.add("금요일 FRIDAY");

                ArrayList<ScheduleChild> arrayMon = new ArrayList<ScheduleChild>();
                ArrayList<ScheduleChild> arrayTue = new ArrayList<ScheduleChild>();
                ArrayList<ScheduleChild> arrayWed = new ArrayList<ScheduleChild>();
                ArrayList<ScheduleChild> arrayThu = new ArrayList<ScheduleChild>();
                ArrayList<ScheduleChild> arrayFri = new ArrayList<ScheduleChild>();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String DY;
                String CLSRM_NM;
                String STRT_TM;
                String END_TM;
                String PROFSR_NM;
                String CLSTM_CD;

                while(count<jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);

                    DY = object.getString("DY");
                    CLSRM_NM = object.getString("CLSRM_NM");
                    STRT_TM = object.getString("STRT_TM");
                    END_TM = object.getString("END_TM");
                    PROFSR_NM = object.getString("PROFSR_NM");
                    CLSTM_CD = object.getString("CLSTM_CD");

                    ScheduleChild scheduleChild = new ScheduleChild(CLSRM_NM, STRT_TM, END_TM, PROFSR_NM, CLSTM_CD);

                    if(DY.equals("월"))
                        arrayMon.add(scheduleChild);
                    else if(DY.equals("화"))
                        arrayTue.add(scheduleChild);
                    else if(DY.equals("수"))
                        arrayWed.add(scheduleChild);
                    else if(DY.equals("목"))
                        arrayThu.add(scheduleChild);
                    else if(DY.equals("금"))
                        arrayFri.add(scheduleChild);
                    count++;
                }

                ScheduleChildList.put(ScheduleGroupList.get(0), arrayMon);
                ScheduleChildList.put(ScheduleGroupList.get(1), arrayTue);
                ScheduleChildList.put(ScheduleGroupList.get(2), arrayWed);
                ScheduleChildList.put(ScheduleGroupList.get(3), arrayThu);
                ScheduleChildList.put(ScheduleGroupList.get(4), arrayFri);

                if(count == 0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScheduleFragment.this.getActivity());
                    dialog = builder.setMessage("조회된 수업이 없습니다..")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();

                }
                adapter.notifyDataSetChanged();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

