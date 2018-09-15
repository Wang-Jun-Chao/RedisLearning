package wjc.redis.command.zsets;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.Map;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class ZScore extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZScore.class);

    @Test
    @Override
    public void testTemplate() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Double score = template.opsForZSet().score("myzset", "one");
        System.out.println(score);
        Assert.assertEquals(Double.valueOf(1), score);
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Double score = connection.zScore(keySerializer.serialize("myzset"),
                valueSerializer.serialize("one"));
        System.out.println(score);
        Assert.assertEquals(Double.valueOf(1), score);
    }
}
