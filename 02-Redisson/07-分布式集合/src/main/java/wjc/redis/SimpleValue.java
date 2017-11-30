package wjc.redis;

import java.util.UUID;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:36
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SimpleValue {
    String value;

    public SimpleValue() {
        value = UUID.randomUUID().toString();
    }

    public SimpleValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SimpleValue{" +
                "value='" + value + '\'' +
                '}';
    }
}
