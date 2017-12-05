package wjc.redis.mapreduce;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.api.mapreduce.RMapReduce;

import java.util.Map;

/**
 * Author: 王俊超
 * Date: 2017-12-05 22-11
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class MapReduceDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        RMap<String, String> map = redisson.getMap("wordsMap");

        map.put("line1", "Alice was beginning to get very tired");
        map.put("line2", "of sitting by her sister on the bank and");
        map.put("line3", "of having nothing to do once or twice she");
        map.put("line4", "had peeped into the book her sister was reading");
        map.put("line5", "but it had no pictures or conversations in it");
        map.put("line6", "and what is the use of a book");
        map.put("line7", "thought Alice without pictures or conversation");

        RMapReduce<String, String, String, Integer> mapReduce
                = map.<String, Integer>mapReduce()
                .mapper(new WordMapper())
                .reducer(new WordReducer());

        System.out.println(mapReduce == null);

        // 统计词频
        Map<String, Integer> mapToNumber = mapReduce.execute();
        System.out.println(mapToNumber);
        System.out.println(mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(mapToNumber));

        // 统计字数
        Integer totalWordsAmount = mapReduce.execute(new WordCollator());
        System.out.println("totalWordsAmount: " + totalWordsAmount);
//
        redisson.shutdown();
    }
}
