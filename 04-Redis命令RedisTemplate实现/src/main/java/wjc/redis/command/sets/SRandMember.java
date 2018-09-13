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
public class SRandMember extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForSet().add("myset", "one", "two", "three");


        String pop = template.opsForSet().randomMember("myset");
        System.out.println(pop);
        Assert.assertNotNull(pop);

        List<String> pops = template.opsForSet().randomMembers("myset", 2);
        System.out.println(pops);
        Assert.assertNotNull(pops);
        Assert.assertTrue(pops.size() > 0);

        pops = template.opsForSet().randomMembers("myset", 5);
        System.out.println(pops);
        Assert.assertNotNull(pops);
        Assert.assertTrue(pops.size() > 0);

    }

    @Test
    @Override
    public void testConnection() {
        template.opsForSet().add("myset", "one", "two", "three");


        String pop = valueSerializer.deserialize(connection.sRandMember(keySerializer.serialize("myset")));
        System.out.println(pop);
        Assert.assertNotNull(pop);

        List<String> pops = Lists.newArrayList();
        connection.sRandMember(keySerializer.serialize("myset"), 2).forEach(item -> pops.add(
                valueSerializer.deserialize(item)));
        System.out.println(pops);
        Assert.assertNotNull(pops);
        Assert.assertTrue(pops.size() > 0);

        pops.clear();
        connection.sRandMember(keySerializer.serialize("myset"), -5).forEach(item -> pops.add(
                valueSerializer.deserialize(item)));
        System.out.println(pops);
        Assert.assertNotNull(pops);
        Assert.assertTrue(pops.size() > 0);

    }
}
