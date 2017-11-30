package wjc.redis;

import java.util.UUID;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:36
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SimpleKey {
    String key;

    public SimpleKey() {
        key = UUID.randomUUID().toString();
    }

    public SimpleKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SimpleKey{" +
                "key='" + key + '\'' +
                '}';
    }
}
