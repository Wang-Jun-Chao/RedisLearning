package wjc.redis.command.zsets;

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
 * Date: 2018-09-14 06:50
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class ZAdd extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "uno", 1);
        template.opsForZSet().add("myzset", "two", 2);
        template.opsForZSet().add("myzset", "three", 3);

        Set<ZSetOperations.TypedTuple<String>> myzset = template.opsForZSet().rangeWithScores(
                "myzset", 0, -1);
        myzset.forEach(item -> System.out.println(item.getValue() + ":" + item.getScore()));

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForZSet().add("myzset", "one", 1);
        template.opsForZSet().add("myzset", "uno", 1);
        template.opsForZSet().add("myzset", "two", 2);
        template.opsForZSet().add("myzset", "three", 3);

        Set<RedisZSetCommands.Tuple> myzset = connection.zRangeWithScores(
                keySerializer.serialize("myzset"), 0, -1);
        myzset.forEach(item -> System.out.println(valueSerializer.deserialize(item.getValue()) + ":" + item.getScore()));
    }
}
