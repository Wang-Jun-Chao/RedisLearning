package wjc.redis.config;

import org.redisson.config.Config;

/**
 * 程序化配置方法
 * Author: 王俊超
 * Date: 2017-11-26 21:06
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.setUseLinuxNativeEpoll(true);
        config.useClusterServers().addNodeAddress("redis://192.168.241.150:7110");
    }
}
