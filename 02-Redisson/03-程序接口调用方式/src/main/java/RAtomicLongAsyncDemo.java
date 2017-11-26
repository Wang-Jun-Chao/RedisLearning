import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RAtomicLongAsync;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * Author: 王俊超
 * Date: 2017-11-26 22:29
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RAtomicLongAsyncDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);

        // RAtomicLong接口继承了RAtomicLongAsync接口
        RAtomicLong longObject = client.getAtomicLong("myLong");
        longObject.set(1);
        Boolean result = longObject.compareAndSet(1, 401);
        if (result) {
            System.out.println(client.getAtomicLong("myLong"));
        } else {
            System.out.println("设值失败");
        }

        longObject.set(1);
        RFuture<Boolean> future = longObject.compareAndSetAsync(1, 402);
        future.handle((BiFunction<Boolean, Throwable, Object>) (b, t) -> b);
        if (future.get()) {
            System.out.println(client.getAtomicLong("myLong"));
        } else {
            System.out.println("设置值失败");
        }

        client.shutdown();
    }
}
