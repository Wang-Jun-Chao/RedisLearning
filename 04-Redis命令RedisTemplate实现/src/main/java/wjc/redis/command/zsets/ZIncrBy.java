package wjc.redis.command.zsets;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.ZSetOperations;
import wjc.redis.Command;

import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-14 06:50
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ZIncrBy extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);

        Double score = template.opsForZSet().incrementScore("myzset", "one", 2);
        System.out.println(score);
        Assert.assertEquals(Double.valueOf(3), score);

        Set<ZSetOperations.TypedTuple<String>> myzset = template.opsForZSet().rangeWithScores(
                "myzset", 0, -1);
        myzset.forEach(item -> System.out.println(item.getValue() + ":" + item.getScore()));

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "two", 2);

        Double score = connection.zIncrBy(keySerializer.serialize("myzset"),
                2, valueSerializer.serialize("one"));
        System.out.println(score);
        Assert.assertEquals(Double.valueOf(3), score);

        Set<ZSetOperations.TypedTuple<String>> myzset = template.opsForZSet().rangeWithScores(
                "myzset", 0, -1);
        myzset.forEach(item -> System.out.println(item.getValue() + ":" + item.getScore()));
    }
}
