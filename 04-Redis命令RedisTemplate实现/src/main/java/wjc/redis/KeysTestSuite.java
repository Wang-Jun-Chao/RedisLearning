package wjc.redis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import wjc.redis.command.keys.Del;
import wjc.redis.command.keys.Dump;
import wjc.redis.command.keys.Exists;
import wjc.redis.command.keys.Expire;
import wjc.redis.command.keys.ExpireAt;
import wjc.redis.command.keys.Keys;
import wjc.redis.command.keys.Migrate;
import wjc.redis.command.keys.Move;
import wjc.redis.command.keys.Object;
import wjc.redis.command.keys.PExpire;
import wjc.redis.command.keys.PExpireAt;
import wjc.redis.command.keys.PTtl;
import wjc.redis.command.keys.Persist;
import wjc.redis.command.keys.RandomKey;
import wjc.redis.command.keys.Rename;
import wjc.redis.command.keys.RenameNx;
import wjc.redis.command.keys.Restore;
import wjc.redis.command.keys.Scan;
import wjc.redis.command.keys.Sort;
import wjc.redis.command.keys.Ttl;
import wjc.redis.command.keys.Type;
import wjc.redis.command.keys.Wait;

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
        Exists.class,
        Expire.class,
        ExpireAt.class,
        Keys.class,
        Migrate.class,
        Move.class,
        wjc.redis.command.keys.Object.class,
        Persist.class,
        PExpire.class,
        PExpireAt.class,
        PTtl.class,
        RandomKey.class,
        Rename.class,
        RenameNx.class,
        Restore.class,
        Scan.class,
        Sort.class,
        Ttl.class,
        Type.class,
        Wait.class,
})
@RunWith(value = Suite.class)
public class KeysTestSuite {
}
