package com.nf.dao.test;

import com.nf.interfaces.IGoodsTypeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration({"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
//@TransactionConfiguration(defaultRollback = true)  //被弃用
@Rollback()  //是否回滚
public class IGoodsTypeTest {


    @Autowired
    IGoodsTypeMapper goodsMapper;

    @Test
    public void testSelectAllGoodsType(){
        System.out.println(goodsMapper.selectAllGoodsType());
    }
}
