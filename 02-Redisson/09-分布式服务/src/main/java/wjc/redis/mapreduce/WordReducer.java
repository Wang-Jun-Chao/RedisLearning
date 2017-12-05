package wjc.redis.mapreduce;

import org.redisson.api.mapreduce.RReducer;

import java.util.Iterator;

/**
 * Author: 王俊超
 * Date: 2017-12-05 22-13
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class WordReducer implements RReducer<String, Integer> {

    @Override
    public Integer reduce(String reducedKey, Iterator<Integer> iter) {
        int sum = 0;
        while (iter.hasNext()) {
            Integer i = iter.next();
            sum += i;
        }
        return sum;
    }

}
