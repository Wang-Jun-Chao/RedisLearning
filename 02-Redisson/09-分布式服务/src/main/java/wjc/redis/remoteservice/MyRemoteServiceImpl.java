package wjc.redis.remoteservice;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-47
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
// 远程接口的实现
public class MyRemoteServiceImpl implements MyRemoteInterface {

    private AtomicLong iterations = new AtomicLong(0);

    public Long myBusyMethod(Long param1, String param2) {
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            long v = iterations.incrementAndGet();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrupted! " + i);
                return v;
            }
        }

        return Long.MAX_VALUE;
    }

}