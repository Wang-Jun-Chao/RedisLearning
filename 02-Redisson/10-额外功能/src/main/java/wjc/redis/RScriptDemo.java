package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;

import java.util.Collections;
import java.util.concurrent.Future;

/**
 * Author: 王俊超
 * Date: 2017-12-06 20-26
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RScriptDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        redisson.getBucket("foo").set("bar");
        String r = redisson.getScript().eval(RScript.Mode.READ_ONLY,
                "return redis.call('get', 'foo')", RScript.ReturnType.VALUE);

        System.out.println(r);

        // 通过预存的脚本进行同样的操作
        RScript s = redisson.getScript();
        // 首先将脚本保存到所有的Redis主节点
        String res = s.scriptLoad("return redis.call('get', 'foo')");

        System.out.println(res);
        // 返回值 res == 282297a0228f48cd3fc6a55de6316f31422f5d17

        // 再通过SHA值调用脚本
        Future<Object> r1 = redisson.getScript().evalShaAsync(RScript.Mode.READ_ONLY,
                "282297a0228f48cd3fc6a55de6316f31422f5d17",
                RScript.ReturnType.VALUE, Collections.emptyList());

        System.out.println(r1.get());

        redisson.shutdown();
    }
}
