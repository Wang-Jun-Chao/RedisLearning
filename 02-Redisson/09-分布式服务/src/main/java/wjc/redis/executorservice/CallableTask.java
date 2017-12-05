package wjc.redis.executorservice;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.api.annotation.RInject;

import java.util.concurrent.Callable;

/**
 * Author: 王俊超
 * Date: 2017-12-05 21-35
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class CallableTask implements Callable<String> {

    @RInject
    RedissonClient redisson;

    @Override
    public String call() throws Exception {
        System.out.println("call call()");
        RMap<String, String> map = redisson.getMap("myMap");
        map.put("1", "2");
        return map.get("3");
    }

}