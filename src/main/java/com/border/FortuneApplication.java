package com.border;

import com.border.db.FortuneDB;
import com.border.resources.FortuneResource;
import com.border.service.FortuneService;
import com.border.service.impl.FortuneServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhou on 2017/12/23.
 */
public class FortuneApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception{
        new FortuneApplication().run(args);
    }
    private static final Map<Long,String> map = new ConcurrentHashMap<>();
    private static final List<Long> list = Collections.synchronizedList(new ArrayList<>());
    private final FortuneDB fortuneDB = new FortuneDB(map,list,list.size());
    /**
     * 初始化数据
     */
    static {
        map.put(1L,"11111");
        map.put(2L,"22222");
        map.put(3L,"33333");

        for (Long key:map.keySet()){
            list.add(key);
        }
    }


    public void run(Configuration configuration, Environment environment) {
        final FortuneService fortuneService = new FortuneServiceImpl(fortuneDB);
        environment.jersey().register(new FortuneResource(fortuneService));
    }
}
