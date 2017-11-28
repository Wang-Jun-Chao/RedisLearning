package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RPatternTopic;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:41
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RPatternTopicDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        // 订阅所有满足`topic1.*`表达式的话题
        RPatternTopic<Message> topic1 = redisson.getPatternTopic("topic1.*");
        int listenerId = topic1.addListener(new PatternMessageListener<Message>() {
            @Override
            public void onMessage(String pattern, String channel, Message msg) {
                System.out.println(pattern);
                System.out.println(channel);
                System.out.println(msg);
            }
        });

        System.out.println(listenerId);



        // 在其他线程或JVM节点
        RTopic<Message> topic2 = redisson.getTopic("topic1.a");
        long clientsReceivedMessage = topic2.publish(new Message("Hello World, from a"));
        System.out.println(clientsReceivedMessage);

        RTopic<Message> topic3 = redisson.getTopic("topic1.b");
        long clientsReceivedMessage3 = topic3.publish(new Message("Hello World, from b"));
        System.out.println(clientsReceivedMessage3);

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
