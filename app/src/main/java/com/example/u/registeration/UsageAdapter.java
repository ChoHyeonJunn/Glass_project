package com.example.u.registeration;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.u.registeration.UsageActivity.userID;

// UI와 데이터를 연결시켜 주는 Adapter클래스

public class UsageAdapter extends RecyclerView.Adapter<UsageViewHolder> {

    private Fragment parent;
    List<Usage> items = new ArrayList<>();

    public UsageAdapter(Fragment parent) {
        this.parent = parent;
    }

    //아이템을 추가할 수 있는 메소드(Usage 클래스에)
    public void add(Usage data) {
        items.add(data);
        notifyDataSetChanged();
    }

    private int viewtype;   //뷰타입을 임시저장할 변수!!

    //뷰 홀더를 어떻게 생성할 것이냐고 하는 부분!!
    //여기서 뷰 홀더 객체를 생성해서 return 해주면 된다.
    //이 뷰 홀더 객체 생성자가 인자로 view를 받기 때문에 이를 생성하기 위해 LayoutInflater를 사용
    @NonNull
    @Override
    public UsageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewtype = viewType;
        if (items.size() == 0) {
            View i = LayoutInflater.from(parent.getContext()).inflate(R.layout.emptyview_usage1, parent, false);
            return new UsageViewHolder(i);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.usage1, parent, false);
            return new UsageViewHolder(v);
        }

    }



    //뷰홀더를 데이터와 바인딩시킬때 어떻게 할것이냐 하는 부분
    //한줄한줄의 아이템에 holder를 통해 ui에 접근해서 데이터를 그려주는 것이 onBindViewHolder의 역할이다.
    @Override
    public void onBindViewHolder(@NonNull UsageViewHolder holder, final int position) {
        if (items.size() == 0) {
            holder.mMessageView.setText("배정내역이 없습니다.");
        }
        else {
            final Usage item = items.get(position);
            holder.campus.setText("캠퍼스 : " + item.CMPS_NM);
            holder.classRoom.setText("강의실 : " + item.CLSRM_NM);
            holder.usingTime.setText(item.DY + "요일 - " + item.STRT_TM + "시 ~ " + item.END_TM + "시");

            Button delButton = holder.btnDel;
            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                    AlertDialog.Builder builder1 = builder;
                    builder1.setMessage("정말 삭제하시겠습니까?");
                    builder1.setPositiveButton("삭제",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonResponse = new JSONObject(response);
                                                boolean success = jsonResponse.getBoolean("success");
                                                if (success) {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                                    AlertDialog dialog = builder.setMessage("삭제되었습니다.")
                                                            .setPositiveButton("확인", null)
                                                            .create();
                                                    dialog.show();
                                                    items.remove(position);
                                                    notifyDataSetChanged();
                                                } else {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                                    AlertDialog dialog = builder.setMessage("삭제에 실패하였습니다.")
                                                            .setNegativeButton("확인", null)
                                                            .create();
                                                    dialog.show();
                                                }

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    };
                                    DeleteRequest deleteRequest = new DeleteRequest(userID, item.getDY(), item.STRT_TM, item.END_TM, item.getCMPS_NM(), item.BULD_NM,
                                            item.CLSRM_NM, responseListener);
                                    RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                                    queue.add(deleteRequest);
                                }
                            });
                    builder1.setNegativeButton("취소", null);
                    AlertDialog dialog = builder1.create();
                    dialog.show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
