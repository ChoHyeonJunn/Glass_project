package com.example.u.registeration;

import android.widget.Spinner;

public class Schedule {

    private String mondy[] = new String[14];
    private String tuesday[] = new String[14];
    private String wednesday[] = new String[14];
    private String thursday[] = new String[14];
    private String friday[] = new String[14];

    public Schedule(){
        for(int i=0;i<14;i++){
            mondy[i] = "";
            tuesday[i] = "";
            wednesday[i] = "";
            thursday[i] = "";
            friday[i] = "";
        }
    }

    public void addSchedule(String scheduleText){
        int temp;
        if((temp = scheduleText.indexOf("월"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    mondy[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "선택";
                }
            }
        }
        if((temp = scheduleText.indexOf("화"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "선택";
                }
            }
        }
        if((temp = scheduleText.indexOf("수"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "선택";
                }
            }
        }
        if((temp = scheduleText.indexOf("목"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "선택";
                }
            }
        }
        if((temp = scheduleText.indexOf("금"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "선택";
                }
            }
        }
    }

    public boolean validate(String DYSpinner, String STRT_TMSpinner, String END_TMSpinner){
        if(DYSpinner.equals("")){
            return true;
        }
        return false;
        /*
        if(scheduleText.equals("")){
            return true;
        }
        int temp;
        if((temp = DYSpinner.indexOf("월"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!mondy[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("화"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("수"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("목"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!mondy[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("금"))>-1){
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i<scheduleText.length() && scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i)=='[')
                    startPoint = i;
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    if(!mondy[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        */
    }

}
