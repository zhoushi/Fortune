package com.border.service;

import com.border.db.FortuneDB;
import com.border.service.impl.FortuneServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhou on 2017/12/24.
 */
public class FortuneServiceTest {

    private  FortuneService fortuneService;

    private FortuneDB fortuneDB = null;

    private static final Map<Long,String> map = new ConcurrentHashMap<>();

    private static final List<Long> list = Collections.synchronizedList(new ArrayList<>());

    @Before
    public void getFortuneService(){

        map.put(1L,"1111");
        map.put(2L,"2222");
        map.put(3L,"3333");

        for (Long key:map.keySet()) {
            list.add(key);
        }
        fortuneDB = new FortuneDB(map,list,list.size());
        fortuneService =new FortuneServiceImpl(fortuneDB);
    }

    /**
     * 查询
     */
    @Test
    public void query(){
        String s = fortuneService.find();
        Assert.assertNotNull(s);
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        boolean actual = fortuneService.delete(2);
        Assert.assertTrue(actual);
    }

    /**
     * 新增
     */
    @Test
    public void create(){
        long id = fortuneService.create("4444");
        Assert.assertEquals(4,id);
    }

    /**
     *  先删除后添加
     */
    @Test
    public void deleteThenAdd(){
        boolean actual = fortuneService.delete(2);
        Assert.assertTrue(actual);
        long id = fortuneService.create("444");
        Assert.assertEquals(4,id);
    }

    /**
     * 先删除后查询改值
     */
    @Test
    public void deleteThenQuery(){
        boolean actual = fortuneService.delete(2);
        System.out.println(fortuneDB.getFortuneMap().toString());
        Assert.assertTrue(actual);
        String s = fortuneService.find();
        Assert.assertNotNull(s);
    }

    /**
     * 先添加后删除
     */
    @Test
    public void addThenDelete(){
        long id = fortuneService.create("4444");
        Assert.assertEquals(4,id);
        boolean actual = fortuneService.delete(4);

        Assert.assertTrue(actual);

    }

    /**
     * 先添加后查询
     */
    @Test
    public void addThenQuery(){
        long id = fortuneService.create("4444");
        Assert.assertEquals(4,id);
        String s = fortuneService.find();
        Assert.assertNotNull(s);

    }
}
