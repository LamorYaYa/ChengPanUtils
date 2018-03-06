package com.example;

/**
 * @author master
 * @date 2018/2/9
 */

public interface EventInterface<T> {

    /**
     * 生产
     */
    void production();

    /**
     * 消费
     */
    T consumption();


}
