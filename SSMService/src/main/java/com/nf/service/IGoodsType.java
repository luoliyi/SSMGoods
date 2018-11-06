package com.nf.service;

import com.nf.Entities.GoodsType;

import java.util.List;

public interface IGoodsType {
    int insert(GoodsType goodsType);
    int delete(int tid);
    List<GoodsType> selectAllGoodsType();
    int update(GoodsType goodsType);

}
