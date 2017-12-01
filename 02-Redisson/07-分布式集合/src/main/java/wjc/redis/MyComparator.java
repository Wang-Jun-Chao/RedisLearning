package wjc.redis;

/**
 * Author: 王俊超
 * Date: 2017-12-01 08:14
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class MyComparator implements java.util.Comparator<Integer> {

    @Override
    public int compare(Integer v, Integer s) {
        if (v == null) {
            return -1;
        }

        if (s == null) {
            return 1;
        }

        return v.compareTo(s);
    }
}
