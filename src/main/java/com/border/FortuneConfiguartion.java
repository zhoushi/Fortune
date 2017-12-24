package com.border;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhou on 2017/12/23.
 */
public class FortuneConfiguartion extends Configuration {

    @NotNull
    private Map<Long,String> mapConfiguration  = new ConcurrentHashMap<>();

    @NotNull
    private List<Long> listConfiguration = Collections.synchronizedList(new ArrayList<>());

    @JsonProperty("mapConfiguration")
    public Map<Long, String> getMapConfiguration() {
        return mapConfiguration;
    }
    @JsonProperty("mapConfiguration")
    public void setMapConfiguration(Map<Long, String> mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    @JsonProperty("listConfiguration")
    public List<Long> getListConfiguration() {
        return listConfiguration;
    }

    @JsonProperty("listConfiguration")
    public void setListConfiguration(Map<Long,String> mapConfiguration) {
        final List<Long> listSize = Collections.synchronizedList(new ArrayList<>());
        for (Long key:mapConfiguration.keySet()){
            listSize.add(key);
        }
        this.listConfiguration = listSize;
    }
}
