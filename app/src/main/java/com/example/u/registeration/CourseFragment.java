package com.example.u.registeration;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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

    //여기서부터 코드 시작~!~!
        /*4가지 모델 스피너를 변수로 선언*/
    private ArrayAdapter DYAdapter;
    private Spinner DYSpinner;
    private ArrayAdapter STRT_TMAdapter;
    private Spinner STRT_TMSpinner;
    private ArrayAdapter END_TMAdapter;
    private Spinner END_TMSpinner;
    private ArrayAdapter BULD_NMAdapter;
    private Spinner BULD_NMSpinner;


    private String courseUniversity="";

    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;

    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);

        final RadioGroup courseUniversityGroup=(RadioGroup) getView().findViewById(R.id.courseUniversityGroup);
        DYSpinner = (Spinner) getView().findViewById(R.id.DYSpinner);
        STRT_TMSpinner = (Spinner) getView().findViewById(R.id.STRT_TMSpinner);
        END_TMSpinner = (Spinner) getView().findViewById(R.id.END_TMSpinner);
        BULD_NMSpinner = (Spinner) getView().findViewById(R.id.BULD_NMSpinner);

        courseUniversityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radiogroup, int i) {
                RadioButton courseButton = (RadioButton) getView().findViewById(i);//현재 선택된 라디오 버튼이 담김
                courseUniversity = courseButton.getText().toString();

                DYAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.DY, android.R.layout.simple_spinner_dropdown_item);
                DYSpinner.setAdapter(DYAdapter);

                STRT_TMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.STRT_TM, android.R.layout.simple_spinner_dropdown_item);
                STRT_TMSpinner.setAdapter(STRT_TMAdapter);

                END_TMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.END_TM, android.R.layout.simple_spinner_dropdown_item);
                END_TMSpinner.setAdapter(END_TMAdapter);

                BULD_NMAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.BULD_NM, android.R.layout.simple_spinner_dropdown_item);
                BULD_NMSpinner.setAdapter(BULD_NMAdapter);

            }
        });

        courseListView = (ListView) getView().findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        adapter = new CourseListAdapter(getContext().getApplicationContext(), courseList, DYSpinner, STRT_TMSpinner, END_TMSpinner, BULD_NMSpinner, this);
        courseListView.setAdapter(adapter);

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
        return inflater.inflate(R.layout.fragment_course, container, false);
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

    class BackgroundTask extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://wjstkdduq.cafe24.com/CourseList.php?courseUniversity=" + URLEncoder.encode(courseUniversity, "UTF-8") +
                        "&DY=" + URLEncoder.encode(DYSpinner.getSelectedItem().toString(), "UTF-8") +
                        "&STRT_TM=" + URLEncoder.encode(STRT_TMSpinner.getSelectedItem().toString(), "utf-8") +
                        "&END_TM=" + URLEncoder.encode(END_TMSpinner.getSelectedItem().toString(), "utf-8") +
                        "&BULD_NM=" + URLEncoder.encode(BULD_NMSpinner.getSelectedItem().toString(), "utf-8");

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
                courseList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                int count = 0;

                String CLSRM_NM;    //강의실명
                String CMPS_NM;     //캠퍼스명
                int COURSE_CPCT;    //정원

                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);

                    CLSRM_NM = object.getString("CLSRM_NM");
                    CMPS_NM = object.getString("CMPS_NM");
                    COURSE_CPCT = object.getInt("COURSE_CPCT");
                    Course course = new Course(CLSRM_NM, CMPS_NM, COURSE_CPCT);
                    courseList.add(course);
                    count++;
                }

                if(count == 0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseFragment.this.getActivity());
                    dialog = builder.setMessage("조회된 강의실이 없습니다.\n날짜를 확인하세요.")
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


