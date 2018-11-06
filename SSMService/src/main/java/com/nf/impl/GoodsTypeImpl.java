package com.nf.impl;

import com.nf.Entities.GoodsType;
import com.nf.service.IGoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class GoodsTypeImpl implements IGoodsType {

    @Autowired
    /*自动装配*/
    IGoodsType iGoodsType;

    @Override
    public int insert(GoodsType goodsType) {
        return iGoodsType.insert(goodsType);
    }

    @Override
    public int delete(int tid) {
        return iGoodsType.delete(tid);
    }

    @Override
    public List<GoodsType> selectAllGoodsType() {
        return iGoodsType.selectAllGoodsType();
    }

    @Override
    public int update(GoodsType goodsType) {
        return iGoodsType.update(goodsType);
    }
}
