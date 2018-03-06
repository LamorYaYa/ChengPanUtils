package com.example;

/**
 * @author master
 * @date 2018/2/9
 */

import java.util.LinkedList;
import java.util.List;

/**
 * 仓库存储
 */
public class EventStorage implements EventInterface<String> {

    /**
     * 任务存储
     */
    private List<String> storage;


    public EventStorage() {
        storage = new LinkedList<>();
    }

    /**
     * 生产
     */
    @Override
    public synchronized void production() {
        storage.add("12345");
        notifyAll();
    }

    /**
     * 消费
     */
    @Override
    public synchronized String consumption() {
        if (storage.size() == 0) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ((LinkedList<String>) storage).poll();

    }
}
