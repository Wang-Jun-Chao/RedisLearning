package wjc.redis.command.keys;

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
public class Migrate extends Command<String, String> {

    @Override
    public void testTemplate() {

    }

    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello World");
        byte[] value = connection.get(keySerializer.serialize("mykey"));
        System.out.println(value);


        // timeout 使用其他机器
        RedisNode redisNode = new RedisNode("localhost", 6379);
        connection.migrate(keySerializer.serialize("mykey"), redisNode, 0,
                RedisServerCommands.MigrateOption.COPY);

        connection.migrate(keySerializer.serialize("mykey"), redisNode, 0,
                RedisServerCommands.MigrateOption.COPY, 5000);
    }
}
