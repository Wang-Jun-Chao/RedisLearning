package wjc.redis.command.keys;

import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DefaultSortParameters;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import wjc.redis.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangjunchao(王俊超)
 * @time: 2018-09-07 18:51
 **/
public class Sort extends Command<String, Number> {
    private final static Logger logger = LoggerFactory.getLogger(Sort.class);

    @Test
    @Override
    public void testTemplate() {
        List<Number> list = Lists.newArrayList(30, 1.5, 10, 8);
        template.opsForList().leftPushAll("cost", list);
        List<Number> cost = template.sort(SortQueryBuilder.sort("cost").build());
        System.out.println(cost);
        sort(list);

        Assert.assertEquals(list, cost);
    }

    @Test
    public void testTemplateDescOrder() {
        template.opsForList().leftPushAll("cost", 30, 1.5, 10, 8);
        List<Number> cost = template.sort(SortQueryBuilder
                .sort("cost")
                .order(SortParameters.Order.DESC)
                .build());
        System.out.println(cost);

    }

    /**
     * 使用字母默认排序
     */
    @Test
    public void testTemplateAlpha() {
        RedisTemplate<String, String> template = Command.template();
        List<String> list = Lists.newArrayList("www.reddit.com", "www.slashdot.com", "www.infoq.com");
        template.opsForList().leftPushAll("website", list);
        List<String> cost = template.sort(SortQueryBuilder
                .sort("website")
                .alphabetical(true)
                .build());
        System.out.println(cost);
    }

    /**
     * 限制返回结果
     */
    @Test
    public void testTemplateLimit() {
        RedisTemplate<String, Integer> template = Command.template();
        template.opsForList().rightPushAll("rank", 1, 3, 5, 7, 9);
        template.opsForList().rightPushAll("rank", 2, 4, 6, 8, 10);
        List<Integer> rank = template.sort(SortQueryBuilder
                .sort("rank")
                .limit(0, 5)
                .build());
        System.out.println(rank);
    }

    @Test
    @Override
    public void testConnection() {
        List<Number> list = Lists.newArrayList(30, 1.5, 10, 8);
        template.opsForList().leftPushAll("cost", list);
        List<byte[]> cost = connection.sort(keySerializer.serialize("cost"), new DefaultSortParameters()
                .order(SortParameters.Order.ASC)
                .numeric());
        cost.forEach(item-> System.out.println(valueSerializer.deserialize(item)));

        List<Number> costt =  new ArrayList<>();
        cost.forEach(item-> costt.add(valueSerializer.deserialize(item)));
        sort(list);

        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals(list.get(i).doubleValue(), costt.get(i).doubleValue(), 0.000001);
        }
    }

    private void sort(List<Number> list) {
        list.sort((o1, o2) -> {
            if (o1.doubleValue() == o2.doubleValue()) {
                return 0;
            } else if (o1.doubleValue() < o2.doubleValue()) {
                return -1;
            } else {
                return 1;
            }
        });
    }
}
