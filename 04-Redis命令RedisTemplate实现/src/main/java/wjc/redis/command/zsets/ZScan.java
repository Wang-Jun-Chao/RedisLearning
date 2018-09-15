package wjc.redis.command.zsets;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import wjc.redis.Command;

import java.util.Map;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class ZScan extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZScan.class);

    @Test
    @Override
    public void testTemplate() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Cursor<ZSetOperations.TypedTuple<String>> cursor = template.opsForZSet().scan(
                "myzset", ScanOptions.scanOptions().build());
        cursor.forEachRemaining(item -> System.out.println(item.getValue() + ": " + item.getScore()));
    }

    @Test
    @Override
    public void testConnection() {
        Map<String, Double> map = Maps.newHashMap();
        map.put("one", 1D);
        map.put("two", 2D);
        map.put("three", 3D);
        map.forEach((k, v) -> template.opsForZSet().add("myzset", k, v));

        Cursor<RedisZSetCommands.Tuple> cursor = connection.zScan(
                keySerializer.serialize("myzset"), ScanOptions.scanOptions().build());
        cursor.forEachRemaining(item -> System.out.println(valueSerializer.deserialize(item.getValue())
                + ": " + item.getScore()));
    }
}
