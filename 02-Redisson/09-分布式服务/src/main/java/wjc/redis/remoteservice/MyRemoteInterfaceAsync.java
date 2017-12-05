package wjc.redis.remoteservice;

import org.redisson.api.RFuture;
import org.redisson.api.annotation.RRemoteAsync;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-47
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
// 匹配远程接口的异步接口
@RRemoteAsync(MyRemoteInterface.class)
public interface MyRemoteInterfaceAsync {
    RFuture<Long> myBusyMethod(Long param1, String param2);
}
