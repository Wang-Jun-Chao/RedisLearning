package wjc.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RedissonClient;

/**
 * Author: 王俊超
 * Date: 2017-12-06 08-09
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RBatchDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        RBatch batch = redisson.createBatch();
        batch.getMap("test").fastPutAsync("1", "2");
        batch.getMap("test").fastPutAsync("2", "3");
        batch.getMap("test").putAsync("2", "5");
        batch.getAtomicLong("counter").incrementAndGetAsync();
        batch.getAtomicLong("counter").incrementAndGetAsync();

        BatchResult<?> res = batch.execute();

        System.out.println(res.getResponses());


        redisson.getKeys().flushall();

        batch = redisson.createBatch();
        batch.getMap("test").fastPutAsync("1", "2");
        batch.getMap("test").fastPutAsync("2", "3");
        batch.getMap("test").putAsync("2", "5");
        batch.getAtomicLong("counter").incrementAndGetAsync();
        batch.getAtomicLong("counter").incrementAndGetAsync();

        // 或
        RFuture<BatchResult<?>> batchResultRFuture = batch.executeAsync();
        System.out.println(batchResultRFuture.get().getResponses());

        // 发送指令并等待执行，但是跳过结果
        RBatch batchResult = batch.skipResult();
        System.out.println(batchResult);

        redisson.shutdown();
    }
}
