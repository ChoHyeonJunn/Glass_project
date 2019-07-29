package com.example.u.registeration;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    private String userID = MainActivity.userID;
    private Schedule schedule = new Schedule();

    private List<String> courseIDList;

    private Spinner DYSpinner;
    private Spinner STRT_TMSpinner;
    private Spinner END_TMSpinner;
    private Spinner BULD_NMSpinner;

    public CourseListAdapter(Context context, List<Course> courseList, Spinner DYSpinner, Spinner STRT_TMSpinner, Spinner END_TMSpinner, Spinner BULD_NMSpinner, Fragment parent) {
        this.context = context;
        this.courseList = courseList;
        this.parent = parent;

        this.DYSpinner = DYSpinner;
        this.STRT_TMSpinner = STRT_TMSpinner;
        this.END_TMSpinner = END_TMSpinner;
        this.BULD_NMSpinner = BULD_NMSpinner;

        schedule = new Schedule();
        courseIDList = new ArrayList<String>();

        //일반검색 버튼 누를 때 backgroundTask 작동
        new BackgroundTask().execute();

    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int i) {
        return courseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course, null);
        TextView CLSRM_NM = (TextView) v.findViewById(R.id.CLSRM_NM);
        TextView CMPS_NM = (TextView) v.findViewById(R.id.CMPS_NM);
        TextView COURSE_CPCT = (TextView) v.findViewById(R.id.COURSE_CPCT);

        if (Objects.equals(courseList.get(i).getCOURSE_CPCT(), "999")) {
            COURSE_CPCT.setText("인원 제한 없음");
        } else {
            COURSE_CPCT.setText("정원 : " + courseList.get(i).getCOURSE_CPCT());
        }
        CLSRM_NM.setText("강의실명 : " + courseList.get(i).getCLSRM_NM());
        CMPS_NM.setText("캠퍼스명 : " + courseList.get(i).getCMPS_NM());
        v.setTag(courseList.get(i).getCLSRM_NM());


        Button addButton = (Button) v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //선택 버튼 누를 때 backgroundTask 작동
                new BackgroundTask().execute();


                boolean validate = false;
                validate = schedule.validate(DYSpinner.getSelectedItem().toString(), STRT_TMSpinner.getSelectedItem().toString(), END_TMSpinner.getSelectedItem().toString());

                if (!alreadyIn(courseIDList)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog dialog = builder.setMessage("이미 선택한 강의실이 존재합니다." + courseIDList)
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                }
                else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("강의실이 선택되었습니다." + courseIDList)
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                    courseIDList.add(DYSpinner.getSelectedItem().toString());

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                    AlertDialog dialog = builder.setMessage("강의실 선택에 실패하였습니다.")
                                            .setNegativeButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    AddRequest addRequest = new AddRequest(userID, DYSpinner.getSelectedItem().toString(), STRT_TMSpinner.getSelectedItem().toString(),
                            END_TMSpinner.getSelectedItem().toString(), courseList.get(i).getCMPS_NM() + "", BULD_NMSpinner.getSelectedItem().toString(),
                            courseList.get(i).getCLSRM_NM() + "", responseListener);
                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                    queue.add(addRequest);
                }
            }

        });
        return v;
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://wjstkdduq.cafe24.com/ScheduleList.php?userID=" + URLEncoder.encode(userID, "UTF-8");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String DY;
                String STRT_TM;
                String END_TM;
                String CMPS_NM;
                String BULD_NM;
                String CLSRM_NM;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    DY = object.getString("DY");
                    STRT_TM = object.getString("STRT_TM");
                    END_TM = object.getString("END_TM");
                    CMPS_NM = object.getString("CMPS_NM");
                    BULD_NM = object.getString("BULD_NM");
                    CLSRM_NM = object.getString("CLSRM_NM");

                    courseIDList.add(DY);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean alreadyIn(List<String> courseIDList) {
        if (courseIDList.isEmpty()) {
            return true;
        }
        return false;
    }
}
