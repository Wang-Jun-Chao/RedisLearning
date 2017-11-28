package wjc.redis;

import org.omg.CORBA.Any;

/**
 * Author: 王俊超
 * Date: 2017-11-28 20:44
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class AnyObject {
    private int i;

    public AnyObject() {

    }

    public AnyObject(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnyObject anyObject = (AnyObject) o;
        return i == anyObject.i;
    }

    @Override
    public int hashCode() {
        return i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
