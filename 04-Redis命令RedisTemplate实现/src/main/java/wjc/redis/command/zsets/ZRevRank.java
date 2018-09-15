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
 * @time: 2018-09-12 13:50
 **/
public class ZRevRank extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRevRank.class);

    @Test
    @Override
    public void testTemplate() {

        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Long rank = template.opsForZSet().reverseRank("myzset", "one");
        System.out.println(rank);
        Assert.assertEquals(Long.valueOf(2), rank);

        rank = template.opsForZSet().rank("myzset", "four");
        System.out.println(rank);
        Assert.assertNull(rank);
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Long rank = connection.zRevRank(keySerializer.serialize("myzset"),
                valueSerializer.serialize("one"));
        System.out.println(rank);
        Assert.assertEquals(Long.valueOf(2), rank);

        rank = connection.zRank(keySerializer.serialize("myzset"),
                valueSerializer.serialize("four"));
        System.out.println(rank);
        Assert.assertNull(rank);
    }

}
