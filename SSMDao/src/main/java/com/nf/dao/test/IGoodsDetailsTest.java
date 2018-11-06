package com.nf.dao.test;

import com.nf.Entities.GoodsDetails;
import com.nf.interfaces.IGoodsDetailsMapper;
import com.nf.interfaces.IGoodsTypeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration({"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
//@TransactionConfiguration(defaultRollback = true)  //被弃用
@Rollback()  //是否回滚
public class IGoodsDetailsTest {


    @Autowired
    IGoodsDetailsMapper goodsDetailsMapper;

    @Test
    public void testSelectAllGoodsType(){
        Map<String ,Object> objectMap=new HashMap<>();
        objectMap.put("tname","小");
        objectMap.put("gname","");
        objectMap.put("tid","");
        objectMap.put("startprice",1);
        objectMap.put("endprice",999);
        objectMap.put("pageno",0);
        objectMap.put("size",3);
        List<GoodsDetails> g=goodsDetailsMapper.selectAllGoodsAndTypeByLimit(objectMap);
        for (GoodsDetails goodsDetails:g){
            System.out.println(goodsDetails);
        }

    }

    @Test
    public void testDeleteAll(){
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(6);
        System.out.println(goodsDetailsMapper.deleteAll(list));

    }

    @Test
    public void selectAllGoodsAndType(){
        List<GoodsDetails> goodsDetails=goodsDetailsMapper.selectAllGoodsAndType();
        System.out.println(goodsDetails);
    }
}
