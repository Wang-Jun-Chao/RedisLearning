package wjc.redis.command.sets;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 22:51
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SPop extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "one", "two", "three");


        String pop = template.opsForSet().pop("myset");
        System.out.println(pop);
        Assert.assertNotNull(pop);

        template.opsForSet().add("myset", "four", "five");
        List<String> pops = template.opsForSet().pop("myset", 3);
        System.out.println(pops);
        Assert.assertNotNull(pops);
        Assert.assertTrue(pops.size() > 0);

        System.out.println(template.opsForSet().members("myset"));
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("myset", "one", "two", "three");


        String pop = valueSerializer.deserialize(connection.sPop(keySerializer.serialize("myset")));
        System.out.println(pop);
        Assert.assertNotNull(pop);

        template.opsForSet().add("myset", "four", "five");
        List<String> pops = Lists.newArrayList();
        connection.sPop(keySerializer.serialize("myset"), 3).forEach(item -> pops.add(
                valueSerializer.deserialize(item)));
        System.out.println(pops);
        Assert.assertNotNull(pops);
        Assert.assertTrue(pops.size() > 0);

        System.out.println(template.opsForSet().members("myset"));
    }
}
