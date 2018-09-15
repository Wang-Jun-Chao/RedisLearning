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
public class ZRevRangeByScore extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRevRangeByScore.class);

    @Test
    @Override
    public void testTemplate() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Set<String> myzset = template.opsForZSet().reverseRangeByScore(
                "myzset", Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.println(myzset);
        Assert.assertEquals(3, myzset.size());
        myzset.forEach(System.out::println);

        myzset = template.opsForZSet().reverseRange("myzset", 1, 2);
        System.out.println(myzset);
        Assert.assertEquals(2, myzset.size());
        myzset.forEach(System.out::println);

    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        final Set<String> myzset = Sets.newHashSet();
        connection.zRevRangeByScore(keySerializer.serialize("myzset"),
                Double.MIN_VALUE, Double.MAX_VALUE)
                .forEach(item -> myzset.add(valueSerializer.deserialize(item)));
        System.out.println(myzset);
        Assert.assertEquals(3, myzset.size());
        myzset.forEach(System.out::println);

        myzset.clear();
        connection.zRevRangeByScore(keySerializer.serialize("myzset"), 1, 2)
                .forEach(item -> myzset.add(valueSerializer.deserialize(item)));
        System.out.println(myzset);
        Assert.assertEquals(2, myzset.size());
        myzset.forEach(System.out::println);

        final Map<String, Double> value = Maps.newHashMap();
        connection.zRevRangeByScoreWithScores(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.unbounded())
                .forEach(item -> value.put(valueSerializer.deserialize(item.getValue()), item.getScore()));
        System.out.println(value);
        Assert.assertEquals(3, value.size());
        value.forEach((k, v) -> System.out.println(k + ": " + v));

        value.clear();
        connection.zRevRangeByScoreWithScores(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gte(1).lte(2))
                .forEach(item -> value.put(valueSerializer.deserialize(item.getValue()), item.getScore()));
        System.out.println(value);
        Assert.assertEquals(2, value.size());
        value.forEach((k, v) -> System.out.println(k + ": " + v));

        value.clear();
        connection.zRevRangeByScoreWithScores(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(1).lte(2))
                .forEach(item -> value.put(valueSerializer.deserialize(item.getValue()), item.getScore()));
        System.out.println(value);
        Assert.assertEquals(1, value.size());
        value.forEach((k, v) -> System.out.println(k + ": " + v));

        value.clear();
        connection.zRevRangeByScoreWithScores(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(1).lt(2))
                .forEach(item -> value.put(valueSerializer.deserialize(item.getValue()), item.getScore()));
        System.out.println(value);
        Assert.assertEquals(0, value.size());
        value.forEach((k, v) -> System.out.println(k + ": " + v));

        value.clear();
        connection.zRevRangeByScoreWithScores(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(2),
                RedisZSetCommands.Limit.limit().offset(0).count(1))
                .forEach(item -> value.put(valueSerializer.deserialize(item.getValue()), item.getScore()));
        System.out.println(value);
        Assert.assertEquals(1, value.size());
        value.forEach((k, v) -> System.out.println(k + ": " + v));

        value.clear();
        connection.zRevRangeByScoreWithScores(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range().gt(2),
                RedisZSetCommands.Limit.limit().offset(2).count(3))
                .forEach(item -> value.put(valueSerializer.deserialize(item.getValue()), item.getScore()));
        System.out.println(value);
        Assert.assertEquals(0, value.size());
        value.forEach((k, v) -> System.out.println(k + ": " + v));
    }

}
