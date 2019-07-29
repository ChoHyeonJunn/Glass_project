package com.example.u.registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class FirstActivity extends AppCompatActivity {

    public static String userID;
    //로그인 후 firstactivity로 전달된 userID를 public static 변수로 설정해 모든곳에서 접근 가능하도록 설정..

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        userID = getIntent().getStringExtra("userID");

        final ImageButton findButton = (ImageButton) findViewById(R.id.button_findClass);
        final ImageButton usageButton = (ImageButton) findViewById(R.id.button_usage);
        final ImageButton recruitButton = (ImageButton) findViewById(R.id.button_recruit);

        //강의실 검색 버튼 클릭이벤트에 RenewIimeBase 실행
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> FresponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("userID", userID);
                                startActivity(intent);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RenewTimeBase renewTimeBase = new RenewTimeBase(userID, FresponseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(renewTimeBase);
            }
        });

        //사용현황 버튼 클릭이벤트에 RenewIimeBase 실행
        usageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> IresponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(getApplicationContext(), UsageActivity.class);
                                intent.putExtra("userID", userID);
                                startActivity(intent);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                RenewTimeBase IrenewTimeBase = new RenewTimeBase(userID, IresponseListener);
                RequestQueue Iqueue = Volley.newRequestQueue(getApplicationContext());
                Iqueue.add(IrenewTimeBase);
            }
        });

        recruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecruitActivity.class);
                startActivity(intent);
            }
        });
    }
}
