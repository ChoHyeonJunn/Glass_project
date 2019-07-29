package com.example.u.registeration;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
     * A placeholder fragment containing a simple view.
     */
    public class UsageFragment1 extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public UsageFragment1() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static UsageFragment1 newInstance(int sectionNumber) {
            UsageFragment1 fragment = new UsageFragment1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        private RecyclerView usageView;
        private UsageAdapter adapter;
        private RecyclerView.LayoutManager usageManager;
        private ArrayList<Usage> usageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usage1, container, false);


        usageView = (RecyclerView) view.findViewById(R.id.using);
        usageManager = new LinearLayoutManager(getActivity());
        usageView.setLayoutManager(usageManager);
        adapter = new UsageAdapter(this);
        usageView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new BackgroundTask().execute();
    }

    //사용자의 배정 내역을 가져오는 backgroundTask

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            try {
                target = "http://wjstkdduq.cafe24.com/ScheduleList.php?userID=" + URLEncoder.encode(UsageActivity.userID, "UTF-8");

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
                usageList = new ArrayList<Usage>();


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

                    Usage usage = new Usage(DY, STRT_TM, END_TM, CMPS_NM, BULD_NM, CLSRM_NM);
                    usageList.add(usage);
                    adapter.add(usage);
                    count++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}