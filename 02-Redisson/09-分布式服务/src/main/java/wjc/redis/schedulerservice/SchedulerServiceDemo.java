package wjc.redis.schedulerservice;

import org.redisson.Redisson;
import org.redisson.RedissonNode;
import org.redisson.api.CronSchedule;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RScheduledFuture;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;
import wjc.redis.executorservice.CallableTask;
import wjc.redis.executorservice.RunnableTask;

import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Author: 王俊超
 * Date: 2017-12-05 21-54
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SchedulerServiceDemo {
    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://192.168.241.150:7110", "redis://192.168.241.150:7111",
                "redis://192.168.241.150:7112", "redis://192.168.241.150:7113",
                "redis://192.168.241.150:7114");

        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RedissonNodeConfig nodeConfig = new RedissonNodeConfig(config);
        nodeConfig.setExecutorServiceWorkers(Collections.singletonMap("myExecutor", 5));
        RedissonNode node = RedissonNode.create(nodeConfig);
        node.start();

        RScheduledExecutorService e = redisson.getExecutorService("myExecutor");
        RScheduledFuture<?> schedule = e.schedule(new RunnableTask(), 10, TimeUnit.SECONDS);
        System.out.println("a: " + schedule.get());

        schedule = e.schedule(new CallableTask(), 1, TimeUnit.MINUTES);
        System.out.println("b: " + schedule.get());

        ScheduledFuture<?> future = e.schedule(new RunnableTask(), CronSchedule.of("10 0/5 * * * ?"));
        System.out.println("c: " + future.get());

        future = e.schedule(new RunnableTask(), CronSchedule.dailyAtHourAndMinute(10, 5));
        System.out.println("d: " + future.get());

        future = e.schedule(new RunnableTask(), CronSchedule.weeklyOnDayAndHourAndMinute(
                12, 4, Calendar.MONDAY, Calendar.FRIDAY));
        System.out.println("e: " + future);

        e.shutdown();
        node.shutdown();
        redisson.shutdown();
    }
}
