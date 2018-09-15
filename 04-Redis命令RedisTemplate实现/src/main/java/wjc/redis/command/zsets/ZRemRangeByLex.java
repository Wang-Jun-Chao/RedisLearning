package wjc.redis.command.zsets;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wjc.redis.Command;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-12 13:50
 **/
public class ZRemRangeByLex extends Command<String, String> {
    private final static Logger logger = LoggerFactory.getLogger(ZRemRangeByLex.class);

    @Test
    @Override
    public void testTemplate() {
        Assert.fail("RedisTemplate dose not have zremrangebylex command");
    }

    @Test
    @Override
    public void testConnection() {
        Assert.fail("RedisConnection dose not have zremrangebylex command");
    }

}
