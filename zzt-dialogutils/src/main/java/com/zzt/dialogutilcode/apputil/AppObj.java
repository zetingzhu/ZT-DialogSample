package com.zzt.dialogutilcode.apputil;

import java.io.Serializable;

/**
 * Created by adu on 2019/3/14.
 */

public class AppObj implements Serializable {
    private String an;//应用名称
    private String ap;//应用包名
    private String it;//安装时间
    private String ut;//更新安装时间

    private long ll; //流量

    private long lt; //最后打开时间
    private long rt; //app运行时间
    private int oc; //打开次数


    public AppObj() {
    }

    public AppObj(String an, String ap) {
        this.an = an;
        this.ap = ap;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getUt() {
        return ut;
    }

    public void setUt(String ut) {
        this.ut = ut;
    }

    public int getOc() {
        return oc;
    }

    public void setOc(int oc) {
        this.oc = oc;
    }

    public long getLl() {
        return ll;
    }

    public void setLl(long ll) {
        this.ll = ll;
    }

    public long getLt() {
        return lt;
    }

    public void setLt(long lt) {
        this.lt = lt;
    }

    public long getRt() {
        return rt;
    }

    public void setRt(long rt) {
        this.rt = rt;
    }

    @Override
    public String toString() {
        return "AppObj{" +
                "an='" + an + '\'' +
                ", ap='" + ap + '\'' +
                ", it='" + it + '\'' +
                ", ut='" + ut + '\'' +
                '}';
    }
}
