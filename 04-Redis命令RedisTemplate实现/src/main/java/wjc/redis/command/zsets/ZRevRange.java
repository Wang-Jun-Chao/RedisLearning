package wjc.redis.command.zsets;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

import java.util.Map;
import java.util.Set;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class ZRevRange extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRevRange.class);

    @Test
    @Override
    public void testTemplate() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Set<String> myzset = template.opsForZSet().reverseRange("myzset", 0, -1);
        System.out.println(myzset);
        Assert.assertEquals(3, myzset.size());
        myzset.forEach(System.out::println);

        myzset = template.opsForZSet().reverseRange("myzset", 2, 3);
        System.out.println(myzset);
        Assert.assertEquals(1, myzset.size());
        myzset.forEach(System.out::println);

        myzset = template.opsForZSet().reverseRange("myzset", -2, -1);
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
        connection.zRevRange(keySerializer.serialize("myzset"), 0, -1)
                .forEach(item -> myzset.add(valueSerializer.deserialize(item)));
        System.out.println(myzset);
        Assert.assertEquals(3, myzset.size());
        myzset.forEach(System.out::println);

        myzset.clear();
        connection.zRevRange(keySerializer.serialize("myzset"), 2, 3)
                .forEach(item -> myzset.add(valueSerializer.deserialize(item)));
        System.out.println(myzset);
        Assert.assertEquals(1, myzset.size());
        myzset.forEach(System.out::println);

        myzset.clear();
        connection.zRevRange(keySerializer.serialize("myzset"), -2, -1)
                .forEach(item -> myzset.add(valueSerializer.deserialize(item)));
        System.out.println(myzset);
        Assert.assertEquals(2, myzset.size());
        myzset.forEach(System.out::println);
    }

}
