package com.nf.interfaces;

import com.nf.Entities.GoodsType;

import java.util.List;

public interface IGoodsTypeMapper {
    int insert(GoodsType goodsType);
    int delete(int tid);
    List<GoodsType> selectAllGoodsType();
    int update(GoodsType goodsType);
}
