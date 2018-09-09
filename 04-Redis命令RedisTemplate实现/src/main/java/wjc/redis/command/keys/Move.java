package wjc.redis.command.keys;

import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-07 07:25
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Move extends Command<String, String> {

    @Override
    public void testTemplate() {
        template.opsForValue().set("mykey", "Hello World");
        template.move("mykey", 1);
    }

    @Override
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello World");
        connection.move(keySerializer.serialize("mykey"), 1);
    }
}
