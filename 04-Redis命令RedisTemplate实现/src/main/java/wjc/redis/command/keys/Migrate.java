package wjc.redis.command.keys;

import org.junit.Test;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.serializer.RedisSerializer;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-07 07:13
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Migrate extends Command<String, String> {

    @Test
    public void test() {

        template.opsForValue().set("mykey", "Hello World");
        RedisSerializer<String> keySerializer = (RedisSerializer<String>) template.getKeySerializer();
        byte[] value = template.getConnectionFactory().getConnection().get(
                keySerializer.serialize("mykey"));
        System.out.println(value);


        // timeout 使用其他机器
        RedisNode redisNode = new RedisNode("localhost", 6379);
        template.getConnectionFactory().getConnection().migrate(keySerializer.serialize("mykey"),
                redisNode, 0, RedisServerCommands.MigrateOption.COPY);

        template.getConnectionFactory().getConnection().migrate(keySerializer.serialize("mykey"),
                redisNode, 0, RedisServerCommands.MigrateOption.COPY, 5000);
    }
}
