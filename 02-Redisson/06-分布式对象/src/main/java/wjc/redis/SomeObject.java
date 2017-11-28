package wjc.redis;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:36
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SomeObject {
    String msg;

    public SomeObject() {
    }

    public SomeObject(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
