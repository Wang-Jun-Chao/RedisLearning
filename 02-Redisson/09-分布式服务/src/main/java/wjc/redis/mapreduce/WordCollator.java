package wjc.redis.mapreduce;

import org.redisson.api.mapreduce.RCollator;

import java.util.Map;

/**
 * Author: 王俊超
 * Date: 2017-12-05 22-09
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class WordCollator implements RCollator<String, Integer, Integer> {

    @Override
    public Integer collate(Map<String, Integer> resultMap) {
        int result = 0;
        for (Integer count : resultMap.values()) {
            result += count;
        }
        return result;
    }

}
