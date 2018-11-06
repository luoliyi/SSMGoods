package com.nf.service;

import com.nf.Entities.GoodsDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface IGoodsDetails {
    int insert(GoodsDetails goodsDetails);
    int update(GoodsDetails goodsDetails);
    int delete(int gid);
    List<GoodsDetails> selectAllGoodsAndType();
    List<GoodsDetails>selectAllGoodsAndTypeByLimit(Map<String,Object> objectMap);
    int deleteAll(List<Integer>ids);
}
