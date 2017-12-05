package wjc.redis.remoteservice;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RRemoteService;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-46
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class CancelAsyncDemo {
    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        try {

            RRemoteService remoteService = redisson.getRemoteService();
            ExecutorService executor = Executors.newFixedThreadPool(5);
            // 注册远程服务的服务端的同时，通过单独指定的ExecutorService来配置执行线程池
            MyRemoteInterface serviceImpl = new MyRemoteServiceImpl();
            remoteService.register(MyRemoteInterface.class, serviceImpl, 5, executor);

            // 异步调用方法
            MyRemoteInterfaceAsync asyncService = remoteService.get(MyRemoteInterfaceAsync.class);
            RFuture<Long> future = asyncService.myBusyMethod(1L, "someparam");

            // 取消异步调用
            future.cancel(true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisson.shutdown();
        }
    }
}
