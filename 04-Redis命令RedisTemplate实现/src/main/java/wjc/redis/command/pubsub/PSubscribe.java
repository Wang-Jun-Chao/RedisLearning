package wjc.redis.command.pubsub;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-14 06:50
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class PSubscribe extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        Assert.fail("RedisTemplate dose not have psubscribe command");
    }

    @Test
    @Override
    public void testConnection() {
        connection.pSubscribe(
                new MessageListener() {
                    @Override
                    public void onMessage(Message message, byte[] pattern) {
                        System.out.println("patten: " + keySerializer.deserialize(pattern));
                        System.out.println("channel: " + valueSerializer.deserialize(message.getChannel()));
                        System.out.println("message: " + valueSerializer.deserialize(message.getBody()));
                    }
                },
                keySerializer.serialize("news.*"),
                keySerializer.serialize("tweet.*"));

    }
}
