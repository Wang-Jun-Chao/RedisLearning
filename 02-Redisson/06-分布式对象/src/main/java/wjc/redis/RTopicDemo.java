package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:34
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RTopicDemo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RTopic<SomeObject> topic = redisson.getTopic("anyTopic");
        topic.addListener(new MessageListener<SomeObject>() {
            @Override
            public void onMessage(String channel, SomeObject message) {
                System.out.println(channel);
                System.out.println(message);
            }
        });

        // 在其他线程或JVM节点
        RTopic<SomeObject> topic2 = redisson.getTopic("anyTopic");
        long clientsReceivedMessage = topic2.publish(new SomeObject("Hello World"));

        System.out.println(clientsReceivedMessage);


        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
