package wjc.redis.command.zsets;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ZSetOperations;
import wjc.redis.Command;

import java.util.Map;
import java.util.Set;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class ZRem extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRem.class);

    @Test
    @Override
    public void testTemplate() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Long rank = template.opsForZSet().remove("myzset", "two");
        System.out.println(rank);
        Assert.assertEquals(Long.valueOf(1), rank);

        Set<ZSetOperations.TypedTuple<String>> tuples = template.opsForZSet().rangeWithScores(
                "myzset", 0, -1);
        tuples.forEach(item->System.out.println(item.getValue() + ": " + item.getScore()));
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Long rank = connection.zRem(keySerializer.serialize("myzset"),
                valueSerializer.serialize("two"));
        System.out.println(rank);
        Assert.assertEquals(Long.valueOf(1), rank);

        Set<ZSetOperations.TypedTuple<String>> tuples = template.opsForZSet().rangeWithScores(
                "myzset", 0, -1);
        tuples.forEach(item->System.out.println(item.getValue() + ": " + item.getScore()));
    }

}
