package com.border.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhou on 2017/12/23.
 */
public class FortuneDBTest {

    private FortuneDB fortuneDB = null;

    private static final Map<Long,String> map = new ConcurrentHashMap<>();

    private static final List<Long> list = Collections.synchronizedList(new ArrayList<>());

    @Before
    public void getFortuneDB(){
        map.put(1L,"1111");
        map.put(2L,"2222");
        map.put(3L,"3333");

        for (Long key:map.keySet()) {
            list.add(key);
        }
        fortuneDB = new FortuneDB(map,list,list.size());
    }

    /**
     * 添加
     */
    @Test
    public void create(){
        long id = fortuneDB.createFortune("4444");
        Assert.assertEquals(4,id);
    }

    /**
     * 删除
     */
    @Test
    public void delete(){
        boolean actual = fortuneDB.delete(2);
        Assert.assertTrue(actual);
    }

    /**
     * 查询
     */
    @Test
    public void query(){
        String s = fortuneDB.findFortune(1);
        Assert.assertNotNull(s);
    }

    /**
     * 当超过查询的fortuneId时
     */
    @Test
    public void thanQuery(){
        String s = fortuneDB.findFortune(10);
        Assert.assertNull(s);
    }

    /**
     *  先删除后添加
     */
    @Test
    public void deleteThenAdd(){
        boolean actual = fortuneDB.delete(2);
        Assert.assertTrue(actual);
        long id = fortuneDB.createFortune("4444");
        Assert.assertEquals(4,id);
    }

    /**
     * 先删除后查询改值
     */
    @Test
    public void deleteThenQuery(){
        boolean actual = fortuneDB.delete(2);
        Assert.assertTrue(actual);
        String s = fortuneDB.findFortune(2);
        Assert.assertNull(s);
    }

    /**
     * 先添加后删除
     */
    @Test
    public void addThenDelete(){
        long id = fortuneDB.createFortune("4444");
        Assert.assertEquals(4,id);
        boolean actual = fortuneDB.delete(4);

        Assert.assertTrue(actual);

    }

    /**
     * 先添加后查询
     */
    @Test
    public void addThenQuery(){
        long id = fortuneDB.createFortune("4444");
        Assert.assertEquals(4,id);
        String s = fortuneDB.findFortune(3);
        Assert.assertNotNull(s);

    }


}
