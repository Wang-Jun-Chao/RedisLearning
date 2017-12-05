package wjc.redis.schedulerservice;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.RInject;

/**
 * Author: 王俊超
 * Date: 2017-12-05 21-54
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RunnableTask implements Runnable {

    @RInject
    RedissonClient redisson;

    @Override
    public void run() {
        System.out.println("call run()");
        RMap<String, String> map = redisson.getMap("myMap");
        map.put("5", "11");
    }
}