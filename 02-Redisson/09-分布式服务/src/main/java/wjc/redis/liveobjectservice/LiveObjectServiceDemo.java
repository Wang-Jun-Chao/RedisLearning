package wjc.redis.liveobjectservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Author: 王俊超
 * Date: 2017-12-05 08-04
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class LiveObjectServiceDemo {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();
        redisson.getKeys().flushall();

        RLiveObjectService liveObjectService = redisson.getLiveObjectService();

        Customer customer = new Customer("12");
        // customer object is becoming "live" object
        customer = liveObjectService.merge(customer);

        customer.setName("Alexander Pushkin");
        customer.setPhone("+7193127489123");
        customer.setAddress("Moscow, Tverskaya str");

        Product product = new Product(1L, "FoodBox");
        // product object is becoming "live" object
        product = liveObjectService.merge(product);

        product.getItemName2Amount().put("apple", 1);
        product.getItemName2Amount().put("banana", 12);
        product.setPrice(BigDecimal.valueOf(10));
        product.setUnitsInStock(12);

        Order order = new Order(customer);
        // order object is becoming "live" object
        order = liveObjectService.merge(order);

        order.setDate(new Date());
        order.setShipAddress("Moscow, Gasheka str");
        order.setShipName("James Bond");
        order.setShipPostalCode("141920");

        OrderDetail od = new OrderDetail(order, product);
        // OrderDetail object is becoming "live" object
        od = liveObjectService.merge(od);
        od.setPrice(BigDecimal.valueOf(9));
        od.setQuantity(1);
        order.getOrderDetails().add(od);
        customer.getOrders().add(order);

        // "live" object could be get on other JVM.
        Customer attachedCustomer = liveObjectService.get(Customer.class, "12");
        System.out.println(mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(attachedCustomer));

        System.out.println("=====");


        for (Order attachedOrder : attachedCustomer.getOrders()) {
            for (OrderDetail orderDetail : attachedOrder.getOrderDetails()) {
                System.out.println(mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(orderDetail));
            }
        }

        Product attachedProduct = liveObjectService.get(Product.class, 1L);
        System.out.println(mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(attachedProduct));

        redisson.getKeys().flushall();
        redisson.shutdown();
    }
}
