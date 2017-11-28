package wjc.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.codec.KryoCodec;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 20:21
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RMapDemo {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);


        System.out.println("Ridis中Key的数目：" + redisson.getKeys().count());

        redisson.getKeys().delete("mymap");

        RMap map = redisson.<String, String>getMap("mymap");
        System.out.println(map.getName()); // = mymap
        System.out.println(mapper.writeValueAsString(map));


        map.put("name", "jack");

        System.out.println(mapper.writeValueAsString(redisson.getMap("mymap")));

        // 清空Redis中的内容
        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
