package com.nf.Entities;

import java.util.List;

public class GoodsDetails {

    private int gid;
    private String gname;
    private double gprice;
    private String gpicture;
    private int tid;

    /*查询单个对象*/
    private GoodsType goodsType;

    /*查询多个对象*/
    private List<GoodsType> goodsTypeList;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getGprice() {
        return gprice;
    }

    public void setGprice(double gprice) {
        this.gprice = gprice;
    }

    public String getGpicture() {
        return gpicture;
    }

    public void setGpicture(String gpicture) {
        this.gpicture = gpicture;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public List<GoodsType> getGoodsTypeList() {
        return goodsTypeList;
    }

    public void setGoodsTypeList(List<GoodsType> goodsTypeList) {
        this.goodsTypeList = goodsTypeList;
    }

    public GoodsDetails() {
    }

    public GoodsDetails(String gname, double gprice, String gpicture, int tid, List<GoodsType> goodsTypeList) {
        this.gname = gname;
        this.gprice = gprice;
        this.gpicture = gpicture;
        this.tid = tid;
        this.goodsTypeList = goodsTypeList;
    }

    public GoodsDetails(String gname, double gprice, String gpicture, int tid) {
        this.gname = gname;
        this.gprice = gprice;
        this.gpicture = gpicture;
        this.tid = tid;
    }

    public GoodsDetails(String gname, double gprice, String gpicture, int tid, GoodsType goodsType) {
        this.gname = gname;
        this.gprice = gprice;
        this.gpicture = gpicture;
        this.tid = tid;
        this.goodsType = goodsType;
    }

    public GoodsDetails(int gid, String gname, double gprice, String gpicture, int tid, List<GoodsType> goodsTypeList) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gpicture = gpicture;
        this.tid = tid;
        this.goodsTypeList = goodsTypeList;
    }

    public GoodsDetails(int gid, String gname, double gprice, String gpicture, int tid, GoodsType goodsType) {
        this.gid = gid;
        this.gname = gname;
        this.gprice = gprice;
        this.gpicture = gpicture;
        this.tid = tid;
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "GoodsDetails{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gprice=" + gprice +
                ", gpicture='" + gpicture + '\'' +
                ", tid=" + tid +
                ", goodsType=" + goodsType +
                ", goodsTypeList=" + goodsTypeList +
                '}';
    }
}
