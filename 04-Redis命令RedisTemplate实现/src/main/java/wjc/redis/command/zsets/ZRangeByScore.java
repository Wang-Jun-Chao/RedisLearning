package wjc.redis.command.zsets;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisZSetCommands;
import wjc.redis.Command;

import java.util.Map;
import java.util.Set;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class ZRangeByScore extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRangeByScore.class);

    @Test
    @Override
    public void testTemplate() {

        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Set<String> range = template.opsForZSet().rangeByScore("myzset",
                Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.println(range);
        Assert.assertTrue(range.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(range));

        range = template.opsForZSet().rangeByScore("myzset", 1, 2);
        System.out.println(range);
        Set<String> expected = Sets.newHashSet("one", "two");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        final Set<String> range = Sets.newHashSet();
        connection.zRangeByScore(keySerializer.serialize("myzset"), RedisZSetCommands.Range.unbounded())
                .forEach(item -> range.add(valueSerializer.deserialize(item)));

        System.out.println(range);
        Assert.assertTrue(range.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(range));

        range.clear();
        connection.zRangeByScore(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gte(1).lte(2)).forEach(
                item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        Set<String> expected = Sets.newHashSet("one", "two");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));

        range.clear();
        connection.zRangeByScore(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(1).lte(2)).forEach(
                item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        expected = Sets.newHashSet("two");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));

        range.clear();
        connection.zRangeByScore(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(1).lt(2)).forEach(
                item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
    }

}
