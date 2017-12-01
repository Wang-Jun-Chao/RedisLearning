package wjc.redis;

/**
 * Author: 王俊超
 * Date: 2017-12-01 08:20
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SomeObject2 {
    String v;
    String s;

    public SomeObject2() {
    }

    public SomeObject2(String v, String s) {
        this.v = v;
        this.s = s;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "SomeObject2{" +
                "v='" + v + '\'' +
                ", s='" + s + '\'' +
                '}';
    }
}
