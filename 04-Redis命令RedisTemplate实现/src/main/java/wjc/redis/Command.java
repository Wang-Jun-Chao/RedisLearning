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
    protected RedisSerializer<V> valueSerializer;
    protected RedisSerializer<K> hashKeySerializer;
    protected RedisSerializer<V> hashValueSerializer;

    public Command() {
        this(new GenericJackson2JsonRedisSerializer(), new GenericJackson2JsonRedisSerializer(),
                new GenericJackson2JsonRedisSerializer(), new GenericJackson2JsonRedisSerializer());
    }

    public Command(RedisSerializer keySerializer, RedisSerializer valueSerializer) {
        this(keySerializer, valueSerializer, new GenericJackson2JsonRedisSerializer(),
                new GenericJackson2JsonRedisSerializer());
    }

    public Command(RedisSerializer keySerializer, RedisSerializer valueSerializer,
                   RedisSerializer hashKeySerializer, RedisSerializer hashValueSerializer) {
        template = template(keySerializer, valueSerializer, hashKeySerializer, hashValueSerializer);
        this.connection = connection(template);
        this.keySerializer = keySerializer(template);
        this.valueSerializer = valueSerializer(template);
        this.hashKeySerializer = hashKeySerializer(template);
        this.hashValueSerializer = hashValueSerializer(template);

    }

    public RedisSerializer hashValueSerializer(RedisTemplate<K, V> template) {
        return template.getHashValueSerializer();
    }

    public RedisSerializer hashKeySerializer(RedisTemplate<K, V> template) {
        return template.getHashKeySerializer();
    }

    public static <K, V> RedisTemplate<K, V> template(RedisSerializer<K> keySerializer,
                                                      RedisSerializer<V> valueSerializer) {
        return template(keySerializer, valueSerializer,
                new GenericJackson2JsonRedisSerializer(),
                new GenericJackson2JsonRedisSerializer());
    }

    public static <K, V, HK, HV> RedisTemplate<K, V> template(RedisSerializer<K> keySerializer,
                                                              RedisSerializer<V> valueSerializer,
                                                              RedisSerializer<HK> hashKeySerializer,
                                                              RedisSerializer<HV> hashValueSerializer) {

        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.afterPropertiesSet();

        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.setHashKeySerializer(hashKeySerializer);
        template.setHashValueSerializer(hashValueSerializer);

        template.setConnectionFactory(factory);

        template.afterPropertiesSet();

        return template;
    }

    public static <K, V> RedisTemplate<K, V> template() {
        return (RedisTemplate<K, V>) template(
                new GenericJackson2JsonRedisSerializer(),
                new GenericJackson2JsonRedisSerializer(),
                new GenericJackson2JsonRedisSerializer(),
                new GenericJackson2JsonRedisSerializer());
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

    public abstract void testTemplate();

    public abstract void testConnection();
}
