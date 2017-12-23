package com.borderXLab.db;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by zhou on 2017/12/23.
 */
public class FortuneDB {

    private final Map<Long,String> fortuneMap;
    private final List<Long> fortuneSize;
    private final AtomicLong count ;

    public FortuneDB(Map<Long,String> fortunes,List<Long> fortuneSize,long count){
        this.fortuneMap = fortunes;
        this.fortuneSize = fortuneSize;
        this.count = new AtomicLong(count);
    }

    /**
     * 添加
     */
    public synchronized long createFortune(String message){
        fortuneMap.put(count.incrementAndGet(), message);
        fortuneSize.add(count.longValue());
        return count.longValue();
    }

    /**
     * 查询
     */
    public synchronized String findFortune(int index){
        if (index >=fortuneSize.size()||fortuneSize.isEmpty()){
            return null;
        }
        return fortuneMap.get(fortuneSize.get(index));
    }

    /**
     * 删除
     */
    public synchronized boolean delete(long fortuneId){
        fortuneMap.remove(fortuneId);
        fortuneSize.remove(fortuneId);
        return true;
    }

    /**
     *
     */
    public List<Long> getFortuneSize() {
        return fortuneSize;
    }

    public Map<Long, String> getFortuneMap() {
        return fortuneMap;
    }
}
