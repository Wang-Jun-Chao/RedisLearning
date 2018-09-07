package wjc.redis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import wjc.redis.command.keys.Del;
import wjc.redis.command.keys.Dump;
import wjc.redis.command.keys.Sort;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-08 06:32
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
@Suite.SuiteClasses(value = {
        Del.class,
        Dump.class,
        Sort.class
})
@RunWith(value = Suite.class)
public class KeysTestSuite {
}
