package wjc.redis;

import java.util.UUID;

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
        msg = UUID.randomUUID().toString();
    }

    public SomeObject(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
