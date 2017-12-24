package com.border.service;

/**
 * Created by zhou on 2017/12/23.
 */
public interface FortuneService {

    Long create(String message);

    String find();

    boolean delete(long fortuneId);
}
