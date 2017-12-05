package wjc.redis.remoteservice;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-11
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RemoteServiceDemo {

    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient server = Redisson.create();
        RedissonClient client = Redisson.create();

        try {
            // 在调用远程方法以前，应该首先注册远程服务
            // 只注册了一个服务端工作者实例，只能同时执行一个并发调用
            server.getRemoteService().register(RemoteInterface.class, new RemoteImpl());

            // 注册了12个服务端工作者实例，可以同时执行12个并发调
//            server.getRemoteService().register(RemoteInterface.class, new RemoteImpl(), 12);

            // 客户端调用
            RemoteInterface service = client.getRemoteService().get(RemoteInterface.class);

            Long r = service.myMethod(21L);
            System.out.println("result: " + r);

        } finally {
            client.shutdown();
            server.shutdown();
        }

    }
}
