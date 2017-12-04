package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;

import java.util.UUID;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-11
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RemoteAsyncServiceDemo {

    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient server = Redisson.create();
        RedissonClient client = Redisson.create();

        try {
            // 在调用远程方法以前，应该首先注册远程服务
            // 只注册了一个服务端工作者实例，只能同时执行一个并发调用
            server.getRemoteService().register(RemoteInterface2.class, new RemoteInterface2Impl());

            // 客户端调用
            RemoteInterfaceAsync service = client.getRemoteService().get(RemoteInterfaceAsync.class);

            RFuture<Long> future = service.someMethod1(1L, "a");
            System.out.println(future.get());

            service.someMethod2(new MyObject(UUID.randomUUID().toString()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.shutdown();
            server.shutdown();
        }


    }
}
