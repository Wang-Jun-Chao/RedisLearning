package wjc.redis.command;

import org.junit.Test;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisServerCommands;
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
public class Migrate extends Command<byte[], String> {

    @Test
    public void test() {

        template.opsForValue().set("mykey".getBytes(), "Hello World");

        RedisNode redisNode = new RedisNode("localhost", 6379);
        template.getConnectionFactory().getConnection().migrate("mykey".getBytes(),
                redisNode, 0, RedisServerCommands.MigrateOption.COPY);

        template.getConnectionFactory().getConnection().migrate("mykey".getBytes(),
                redisNode, 0, RedisServerCommands.MigrateOption.COPY, 5000);
    }
}
