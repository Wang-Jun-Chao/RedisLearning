package wjc.redis.command.lists;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class BRPop extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(BRPop.class);

    @Test
    @Override
    public void testTemplate() {
        template.delete(Lists.newArrayList("job", "command", "request"));
        template.opsForList().leftPush("command", "update system...");
        template.opsForList().leftPush("request", "visit page");

        Assert.fail("RedisTemplate dose not have brpop command");
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForList().rightPush("course", "algorithm001");
        template.opsForList().rightPush("course", "c++101");

        List<byte[]> values = connection.bRPop(3, keySerializer.serialize("course"));
        List<String> stringValue = Lists.newArrayList();
        values.forEach(item -> stringValue.add(valueSerializer.deserialize(item)));

        System.out.println(stringValue);

        List<String> set = Lists.newArrayList("course", "c++101");

        Assert.assertTrue(set.containsAll(stringValue) && stringValue.containsAll(set));
    }
}
