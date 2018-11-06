package com.nf.impl;

import com.nf.Entities.GoodsDetails;
import com.nf.interfaces.IGoodsDetailsMapper;
import com.nf.service.IGoodsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Repository
public class GoodsDetailsImpl implements IGoodsDetails {

    @Autowired
    IGoodsDetailsMapper iGoodsDetailsMapper;

    @Override
    public int insert(GoodsDetails goodsDetails) {
        return iGoodsDetailsMapper.insert(goodsDetails);
    }

    @Override
    public int update(GoodsDetails goodsDetails) {
        return iGoodsDetailsMapper.update(goodsDetails);
    }

    @Override
    public int delete(int gid) {
        return iGoodsDetailsMapper.delete(gid);
    }

    @Override
    public List<GoodsDetails> selectAllGoodsAndType() {
        return iGoodsDetailsMapper.selectAllGoodsAndType();
    }

    @Override
    public List<GoodsDetails> selectAllGoodsAndTypeByLimit(Map<String, Object> objectMap) {
        return iGoodsDetailsMapper.selectAllGoodsAndTypeByLimit(objectMap);
    }

    @Override
    public int deleteAll(List<Integer> ids) {
        return iGoodsDetailsMapper.deleteAll(ids);
    }
}
