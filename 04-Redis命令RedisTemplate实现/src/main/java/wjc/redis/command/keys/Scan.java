package wjc.redis.command.keys;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import wjc.redis.Command;

import java.util.Set;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class Scan extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(Scan.class);

    @Test
    public void testTemplate() {

    }

    @Test
    public void testConnection() {
        template.opsForValue().set("mykey", "Hello");
        template.opsForValue().set("myotherkey", "World");
        Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().build());

        Set<String> set = Sets.newHashSet("mykey", "myotherkey");
        String key;
        while (cursor.hasNext()) {
            key = keySerializer.deserialize(cursor.next());
            System.out.println("cursorId: " + cursor.getCursorId() + ", position: " + cursor.getPosition() + ", key: " + key);

            Assert.assertTrue(set.contains(key));
            set.remove(key);
        }

        Assert.assertTrue(set.isEmpty());
    }
}
