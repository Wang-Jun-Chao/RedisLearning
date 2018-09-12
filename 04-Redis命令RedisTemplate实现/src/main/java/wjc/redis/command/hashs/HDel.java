package wjc.redis.command.hashs;


import org.junit.Assert;
import org.junit.Test;
import wjc.redis.Command;

/**
 * <pre>
 *
 * </pre>
 * Author: 王俊超
 * Date: 2018-09-13 07:23
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class HDel extends Command<String, String> {
    @Test
    @Override
    public void testTemplate() {
        template.opsForHash().put("myhash", "field1", "foo");
        Long delete = template.opsForHash().delete("myhash", "field1");
        System.out.println(delete);
        Assert.assertEquals(Long.valueOf(1), delete);

        delete = template.opsForHash().delete("myhash", "field2");
        System.out.println(delete);
        Assert.assertEquals(Long.valueOf(0), delete);
    }

    @Test
    @Override
    public void testConnection() {
        template.opsForHash().put("myhash", "field1", "foo");

        Long delete = connection.hDel(hashK"myhash", "field1");
        System.out.println(delete);
        Assert.assertEquals(Long.valueOf(1), delete);

        delete = template.opsForHash().delete("myhash", "field2");
        System.out.println(delete);
        Assert.assertEquals(Long.valueOf(0), delete);
    }
}
