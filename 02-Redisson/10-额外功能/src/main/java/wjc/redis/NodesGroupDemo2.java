package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.Node;
import org.redisson.api.NodesGroup;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Collection;

/**
 * Author: 王俊超
 * Date: 2017-12-06 07-58
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class NodesGroupDemo2 {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://192.168.241.150:7110", "redis://192.168.241.150:7111",
                "redis://192.168.241.150:7112", "redis://192.168.241.150:7113",
                "redis://192.168.241.150:7114");

        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        NodesGroup nodesGroup = redisson.getNodesGroup();
        Collection<Node> allNodes = nodesGroup.getNodes();
        for (Node n : allNodes) {
            System.out.println(n.getAddr());
            // 下面的代码会报错
//            System.out.println(n.getType() == NodeType.MASTER ?
//                    "master ping: " + n.ping() : "slaver no ping");
        }

        // 或者
        System.out.println(nodesGroup.pingAll());

        redisson.shutdown();
    }
}
