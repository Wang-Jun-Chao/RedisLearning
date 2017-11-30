package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Author: 王俊超
 * Date: 2017-11-30 21:29
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RMapDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);


        redisson.getKeys().flushall();


        RMap<String, SomeObject> map = redisson.getMap("anyMap");
        SomeObject prevObject = map.put("123", new SomeObject());
        System.out.println("prevObject: " + prevObject);
        System.out.println("currentObject: " + map.get("123"));

        // 不存在就放入
        SomeObject currentObject = map.putIfAbsent("323", new SomeObject());
        System.out.println("currentObject: " + currentObject);

        // 删除
        SomeObject obj = map.remove("123");
        System.out.println("obj: " + obj);

        map.fastPut("321", new SomeObject());
        map.fastRemove("321");
        System.out.println(mapper.writeValueAsString(map));

        RFuture<SomeObject> putAsyncFuture = map.putAsync("321", new SomeObject());
        System.out.println(putAsyncFuture.get());

        // key不存在才会被放入进去
        RFuture<Boolean> fastPutAsyncFuture = map.fastPutAsync("321", new SomeObject());
        if (fastPutAsyncFuture.get()) {
            System.out.println("fastPutAsyncFuture - true : " + map.get("321"));
        } else {
            System.out.println(mapper.writeValueAsString(map));
            System.out.println("fastPutAsyncFuture - false : " + map.get("321"));
        }

        map.fastPutAsync("321", new SomeObject());
        map.fastRemoveAsync("321");
        System.out.println(mapper.writeValueAsString(map));

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
