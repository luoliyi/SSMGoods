package com.nf.Entities;

public class GoodsType {
    private int tid;
    private String tname;

    public GoodsType() {
    }

    public GoodsType(int tid) {
        this.tid = tid;
    }

    public GoodsType(String tname) {
        this.tname = tname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                '}';
    }

    public GoodsType(int tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }
}
