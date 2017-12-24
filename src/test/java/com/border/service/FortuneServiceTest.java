package com.border.service;

import com.border.db.FortuneDB;
import com.border.service.impl.FortuneServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhou on 2017/12/24.
 */
public class FortuneServiceTest {

    private  FortuneService fortuneService;

    private FortuneDB fortuneDB = null;

    private static final Map<Long,String> map = new ConcurrentHashMap<>();

    private static final List<Long> list = Collections.synchronizedList(new ArrayList<>());

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

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

    @Test
    public void testConcurrency(){
        Random random = new Random();

        for (int i = 0;i<100;i++){
            int index = random.nextInt(3);
            switch (index){
                case 1:
                    executorService.submit(new FortuneAddRunnable(fortuneService,"test"+i));
                    break;
                case 2:
                    executorService.submit(new FortuneDeleteRunnable(fortuneService,i));
                    break;
                case 3:
                    executorService.submit(new FortuneQueryRunnable(fortuneService));

            }
        }
    }



    static class FortuneAddRunnable implements Runnable{

        private FortuneService fortuneService = null;

        private String message;
        public FortuneAddRunnable(FortuneService fortuneService,String message){
            this.fortuneService = fortuneService;
            this.message = message;
        }
        @Override
        public void run() {
            fortuneService.create(message);
        }
    }

    static class FortuneQueryRunnable implements Runnable{

        private FortuneService fortuneService = null;

        public FortuneQueryRunnable(FortuneService fortuneService){
            this.fortuneService = fortuneService;
        }
        @Override
        public void run() {
            fortuneService.find();
        }
    }

    static class FortuneDeleteRunnable implements Runnable{

        private FortuneService fortuneService = null;

        private long fortuneId;
        public FortuneDeleteRunnable(FortuneService fortuneService,long fortuneId){
            this.fortuneService = fortuneService;
            this.fortuneId = fortuneId;
        }
        @Override
        public void run() {
            fortuneService.delete(fortuneId);
        }
    }
}
