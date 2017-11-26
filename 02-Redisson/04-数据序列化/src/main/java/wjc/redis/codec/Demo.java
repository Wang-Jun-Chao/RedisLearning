package wjc.redis.codec;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.AvroJacksonCodec;
import org.redisson.codec.KryoCodec;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-26 22:44
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Demo {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec( new KryoCodec());
        RedissonClient client = Redisson.create(config);



    }
}
