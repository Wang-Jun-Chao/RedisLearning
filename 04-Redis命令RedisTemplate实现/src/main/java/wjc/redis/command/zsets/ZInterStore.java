package wjc.redis.command.zsets;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.ZSetOperations;
import wjc.redis.Command;

import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 22:51
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ZInterStore extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForZSet().add("zset1", "one", 1);
        template.opsForZSet().add("zset1", "two", 2);
        template.opsForZSet().add("zset2", "one", 1);
        template.opsForZSet().add("zset2", "two", 2);
        template.opsForZSet().add("zset2", "three", 3);


        Long inter = template.opsForZSet().intersectAndStore("zset1", "zset2", "out");
        System.out.println(inter);
        Assert.assertEquals(Long.valueOf(2), inter);

        Set<ZSetOperations.TypedTuple<String>> myzset = template.opsForZSet().rangeWithScores(
                "out", 0, -1);
        myzset.forEach(item -> System.out.println(item.getValue() + ":" + item.getScore()));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForZSet().add("zset1", "one", 1);
        template.opsForZSet().add("zset1", "two", 2);
        template.opsForZSet().add("zset2", "one", 1);
        template.opsForZSet().add("zset2", "two", 2);
        template.opsForZSet().add("zset2", "three", 3);


        Long inter = connection.zInterStore(
                keySerializer.serialize("out"),
                keySerializer.serialize("zset1"),
                keySerializer.serialize("zset2"));
        System.out.println(inter);
        Assert.assertEquals(Long.valueOf(2), inter);

        Set<ZSetOperations.TypedTuple<String>> myzset = template.opsForZSet().rangeWithScores(
                "out", 0, -1);
        myzset.forEach(item -> System.out.println(item.getValue() + ":" + item.getScore()));
    }

    @Test
    public void testConnection2() {
        template.opsForZSet().add("zset1", "one", 1);
        template.opsForZSet().add("zset1", "two", 2);
        template.opsForZSet().add("zset2", "one", 1);
        template.opsForZSet().add("zset2", "two", 2);
        template.opsForZSet().add("zset2", "three", 3);


        Long inter = connection.zInterStore(
                keySerializer.serialize("out"),
                RedisZSetCommands.Aggregate.SUM,
                new int[]{2, 3},
                keySerializer.serialize("zset1"),
                keySerializer.serialize("zset2"));
        System.out.println(inter);
        Assert.assertEquals(Long.valueOf(2), inter);

        Set<ZSetOperations.TypedTuple<String>> myzset = template.opsForZSet().rangeWithScores(
                "out", 0, -1);
        myzset.forEach(item -> System.out.println(item.getValue() + ":" + item.getScore()));
    }
}
