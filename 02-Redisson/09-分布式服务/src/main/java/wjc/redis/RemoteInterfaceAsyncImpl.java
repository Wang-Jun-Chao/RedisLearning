package wjc.redis;

import org.redisson.api.RFuture;
import org.redisson.executor.RemotePromise;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-24
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RemoteInterfaceAsyncImpl implements RemoteInterfaceAsync {
    public RFuture<Long> someMethod1(Long param1, String param2) {
        return new RemotePromise<Long>(param1 + ", " + param2);
    }

    public RFuture<Void> someMethod2(MyObject param) {
        return new RemotePromise<Void>(param.getValue());
    }
}
