package wjc.redis.remoteservice;

import java.util.UUID;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-25
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RemoteInterface2Impl implements RemoteInterface2 {
    @Override
    public Long someMethod1(Long param1, String param2) {
        System.out.println("someMethod1: " + param1 + ", " + param2);
        return System.currentTimeMillis();
    }

    @Override
    public void someMethod2(MyObject param) {
        System.out.println("MyObject: " + param);
    }

    @Override
    public MyObject someMethod3() {
        return new MyObject(UUID.randomUUID().toString());
    }
}
