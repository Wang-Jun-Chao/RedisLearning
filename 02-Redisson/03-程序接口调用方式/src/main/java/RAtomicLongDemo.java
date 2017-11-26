import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Author: 王俊超
 * Date: 2017-11-26 22:10
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RAtomicLongDemo {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);

        RAtomicLong longObject = client.getAtomicLong("myLong");
        longObject.set(3);

        // 同步执行方式
        longObject.compareAndSet(3, 401);
        System.out.println(client.getAtomicLong("myLong"));


        // 异步执行方式
        longObject.set(3);
        Future<Boolean> future = longObject.compareAndSetAsync(3, 402);
        if (future.get()) {
            System.out.println(client.getAtomicLong("myLong"));
        } else {
            System.out.println("执行出错");
        }


        // 是以发布订阅的模式进行的
        longObject.set(3);
        RedissonReactiveClient redissonReactiveClient = Redisson.createReactive(config);
        RAtomicLongReactive rAtomicLongReactive = redissonReactiveClient.getAtomicLong("myLong");
        // 异步流执行方式
        Publisher<Boolean> publisher = rAtomicLongReactive.compareAndSet(3, 403);
        publisher.subscribe(new BaseSubscriber<Boolean>() {
            @Override
            protected void hookOnComplete() {
                System.out.println(client.getAtomicLong("myLong"));
            }
        });



        client.shutdown();
        redissonReactiveClient.shutdown();
    }
}
