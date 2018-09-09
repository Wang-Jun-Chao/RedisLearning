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
public abstract class Command<K, V> {
    protected RedisTemplate<K, V> template;
    protected RedisConnection connection;
    protected RedisSerializer<K> keySerializer;
    protected RedisSerializer<K> valueSerializer;

    public Command() {
        template = template();
        keySerializer = keySerializer(template);
        connection = connection(template);
        valueSerializer = valueSerializer(template);
    }

    private static <K, V> void setSerializer(RedisTemplate<K, V> template) {
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    }

    public static <K, V> RedisTemplate<K, V> template() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.afterPropertiesSet();
        RedisTemplate<K, V> template = new RedisTemplate<>();
        setSerializer(template);
        template.setConnectionFactory(factory);

        template.afterPropertiesSet();
        return template;
    }

    public static RedisConnection connection(RedisTemplate template) {
        return template.getConnectionFactory().getConnection();
    }

    public static RedisSerializer keySerializer(RedisTemplate template) {
        return template.getKeySerializer();
    }

    public static RedisSerializer valueSerializer(RedisTemplate template) {
        return template.getKeySerializer();
    }

    @Before
    public void setUp() {
        connection.flushAll();
    }

    public void testTemplate() throws InterruptedException {
    }

    public void testConnection() {
    }
}
