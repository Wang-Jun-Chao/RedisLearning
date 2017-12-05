package wjc.redis.mapreduce;

import org.redisson.api.mapreduce.RCollector;
import org.redisson.api.mapreduce.RMapper;

/**
 * Author: 王俊超
 * Date: 2017-12-05 22-08
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class WordMapper implements RMapper<String, String, String, Integer> {
    @Override
    public void map(String key, String value, RCollector<String, Integer> collector) {

        System.out.println(key + " : " + value);

        String[] words = value.split("[^a-zA-Z]");
        for (String word : words) {
            collector.emit(word, 1);
        }
    }
}
