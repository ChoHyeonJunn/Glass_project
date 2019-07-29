package com.example.u.registeration;

public class Usage {

    String DY;
    String STRT_TM;
    String END_TM;
    String CMPS_NM;
    String BULD_NM;
    String CLSRM_NM;

    public String getDY() {
        return DY;
    }

    public void setDY(String DY) {
        this.DY = DY;
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

    public String getCMPS_NM() {
        return CMPS_NM;
    }

    public void setCMPS_NM(String CMPS_NM) {
        this.CMPS_NM = CMPS_NM;
    }

    public String getBULD_NM() {
        return BULD_NM;
    }

    public void setBULD_NM(String BULD_NM) {
        this.BULD_NM = BULD_NM;
    }

    public String getCLSRM_NM() {
        return CLSRM_NM;
    }

    public void setCLSRM_NM(String CLSRM_NM) {
        this.CLSRM_NM = CLSRM_NM;
    }

    public Usage(String DY, String STRT_TM, String END_TM, String CMPS_NM, String BULD_NM, String CLSRM_NM) {
        this.DY = DY;
        this.STRT_TM = STRT_TM;
        this.END_TM = END_TM;
        this.CMPS_NM = CMPS_NM;
        this.BULD_NM = BULD_NM;
        this.CLSRM_NM = CLSRM_NM;
    }
}
