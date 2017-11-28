package wjc.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Author: 王俊超
 * Date: 2017-11-28 21:13
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class RGeoDemo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        redisson.getKeys().flushall();

        RGeo<String> geo = redisson.getGeo("test");
        geo.add(new GeoEntry(13.361389, 38.115556, "Palermo"),
                new GeoEntry(15.087269, 37.502669, "Catania"));
        geo.addAsync(37.618423, 55.751244, "Moscow");

        // 求两个城市的跨度，以米为单位
        Double distance = geo.dist("Palermo", "Catania", GeoUnit.METERS);
        System.out.println(distance);

        // 获取名称的hash值
        RFuture<Map<String, String>> future = geo.hashAsync("Palermo", "Catania");
        System.out.println(future.get());

        // 返回城市的经纬度
        Map<String, GeoPosition> positions = geo.pos("test2", "Palermo", "test3", "Catania", "test1");
        System.out.println(positions);

        // (15, 37) 200公里以内的城市
        List<String> cities = geo.radius(15, 37, 200, GeoUnit.KILOMETERS);
        System.out.println(cities);

        // (15, 37) 200公里以内的城市，城市带经度
        Map<String, GeoPosition> citiesWithPositions = geo.radiusWithPosition(15, 37, 200, GeoUnit.KILOMETERS);
        System.out.println(citiesWithPositions);

        redisson.getKeys().flushall();

        redisson.shutdown();
    }
}
