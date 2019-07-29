package com.example.u.registeration;

public class GPSChild {

    String COURSE_NM;
    String STRT_TM;
    String END_TM;
    String PROFSR_NM;
    String CLSTM_CD;

    public String getCOURSE_NM() {
        return COURSE_NM;
    }

    public void setCOURSE_NM(String COURSE_NM) {
        this.COURSE_NM = COURSE_NM;
    }

    public String getSTRT_TM() {
        return STRT_TM;
    }

    public void setSTRT_TM(String STRT_TM) {
        this.STRT_TM = STRT_TM;
    }

    public String getEND_TM() {
        return END_TM;
    }

    public void setEND_TM(String END_TM) {
        this.END_TM = END_TM;
    }

    public String getPROFSR_NM() {
        return PROFSR_NM;
    }

    public void setPROFSR_NM(String PROFSR_NM) {
        this.PROFSR_NM = PROFSR_NM;
    }

    public String getCLSTM_CD() {
        return CLSTM_CD;
    }

    public void setCLSTM_CD(String CLSTM_CD) {
        this.CLSTM_CD = CLSTM_CD;
    }

    public GPSChild(String COURSE_NM, String STRT_TM, String END_TM, String PROFSR_NM, String CLSTM_CD) {
        this.COURSE_NM = COURSE_NM;
        this.STRT_TM = STRT_TM;
        this.END_TM = END_TM;
        this.PROFSR_NM = PROFSR_NM;
        this.CLSTM_CD = CLSTM_CD;
    }
}
