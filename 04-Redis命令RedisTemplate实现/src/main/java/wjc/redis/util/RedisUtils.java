package wjc.redis.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 15:05
 **/
public class RedisUtils {
    private final static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    public static String toString(Object object, RedisSerializer<String> serializer) {

        if (object == null) {
            return null;
        }

        if (Collection.class.isAssignableFrom(object.getClass())) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            Collection coll = (Collection) object;
            for (Object o : coll) {
                builder.append(toString(o, serializer)).append(", ");
            }

            if (builder.length() > 1) {
                builder.replace(builder.length() - 2, builder.length(), "");
            }

            builder.append("]");

            return builder.toString();
        }

        if (object instanceof byte[]) {
            return serializer.deserialize((byte[]) object);
        }

        if (object.getClass().isArray()) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            int length = Array.getLength(object);
            for (int i = 0; i < length; i++) {
                builder.append(toString(Array.get(object, i), serializer)).append(", ");
            }
            if (builder.length() > 1) {
                builder.replace(builder.length() - 2, builder.length(), "");
            }

            builder.append("]");
            return builder.toString();
        }

        if (Map.class.isAssignableFrom(object.getClass())) {
            Map map = (Map) object;
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            for (Object o : map.entrySet()) {
                builder.append(toString(((Map.Entry) o).getKey(), serializer))
                        .append(": ").append(toString(((Map.Entry) o).getValue(), serializer)).append(", ");
            }

            if (builder.length() > 1) {
                builder.replace(builder.length() - 2, builder.length(), "");
            }

            builder.append("}");
            return builder.toString();
        }

        return object.toString();
    }

    public static List<String> toStringList(List<byte[]> list, RedisSerializer<String> serializer) {
        List<String> result = Lists.newArrayList();
        Optional.of(list).ifPresent(item -> item.forEach(i -> result.add(serializer.deserialize(i))));
        return result;
    }
}
