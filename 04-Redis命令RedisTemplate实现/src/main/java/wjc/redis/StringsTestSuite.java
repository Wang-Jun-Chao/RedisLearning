package wjc.redis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import wjc.redis.command.strings.Append;
import wjc.redis.command.strings.BitCount;
import wjc.redis.command.strings.BitField;
import wjc.redis.command.strings.BitOp;
import wjc.redis.command.strings.BitPos;
import wjc.redis.command.strings.Decr;
import wjc.redis.command.strings.DecrBy;
import wjc.redis.command.strings.Get;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-11 08:56
 **/
@Suite.SuiteClasses(value = {
        Append.class,
        BitCount.class,
        BitField.class,
        BitOp.class,
        BitPos.class,
        Decr.class,
        DecrBy.class,
        Get.class
})
@RunWith(value = Suite.class)
public class StringsTestSuite {
}
