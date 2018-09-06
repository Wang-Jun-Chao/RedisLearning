package wjc.redis.batch;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.util.*;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-06 06:26
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Sample {
    public static void main(String[] args) {


        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.afterPropertiesSet();

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.afterPropertiesSet();

        Map<String, String> map = new HashMap<>();
        map.put("k1", UUID.randomUUID().toString());
        map.put("k2", UUID.randomUUID().toString());
        map.put("k3", UUID.randomUUID().toString());
        map.put("k4", UUID.randomUUID().toString());


        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new JdkSerializationRedisSerializer());

        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());

        template.afterPropertiesSet();
        template.boundHashOps("name").putAll(map);

        List<Object> values = template.boundHashOps("name").multiGet(new HashSet<>(map.keySet()));
        System.out.println(template.boundHashOps("name").size());
        System.out.println(template.boundHashOps("name").keys());
        System.out.println(template.boundHashOps("name").values());
        System.out.println("values: " + values);

    }
}
