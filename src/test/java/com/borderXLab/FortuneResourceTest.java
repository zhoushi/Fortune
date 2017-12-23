package com.borderXLab;

import com.borderXLab.resources.FortuneResource;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhou on 2017/12/23.
 * fortune测试
 */
public class FortuneResourceTest {

    private  FortuneResource fortuneResource = null;

    @Before
    public void getFortuneResource(){
        fortuneResource = new FortuneResource();
    }

    /**
     * 获取
     */
    @Test
    public void getFortune(){
        System.out.println(fortuneResource.getFortune());
    }

    /**
     * 添加
     */
    @Test
    public void putFortune(){
        System.out.println(fortuneResource.addFortunes("我"));
        Map<Long,String> map =  fortuneResource.getFortuneMap();
        System.out.println(map.toString());
        List<Long> list = fortuneResource.getFortuneSize();
        System.out.println(list.size());
    }

    /**
     * 删除
     */
    @Test
    public void deleteFortune(){
        System.out.println(fortuneResource.deleteFortunes(1));
        Map<Long,String> map =  fortuneResource.getFortuneMap();
        System.out.println(map.toString());
        List<Long> list = fortuneResource.getFortuneSize();
        System.out.println(list.size());

    }

    /**
     * 复合操作，删除过后添加
     */
    @Test
    public void testFortuneDeleteThenAdd(){

        System.out.println("删除的："+fortuneResource.deleteFortunes(4));
        System.out.println("新增的值："+fortuneResource.addFortunes("我"));
        Map<Long,String> map =  fortuneResource.getFortuneMap();
        System.out.println(map.toString());
        List<Long> list = fortuneResource.getFortuneSize();
        System.out.println(list.size());

        System.out.println("随机获取值："+fortuneResource.getFortune());
    }

    /**
     * 复合操作，添加过后删除
     */
    @Test
    public void testFortuneAddThenDelete(){
        System.out.println("添加的值："+fortuneResource.addFortunes("我"));
        Map<Long,String> oldMap =  fortuneResource.getFortuneMap();
        System.out.println("添加前："+oldMap.toString());
        List<Long> oldList = fortuneResource.getFortuneSize();
        System.out.println("添加前的数量："+oldList.size());

        System.out.println("删除操作2："+fortuneResource.deleteFortunes(-4));
        Map<Long,String> newMap =  fortuneResource.getFortuneMap();
        System.out.println("添加后："+newMap.toString());
        List<Long> newList = fortuneResource.getFortuneSize();
        System.out.println("添加后的数量："+newList.size());


    }



    @Test
    public void ThreadTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }


}
