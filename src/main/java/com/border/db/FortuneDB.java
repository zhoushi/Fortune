package com.border.db;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by zhou on 2017/12/23.
 */
public class FortuneDB {

    private Lock lock = new ReentrantLock();

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
    public  long createFortune(String message){
        lock.lock();

        try {
            fortuneMap.put(count.incrementAndGet(), message);
            fortuneSize.add(count.longValue());
        }finally {
            lock.unlock();
        }

        return count.longValue();
    }

    /**
     * 查询
     */
    public String findFortune(int index){
        if (index >=fortuneSize.size()||fortuneSize.isEmpty()){
            return null;
        }
        return fortuneMap.get(fortuneSize.get(index));
    }

    /**
     * 删除
     */
    public  boolean delete(long fortuneId){
        lock.lock();
        try {
            fortuneMap.remove(fortuneId);
            fortuneSize.remove(fortuneId);
        }finally {
            lock.unlock();
        }

        return true;
    }


    public List<Long> getFortuneSize() {
        return fortuneSize;
    }

    public Map<Long, String> getFortuneMap() {
        return fortuneMap;
    }
}
