package wjc.redis;

import org.junit.Before;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 21:37
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Command<K, V> {
    protected RedisTemplate<K, V> template;
    protected RedisConnection connection;
    protected RedisSerializer<K> keySerializer;

    public Command() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.afterPropertiesSet();

        template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        keySerializer = (RedisSerializer<K>) template.getKeySerializer();
        connection = template.getConnectionFactory().getConnection();
    }

    @Before
    public void setUp() {
        template.getConnectionFactory().getConnection().flushAll();
    }
}
