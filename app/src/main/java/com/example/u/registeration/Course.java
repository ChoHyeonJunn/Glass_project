package com.example.u.registeration;

public class Course {

    String CLSRM_NM;    //강의실명
    String CMPS_NM;     //캠퍼스명
    int COURSE_CPCT;    //정원

    public String getCLSRM_NM() {
        return CLSRM_NM;
    }

    public void setCLSRM_NM(String CLSRM_NM) {
        this.CLSRM_NM = CLSRM_NM;
    }

    public String getCMPS_NM() {
        return CMPS_NM;
    }

    public void setCMPS_NM(String CMPS_NM) {
        this.CMPS_NM = CMPS_NM;
    }

    public int getCOURSE_CPCT() {
        return COURSE_CPCT;
    }

    public void setCOURSE_CPCT(int COURSE_CPCT) {
        this.COURSE_CPCT = COURSE_CPCT;
    }

    public Course(String CLSRM_NM, String CMPS_NM, int COURSE_CPCT) {
        this.CLSRM_NM = CLSRM_NM;
        this.CMPS_NM = CMPS_NM;
        this.COURSE_CPCT = COURSE_CPCT;
    }
}
