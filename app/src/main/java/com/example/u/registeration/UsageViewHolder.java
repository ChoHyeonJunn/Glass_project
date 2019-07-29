package com.example.u.registeration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


// ViewHolder 클래스 - 반복되는 리스트에서 매번 FindViewById 하지 않고
// 뷰들을 홀딩하고 있다가 계속 재활용해서 사용할 수 있다.

public class UsageViewHolder extends RecyclerView.ViewHolder {
    public TextView campus, classRoom, usingTime, mMessageView;
    public Button btnDel;

    public UsageViewHolder(View itemView){
        super(itemView);

        campus = (TextView) itemView.findViewById(R.id.campus);
        classRoom = (TextView) itemView.findViewById(R.id.classRoom);
        usingTime = (TextView) itemView.findViewById(R.id.usingTime);
        btnDel = (Button) itemView.findViewById(R.id.delButton);
        mMessageView = (TextView) itemView.findViewById(R.id.empty_view);
    }

}
