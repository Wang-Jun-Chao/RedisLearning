package wjc.redis.command.sets;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import wjc.redis.Command;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class SScan extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(SScan.class);

    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "one", "two", "three");
        Cursor<String> scan = template.opsForSet().scan("myset", ScanOptions.scanOptions().build());
        scan.forEachRemaining(System.out::println);
    }

    @Test
    @Override
    public void testConnection() {

        template.opsForSet().add("myset", "one", "two", "three");
        Cursor<byte[]> scan = connection.sScan(keySerializer.serialize("myset"), ScanOptions.scanOptions().build());
        scan.forEachRemaining(item -> System.out.println(valueSerializer.deserialize(item)));
    }
}
