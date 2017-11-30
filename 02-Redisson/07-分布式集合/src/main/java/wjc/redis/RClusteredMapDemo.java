package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-30 22:08
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RClusteredMapDemo {
    public static void main(String[] args) {
        Config config = new Config();
        config.setUseLinuxNativeEpoll(true);
        config.useClusterServers().addNodeAddress("redis://192.168.241.150:7110");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        // 以下功能只在pro版本中有
//        RClusteredMap<String, SomeObject> map = redisson.getClusteredMap("anyMap");
//
//        SomeObject prevObject = map.put("123", new SomeObject());
//        SomeObject currentObject = map.putIfAbsent("323", new SomeObject());
//        SomeObject obj = map.remove("123");
//
//        map.fastPut("321", new SomeObject());
//        map.fastRemove("321");

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
