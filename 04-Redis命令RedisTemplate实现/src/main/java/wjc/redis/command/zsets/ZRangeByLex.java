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
public class ZRangeByLex extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRangeByLex.class);

    @Test
    @Override
    public void testTemplate() {

        Map<String, Double> map = Maps.newHashMap();

        map.put("a", 0D);
        map.put("aa", 0D);
        map.put("abc", 0D);
        map.put("apple", 0D);
        map.put("b", 0D);
        map.put("c", 0D);
        map.put("d", 0D);
        map.put("d1", 0D);
        map.put("dd", 0D);
        map.put("dobble", 0D);
        map.put("z", 0D);
        map.put("z1", 0D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Set<String> range = template.opsForZSet().rangeByLex("myzset",
                RedisZSetCommands.Range.unbounded());
        System.out.println(range);
        Assert.assertTrue(range.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(range));

        range = template.opsForZSet().rangeByLex("myzset", RedisZSetCommands.Range.unbounded(),
                RedisZSetCommands.Limit.limit().offset(0).count(3));
        System.out.println(range);
        Set<String> expected = Sets.newHashSet("a", "aa", "abc");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));

        range = template.opsForZSet().rangeByLex("myzset", RedisZSetCommands.Range.unbounded(),
                RedisZSetCommands.Limit.limit().offset(3).count(3));
        System.out.println(range);
        expected = Sets.newHashSet("apple", "b", "c");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));

        range = template.opsForZSet().rangeByLex("myzset",
                RedisZSetCommands.Range.range()
                        .gte(keySerializer.serialize("aa"))
                        .lte(keySerializer.serialize("c")));
        System.out.println(range);

        range = template.opsForZSet().rangeByLex("myzset",
                RedisZSetCommands.Range.range()
                        .gte(keySerializer.serialize("aa"))
                        .lt(keySerializer.serialize("c")));
        System.out.println(range);
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();

        map.put("a", 0D);
        map.put("aa", 0D);
        map.put("abc", 0D);
        map.put("apple", 0D);
        map.put("b", 0D);
        map.put("c", 0D);
        map.put("d", 0D);
        map.put("d1", 0D);
        map.put("dd", 0D);
        map.put("dobble", 0D);
        map.put("z", 0D);
        map.put("z1", 0D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        final Set<String> range = Sets.newHashSet();
        connection.zRangeByLex(keySerializer.serialize("myzset"), RedisZSetCommands.Range.unbounded())
                .forEach(item -> range.add(valueSerializer.deserialize(item)));

        System.out.println(range);
        Assert.assertTrue(range.containsAll(map.keySet()));
        Assert.assertTrue(map.keySet().containsAll(range));

        range.clear();
        connection.zRangeByLex(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.unbounded(),
                RedisZSetCommands.Limit.limit().offset(0).count(3)).forEach(
                        item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        Set<String> expected = Sets.newHashSet("a", "aa", "abc");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));

        range.clear();
        connection.zRangeByLex(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.unbounded(),
                RedisZSetCommands.Limit.limit().offset(3).count(3)).forEach(
                item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
        expected = Sets.newHashSet("apple", "b", "c");
        Assert.assertTrue(expected.containsAll(range));
        Assert.assertTrue(range.containsAll(expected));

        range.clear();
        connection.zRangeByLex(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range()
                        .gte(keySerializer.serialize("aa"))
                        .lte(keySerializer.serialize("c"))).forEach(
                item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);

        range.clear();
        connection.zRangeByLex(keySerializer.serialize("myzset"),
                RedisZSetCommands.Range.range()
                        .gte(keySerializer.serialize("aa"))
                        .lt(keySerializer.serialize("c"))).forEach(
                item -> range.add(valueSerializer.deserialize(item)));
        System.out.println(range);
    }

}
