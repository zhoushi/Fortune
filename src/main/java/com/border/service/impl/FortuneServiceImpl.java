package com.border.service.impl;

import com.border.db.FortuneDB;
import com.border.service.FortuneService;

import java.util.Random;

/**
 * Created by zhou on 2017/12/23.
 */
public class FortuneServiceImpl implements FortuneService {

    private FortuneDB fortuneDB;

    public FortuneServiceImpl(FortuneDB fortuneDB){
        this.fortuneDB = fortuneDB;
    }

    @Override
    public Long create(String message) {
        if(fortuneDB.getFortuneMap().containsValue(message)){
            return -1L;
        }
        return fortuneDB.createFortune(message);
    }

    @Override
    public String find() {
        Random random = new Random();
        int index = random.nextInt(fortuneDB.getFortuneSize().size());
        return fortuneDB.findFortune(index);
    }

    @Override
    public boolean delete(long fortuneId) {
        if(fortuneId < 0 || fortuneId > fortuneDB.getFortuneSize().size()){
            return false;
        }
        return fortuneDB.delete(fortuneId);
    }
}
