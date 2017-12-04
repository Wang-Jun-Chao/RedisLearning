package wjc.redis;

/**
 * Author: 王俊超
 * Date: 2017-12-05 07-24
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class MyObject {
    String value;

    public MyObject() {
    }

    public MyObject(String value) {
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
        return "MyObject{" +
                "value='" + value + '\'' +
                '}';
    }
}
