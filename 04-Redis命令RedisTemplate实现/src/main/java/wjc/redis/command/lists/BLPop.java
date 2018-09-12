package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.util.CollectionUtils;
import wjc.redis.Command;
import wjc.redis.util.RedisUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class BLPop extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(BLPop.class);

    @Test
    @Override
    public void testTemplate() {
        template.delete(Lists.newArrayList("job", "command", "request"));
        template.opsForList().leftPush("command", "update system...");
        template.opsForList().leftPush("request", "visit page");

        Assert.fail("RedisTemplate dose not have blpop command");
    }

    @Test
    @Override
    public void testConnection() {
        template.delete(Lists.newArrayList("job", "command", "request"));
        template.opsForList().leftPush("command", "update system...");
        template.opsForList().leftPush("request", "visit page");

        List<byte[]> values = connection.bLPop(0, keySerializer.serialize("job"),
                keySerializer.serialize("command"),
                keySerializer.serialize("request"));
        List<String> stringValue = Lists.newArrayList();
        values.forEach(item -> stringValue.add(valueSerializer.deserialize(item)));

        System.out.println(stringValue);

        List<String> set = Lists.newArrayList("command", "update system...");

        Assert.assertTrue(set.containsAll(stringValue) && stringValue.containsAll(set));
    }

    @Test
    public void testConnectionBlock() {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                template.opsForList().leftPush("job", "do my home work");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        List<byte[]> values = connection.bLPop(5,
                keySerializer.serialize("job"),
                keySerializer.serialize("command"));
        List<String> stringValue = Lists.newArrayList();
        values.forEach(item -> stringValue.add(valueSerializer.deserialize(item)));
        System.out.println(stringValue);
        List<String> set = Lists.newArrayList("job", "do my home work");
        Assert.assertTrue(set.containsAll(stringValue) && stringValue.containsAll(set));

        values = connection.bLPop(3,
                keySerializer.serialize("job"),
                keySerializer.serialize("command"));
        System.out.println(values);
        Assert.assertTrue(CollectionUtils.isEmpty(values));
    }

    @Test
    public void testConnectionMulti() {

        template = template(new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer(String.class));
        connection = connection(template);
        keySerializer = keySerializer(template);
        valueSerializer = valueSerializer(template);

        connection.rPush(keySerializer.serialize("job"),
                valueSerializer.serialize("programming"));

        connection.multi();
        List<byte[]> values = connection.bLPop(5, keySerializer.serialize("job"));
        List<Object> list = connection.exec();
        String s = RedisUtils.toString(list, keySerializer);
        System.out.println(s);
        System.out.println(values);
    }

    @Test
    public void testConnectionMulti2() {

        template = template(new GenericToStringSerializer<>(String.class),
                new GenericToStringSerializer(String.class));
        connection = connection(template);
        keySerializer = keySerializer(template);
        valueSerializer = valueSerializer(template);

        connection.multi();
        List<byte[]> values = connection.bLPop(5, keySerializer.serialize("job"));
        List<Object> list = connection.exec();
        String s = RedisUtils.toString(list, keySerializer);
        System.out.println(s);
        System.out.println(values);
    }
}
