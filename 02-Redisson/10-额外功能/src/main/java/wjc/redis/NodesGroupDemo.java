package wjc.redis;

import org.redisson.Redisson;
import org.redisson.api.NodesGroup;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.connection.ConnectionListener;

import java.net.InetSocketAddress;

/**
 * Author: 王俊超
 * Date: 2017-12-06 07-58
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class NodesGroupDemo {
    public static void main(String[] args) {
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://192.168.241.150:7110", "redis://192.168.241.150:7111",
                "redis://192.168.241.150:7112", "redis://192.168.241.150:7113",
                "redis://192.168.241.150:7114");

        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        NodesGroup nodesGroup = redisson.getNodesGroup();
        nodesGroup.addConnectionListener(new ConnectionListener() {
            public void onConnect(InetSocketAddress addr) {
                // Redis节点连接成功
                System.out.println(addr + " connect.");
            }

            public void onDisconnect(InetSocketAddress addr) {
                // Redis节点连接断开
                System.out.println(addr + " disconnect.");

            }
        });

        redisson.shutdown();
    }
}
