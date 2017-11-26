package wjc.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: 王俊超
 * Date: 2017-11-26 21:14
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        InputStream is = Main2.class.getClassLoader().getResourceAsStream("config-file.json");
        Config config = Config.fromJSON(is);
        RedissonClient redisson = Redisson.create(config);
        is.close();

        System.out.println(config.toJSON());
        System.out.println(config.toYAML());
    }
}
