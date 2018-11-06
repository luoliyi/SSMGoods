package com.nf.interfaces;

import com.nf.Entities.GoodsDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IGoodsDetailsMapper {
    int insert(GoodsDetails goodsDetails);
    int update(GoodsDetails goodsDetails);
    int delete(int gid);

    List<GoodsDetails> selectAllGoodsAndType();
    List<GoodsDetails>selectAllGoodsAndTypeByLimit(Map<String,Object> objectMap);

    int deleteAll(@Param("ids") List<Integer>ids);

}
