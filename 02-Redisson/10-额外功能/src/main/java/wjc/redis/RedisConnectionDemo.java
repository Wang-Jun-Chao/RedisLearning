package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RFuture;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisClientConfig;
import org.redisson.client.RedisConnection;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;

import java.util.concurrent.Future;

/**
 * Author: 王俊超
 * Date: 2017-12-06 20-33
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RedisConnectionDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RedisClientConfig config = new RedisClientConfig();
        config.setAddress("localhost", 6379);
        RedisClient client = RedisClient.create(config);
        RedisConnection conn = client.connect();
        // 或
        Future<RedisConnection> connFuture = client.connectAsync();

        Object test = conn.sync(StringCodec.INSTANCE, RedisCommands.SET, "test", 0);
        System.out.println(test);

        RFuture<Object> result = conn.async(StringCodec.INSTANCE, RedisCommands.GET, "test");
        System.out.println(result.get());

        test = conn.sync(RedisCommands.PING);
        System.out.println(test);

        conn.closeAsync();

        client.shutdown();
//        // 或
//        client.shutdownAsync();

    }
}
